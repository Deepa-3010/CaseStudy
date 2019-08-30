/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.UserInfo;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;


public interface UserInfoInterface {

  UserInfo save(UserInfoDTO user);

  List<UserInfo> findAll();

}
