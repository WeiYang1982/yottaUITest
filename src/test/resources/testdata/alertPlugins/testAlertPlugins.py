import requests
import json
import logging
import voluptuous as vol
import homeassistant.helpers.config_validation as cv
from homeassistant.helpers.entity import Entity
from homeassistant.components.sensor import PLATFORM_SCHEMA
from homeassistant.const import CONF_NAME
from datetime import datetime
from homeassistant.helpers.entity import generate_entity_id

_Log=logging.getLogger(__name__)

DEFAULT_NAME = 'chinese_calendar'
friendly_name = '农历日期'

PLATFORM_SCHEMA = PLATFORM_SCHEMA.extend({
    vol.Optional(CONF_NAME, default=DEFAULT_NAME): cv.string,
})

def setup_platform(hass, config, add_devices, discovery_info=None):
    """Setup the sensor platform."""
    sensor_name = config.get(CONF_NAME)

    add_devices([ChineseCalendar(hass,sensor_name,friendly_name)])


class ChineseCalendar(Entity):
    """Representation of a Sensor."""

    def __init__(self,hass,sensor_name,friendly_name):
        """Initialize the sensor."""
        self._state = None
        self._hass = hass
        self.entity_id = generate_entity_id(
            'sensor.{}', sensor_name, hass=self._hass)
        self.attributes = {}
        self.friendly_name = friendly_name
        self.update()

    @property
    def name(self):
        """Return the name of the sensor."""
        return self.friendly_name

    @property
    def device_state_attributes(self):
        """Return the state attributes."""
        return self.attributes

    @property
    def icon(self):
        """Return the icon to use in the frontend, if any."""
        return 'mdi:movie-roll'

    @property
    def state(self):
        """Return the state of the sensor."""
        return self._state

    def update(self):
        """Fetch new state data for the sensor.

        This is the only method that should fetch new data for Home Assistant.
        """

        api_url = "http://www.sojson.com/open/api/lunar/json.shtml"

        resp = requests.get(api_url)
        if resp.status_code != 200:
            _Log.error("[chinese_calendar]: http get error code --" + str(resp.status_code))
            return
        resp.encoding = "utf-8"
        s = resp.text
        r = json.loads(s)
        d = r['data']['lunarYearString'].strip() + '-' +r['data']['cnmonth'] + '-' +r['data']['cnday']
        self._state = d
        self.attributes['宜'] = r['data']['suit']
        self.attributes['忌'] = r['data']['taboo']
        self.attributes['更新时间'] = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
