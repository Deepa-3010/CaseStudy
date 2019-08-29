/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.dto.MonitoringDeviceDTO;

@Transactional
@Repository
public class MonitoringDAO implements MonitoringDeviceInterface {

  @PersistenceContext
  EntityManager em;



  @Override
  public MonitoringDevice save(MonitoringDeviceDTO device) {
    final MonitoringDevice entityDevice=changeDTOToEntity(device);
    em.persist(entityDevice);
    return entityDevice;
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<MonitoringDevice> findAll() {

    return em.createQuery("select device from MonitoringDevice as device ").getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByUserChoice(String touch,float screenSize) {

    if(touch==null) {
      return em.createQuery("select device from MonitoringDevice as device where device.screenSize=:sizeParam").setParameter("sizeParam", screenSize).getResultList();
    }
    else if(screenSize==0) {
      return em.createQuery("select device from MonitoringDevice as device where device.touch=:touchParam").setParameter("touchParam", touch).getResultList();

    }
    else {
      return em.createQuery("select device from MonitoringDevice as device where device.screenSize=:sizeParam AND device.touch=:touchParam").setParameter("sizeParam", screenSize).setParameter("touchParam", touch).getResultList();
    }
  }

  @SuppressWarnings("unchecked")
  public boolean findByParameters(MonitoringDeviceDTO device)
  {
    final List<MonitoringDevice> devices=em.createQuery("select device from MonitoringDevice as device where device.screenSize=:sizeParam AND device.touch=:touchParam AND device.name=:nameParam")
        .setParameter("sizeParam", device.getScreenSize())
        .setParameter("touchParam", device.getTouch())
        .setParameter("nameParam", device.getName())
        .getResultList();
    if(devices.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public MonitoringDevice changeDTOToEntity(MonitoringDeviceDTO device)
  {
    final ModelMapper model=new ModelMapper();
    final MonitoringDevice devices=model.map(device,MonitoringDevice.class);
    return devices;
  }

}
