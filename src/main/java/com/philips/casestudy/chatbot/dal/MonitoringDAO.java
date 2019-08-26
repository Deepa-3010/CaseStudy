/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;

@Transactional
@Repository
public class MonitoringDAO implements MonitoringDeviceInterface {

  @PersistenceContext
  EntityManager em;



  @Override
  public MonitoringDevice save(MonitoringDevice device) {
    em.persist(device);
    return device;
  }


  @Override
  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findAll() {

    return em.createQuery("select device from MonitoringDevice as device ").getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByUserChoice(String touch,float screenSize) {

    if(touch==null) {
      return em.createQuery("select device from MonitoringDevice as device where device.screenSize:=sizeParam").setParameter("sizeParam", screenSize).getResultList();
    }
    else if(screenSize==0) {
      return em.createQuery("select device from MonitoringDevice as device where device.touch:=touchParam").setParameter("touchParam", touch).getResultList();

    }
    else {
      return em.createQuery("select device from MonitoringDevice as device where device.screenSize:=sizeParam AND device.touch:=touchParam").setParameter("sizeParam", screenSize).setParameter("touchParam", touch).getResultList();
    }
  }


}
