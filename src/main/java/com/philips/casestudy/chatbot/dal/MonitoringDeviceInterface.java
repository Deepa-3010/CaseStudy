package com.philips.casestudy.chatbot.dal;

import java.util.List;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;


public interface MonitoringDeviceInterface {

  MonitoringDevice save(MonitoringDevice device);

  List<MonitoringDevice> findAll();

}
