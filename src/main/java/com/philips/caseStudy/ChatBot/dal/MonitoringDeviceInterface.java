/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.dto.MonitoringDeviceDTO;


public interface MonitoringDeviceInterface {

  MonitoringDevice save(MonitoringDeviceDTO device);

  List<MonitoringDevice> findAll();

}
