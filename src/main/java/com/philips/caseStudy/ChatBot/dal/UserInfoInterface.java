package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.UserInfo;


public interface UserInfoInterface {

  UserInfo save(UserInfo user);

  List<UserInfo> findAll();

}
