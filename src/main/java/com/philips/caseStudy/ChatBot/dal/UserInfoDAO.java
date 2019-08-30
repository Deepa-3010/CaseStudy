/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.caseStudy.ChatBot.domain.UserInfo;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;

@Transactional
@Repository
public class UserInfoDAO implements UserInfoInterface {


  @PersistenceContext
  EntityManager em;




  @Override
  public UserInfo save(UserInfoDTO user) {
    final UserInfo entityUser=user.changeDTOToEntity(user);
    em.persist(entityUser);
    return entityUser;
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<UserInfo> findAll() {

    return em.createQuery("select user from UserInfo as user ").getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<UserInfo> findByContactNo(UserInfoDTO user)
  {
    return em.createQuery("select user from UserInfo as user where user.contactNo=:contactParam ")
        .setParameter("contactParam", user.getContactNo())
        .getResultList();


  }

  @SuppressWarnings("unchecked")
  public List<UserInfo> findByEmail(UserInfoDTO user) {
    return em.createQuery("select user from UserInfo as user where user.email=:emailParam ")
        .setParameter("emailParam", user.getEmail())
        .getResultList();

  }


}
