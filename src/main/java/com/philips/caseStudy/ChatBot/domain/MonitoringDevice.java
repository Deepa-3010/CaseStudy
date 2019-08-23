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
@Table(name="monitoringdevice")
public class MonitoringDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String name;
  String touch;
  @Column(name = "screen_size")
  float screenSize;




  public MonitoringDevice() {
    super();
    // TODO Auto-generated constructor stub
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTouch() {
    return touch;
  }
  public void setTouch(String touch) {
    this.touch = touch;
  }
  public double getScreenSize() {
    return screenSize;
  }
  public void setScreenSize(float screenSize) {
    this.screenSize = screenSize;
  }
  public int getId() {
    return id;
  }

  public MonitoringDevice(String name, String touch, float screenSize) {
    super();
    this.name = name;
    this.touch = touch;
    this.screenSize = screenSize;
  }



}
