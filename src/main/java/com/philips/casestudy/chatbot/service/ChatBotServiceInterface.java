package com.philips.casestudy.chatbot.service;

import java.util.List;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.domain.UserInfo;


public interface ChatBotServiceInterface {

  MonitoringDevice save(MonitoringDevice device);

  List<MonitoringDevice> findAll();

  List<MonitoringDevice> findByUserChoice(String touch, float screenSize);

  UserInfo saveUsers(UserInfo user);

}
