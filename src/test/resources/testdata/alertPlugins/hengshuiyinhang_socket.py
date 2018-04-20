#!/usr/bin/env python
# -*- coding: utf-8 -*-
# import sys
# reload(sys)
# sys.setdefaultencoding("utf-8")
__author__ = 'wu.ranbo'

import traceback
import sys
import socket
import time
import logging
import datetime

from django.template import Context, Template
req_logger = logging.getLogger("django.request")

_ALERT_HOST = "192.168.1.140"
_ALERT_PORT = 8080
_CONTENT_LENGTH = 9999 # 暂时不限制长度

ONLINE_CONTENT = """{% if alert.is_alert_recovery %}{{ alert.name }}已经恢复{% else %} {{ alert.name }} {{ alert.strategy.trigger.compare_desc_text }}，时间:{{ alert.send_time|date:"Y年n月d日 H:i:s" }}{% endif %}"""

SIMPLE_ONLINE_CONTENT = """{% if alert.is_alert_recovery %}{{ alert.name|slice:":40" }},恢复{% else %} {{ alert.name|slice:":40" }},触发 {% endif %}"""

META = {
    "name": "hengshuiyinhang_socket",
    "version": 3,
    "alias": "衡水银行告警插件",
    "configs": [
        {
            "name": "cellphones",
            "alias": "接收短信的手机号，多个之间用逗号分隔",
            "presence": True,
            "value_type": "string",
            "default_value": "",
            "style": {
                "rows": 1,
                "cols": 30
            }
        },
        {
            "name": "msg_content",
            "alias": "短信内容模板",
            "presence": True,
            "value_type": "string",
            "default_value": ONLINE_CONTENT,
            "style": {
                "rows": 2,
                "cols": 30
            }
        }
    ]
}


def _render(conf_obj, tmpl_str):
    t = Template(tmpl_str)
    c = Context(conf_obj)
    _content = t.render(c)
    return _content

def alert_content(alert, template_str):
    conf_obj = {'alert': alert}
    _content = _render(conf_obj, template_str)
    return _content

def content(params, alert):
    return _get_content_str(alert, params)

def _get_content_str(alert, params):
    msg_content = alert_content(alert, params['configs'][1]['value'])
    ret = msg_content
    if not isinstance(msg_content, unicode):
        try:
            unicode_content = msg_content.decode("utf-8")  # 判断长度需要用utf8的字符数
            if len(unicode_content) >= _CONTENT_LENGTH:
                ret = alert_content(alert, SIMPLE_ONLINE_CONTENT)
        except Exception, e:
            ret = alert_content(alert, SIMPLE_ONLINE_CONTENT)
    elif len(msg_content) >= _CONTENT_LENGTH:
        ret = alert_content(alert, SIMPLE_ONLINE_CONTENT)
    return ret

def handle(params, alert):
    try:
        host = _ALERT_HOST
        port = _ALERT_PORT
        req_logger.info("alert.plugins.hengshuiyinhang_socket ready before all send to %s:%d", host, port)

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        remote_ip = socket.gethostbyname( host)
        s.connect((remote_ip , port))

        sms_message = _get_content_str(alert, params)

        now = datetime.datetime.now()

        phones = str(params['configs'][0]['value']).split(",")
        for p in phones:
            body = """<?xml version="1.0" encoding="GB18030"?><Msg><Head><TxCode>8003</TxCode><SysId>MBAK</SysId><TxDate>"""
            body += now.strftime("%Y%m%d")
            body += """</TxDate> <TxTime>"""
            body += now.strftime("%H%M%S")
            body += """</TxTime> <TraceNo>00000001</TraceNo> </Head> <Body> <Cellphone>"""
            body += p
            body += """</Cellphone> <Message>"""
            body += sms_message
            body += """</Message> </Body> </Msg> """
            msg_head = "%08d" % len(body.decode("utf8").encode("gb18030"))
            msg = (msg_head + body).decode("utf8").encode("gb18030")
            req_logger.info("alert.plugins.hengshuiyinhang_socket ready send to %s:%d, phone:%s, msg:%s", host, port, p, msg)
            s.sendall(msg)
            req_logger.info("alert.plugins.hengshuiyinhang_socket success send to %s:%d, phone:%s, msg:%s", host, port, p, msg)

        # 返回值： {"Message":"OK","RequestId":"183F9A56-65D8-4C76-9190-02A4D5665B0E","BizId":"139307014188923976^0","Code":"OK"}
        req_logger.info("alert.plugins.hengshuiyinhang_socket success send all to %s:%d", host, port)
    except Exception, e:
        exc_type, exc_value, exc_traceback = sys.exc_info()
        req_logger.error(traceback.format_exception(exc_type, exc_value, exc_traceback))
        req_logger.error("alert.plugins.hengshuiyinhang_socket got exception %s", e)
        raise e
