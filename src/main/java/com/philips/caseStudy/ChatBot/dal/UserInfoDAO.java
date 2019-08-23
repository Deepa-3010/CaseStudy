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

@Transactional
@Repository
public class UserInfoDAO implements UserInfoInterface {


  @PersistenceContext
  EntityManager em;




  @Override
  public UserInfo save(UserInfo user) {
    em.persist(user);
    return user;
  }



  @Override
  public List<UserInfo> findAll() {

    return em.createQuery("select user from UserInfo as user ").getResultList();
  }

}
