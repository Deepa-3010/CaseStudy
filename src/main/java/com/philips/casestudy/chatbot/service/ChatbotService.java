/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.chatbot.dal.MonitoringDAO;
import com.philips.casestudy.chatbot.dal.UserInfoDAO;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.domain.UserInfo;

@Service
public class ChatbotService implements ChatBotServiceInterface {


  MonitoringDAO monitoringDAO;
  @Autowired
  public void setMonitoringDao(MonitoringDAO monitoringDao) {
    monitoringDAO = monitoringDao;
  }



  UserInfoDAO userInfoDAO;
  @Autowired
  public void setUserInfoDao(UserInfoDAO userInfoDao) {
    userInfoDAO = userInfoDao;
  }

  @Override
  public MonitoringDevice save(MonitoringDevice device) {
    return monitoringDAO.save(device);
  }

  @Override
  public List<MonitoringDevice> findAll() {
    return monitoringDAO.findAll();
  }

  @Override
  public List<MonitoringDevice> findByUserChoice(String touch, float screenSize) {
    return monitoringDAO.findByUserChoice(touch, screenSize);
  }

  @Override
  public UserInfo saveUsers(UserInfo user) {
    return userInfoDAO.save(user);
  }


}
