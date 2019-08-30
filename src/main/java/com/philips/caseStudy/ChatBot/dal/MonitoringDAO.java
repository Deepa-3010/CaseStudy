/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    final MonitoringDevice entityDevice=device.changeDTOToEntity(device);
    em.persist(entityDevice);
    return entityDevice;
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<MonitoringDevice> findAll() {

    return em.createQuery("select device.name from MonitoringDevice as device ").getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByUserChoiceOnlyTouch(String touch)
  {
    return em.createQuery("select device.name from MonitoringDevice as device where device.touch=:touchParam")
        .setParameter("touchParam",touch)
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByUserChoiceOnlyScreenSize(float screenSize)
  {
    return em.createQuery("select device.name from MonitoringDevice as device where device.screenSize=:sizeParam")
        .setParameter("sizeParam", screenSize)
        .getResultList();
  }
  @Override
  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByUserChoiceByBothTouchAndScreenSize(String touch,float screenSize) {


    return em.createQuery("select device.name from MonitoringDevice as device where device.screenSize=:sizeParam AND device.touch=:touchParam")
        .setParameter("sizeParam", screenSize)
        .setParameter("touchParam", touch)
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<MonitoringDevice> findByParameters(MonitoringDeviceDTO device)
  {
    return em.createQuery("select device.name from MonitoringDevice as device where device.screenSize=:sizeParam AND device.touch=:touchParam AND device.name=:nameParam")
        .setParameter("sizeParam", device.getScreenSize())
        .setParameter("touchParam", device.getTouch())
        .setParameter("nameParam", device.getName())
        .getResultList();

  }



}
