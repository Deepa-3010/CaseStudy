package com.philips.caseStudy.ChatBot.service;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.domain.UserInfo;


public interface ChatBotServiceInterface {

  MonitoringDevice save(MonitoringDevice device);

  List<MonitoringDevice> findAll();

  List<MonitoringDevice> findByUserChoice(String touch, float screenSize);

  UserInfo saveUsers(UserInfo user);

}
