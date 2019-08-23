package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;


public interface MonitoringDeviceInterface {

  MonitoringDevice save(MonitoringDevice device);

  List<MonitoringDevice> findAll();

}
