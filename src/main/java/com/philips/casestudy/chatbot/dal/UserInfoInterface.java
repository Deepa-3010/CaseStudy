package com.philips.casestudy.chatbot.dal;

import java.util.List;
import com.philips.casestudy.chatbot.domain.UserInfo;


public interface UserInfoInterface {

  UserInfo save(UserInfo user);

  List<UserInfo> findAll();

}
