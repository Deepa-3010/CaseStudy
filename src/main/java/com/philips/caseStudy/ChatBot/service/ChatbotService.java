/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.caseStudy.ChatBot.dal.MonitoringDAO;
import com.philips.caseStudy.ChatBot.dal.UserInfoDAO;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.domain.UserInfo;

@Service
public class ChatbotService implements ChatBotServiceInterface {


  MonitoringDAO MonitoringDao;
  @Autowired
  public void setMonitoringDao(MonitoringDAO monitoringDao) {
    MonitoringDao = monitoringDao;
  }



  UserInfoDAO UserInfoDao;
  @Autowired
  public void setUserInfoDao(UserInfoDAO userInfoDao) {
    UserInfoDao = userInfoDao;
  }

  @Override
  public MonitoringDevice save(MonitoringDevice device) {
    return MonitoringDao.save(device);
  }

  @Override
  public List<MonitoringDevice> findAll() {
    return MonitoringDao.findAll();
  }

  @Override
  public List<MonitoringDevice> findByUserChoice(String touch, float screenSize) {
    return MonitoringDao.findByUserChoice(touch, screenSize);
  }

  @Override
  public UserInfo saveUsers(UserInfo user) {
    return UserInfoDao.save(user);
  }


}
