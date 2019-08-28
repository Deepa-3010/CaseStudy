/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dto;

import com.philips.caseStudy.ChatBot.domain.UserInfo;

public class UserInfoDTO {
  String name;
  int contactNo;
  String email;
  String city;

  public UserInfoDTO(UserInfo user)
  {
    this(user.getName(),user.getContactNo(),user.getEmail(),user.getCity());
  }

  public UserInfoDTO(String name,int contactNo,String email, String city)
  {
    this.name = name;
    this.contactNo = contactNo;
    this.email = email;
    this.city = city;
  }



  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getContactNo() {
    return contactNo;
  }
  public void setContactNo(int contactNo) {
    this.contactNo = contactNo;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
}
