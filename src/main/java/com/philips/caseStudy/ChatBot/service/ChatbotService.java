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
import com.philips.caseStudy.ChatBot.dto.MonitoringDeviceDTO;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;

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
  public MonitoringDevice save(MonitoringDeviceDTO device) {
    if(monitoringDAO.findByParameters(device))
    {
      return monitoringDAO.save(device);
    }
    else
    {
      return monitoringDAO.changeDTOToEntity(device);
    }
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
  public UserInfo saveUsers(UserInfoDTO user) {
    if(userInfoDAO.findByContactNo(user) && userInfoDAO.findByEmail(user))
    {
      return userInfoDAO.save(user);
    }
    else
    {
      return userInfoDAO.changeDTOToEntity(user);
    }

  }


}
