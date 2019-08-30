/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @Column(name="name")
  String name;
  @Column(name = "contact_no")
  int contactNo;
  @Column(name="email")
  String email;
  @Column(name="city")
  String city;

  public UserInfo() {
    super();
  }
  public UserInfo(String name, int contactNo, String email, String city) {
    super();
    this.name = name;
    this.contactNo = contactNo;
    this.email = email;
    this.city = city;
  }

  public int getId() {
    return id;
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

