/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.service;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.domain.UserInfo;
import com.philips.caseStudy.ChatBot.dto.MonitoringDeviceDTO;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;


public interface ChatBotServiceInterface {

  MonitoringDevice save(MonitoringDeviceDTO device);

  List<MonitoringDevice> findAll();

  UserInfo saveUsers(UserInfoDTO user);

  public List<MonitoringDevice> findByUserChoiceByBothTouchAndScreenSize(String touch, float screenSize) ;

  public List<MonitoringDevice> findByUserChoiceByTouchOnly(String touch);

  public List<MonitoringDevice> findByUserChoiceByScreenSizeOnly(float screenSize);

}
