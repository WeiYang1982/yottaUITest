package com.yottabyte.stepDefs;

import cucumber.api.java.en.And;
public class UploadData {


    @And("^I upload I upload some data")
    public void uploadData() {
        String cmd = "/opt/rizhiyi/python/bin/python log_gen.py -d192.168.1.171 -p5140 -l/home/sendlog/all_format_log/baimi-c200.conf -r2";


    }



}
