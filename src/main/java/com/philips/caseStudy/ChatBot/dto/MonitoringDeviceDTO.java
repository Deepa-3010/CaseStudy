/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dto;

import org.modelmapper.ModelMapper;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;

public class MonitoringDeviceDTO {
  String name;
  String touch;
  float screenSize;

  public MonitoringDeviceDTO(MonitoringDevice device)
  {
    this(device.getName(),device.getTouch(),device.getScreenSize());
  }

  public MonitoringDeviceDTO(String name,String touch,float screenSize)
  {
    this.name=name;
    this.touch=touch;
    this.screenSize=screenSize;
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
  public float getScreenSize() {
    return screenSize;
  }
  public void setScreenSize(float screenSize) {
    this.screenSize = screenSize;
  }

  public MonitoringDevice changeDTOToEntity(MonitoringDeviceDTO device)
  {
    final ModelMapper model=new ModelMapper();
    final MonitoringDevice devices=model.map(device,MonitoringDevice.class);
    return devices;
  }

}
