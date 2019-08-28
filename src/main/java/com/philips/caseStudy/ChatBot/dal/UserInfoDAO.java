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
import com.philips.caseStudy.ChatBot.domain.UserInfo;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;

@Transactional
@Repository
public class UserInfoDAO implements UserInfoInterface {


  @PersistenceContext
  EntityManager em;




  @Override
  public UserInfo save(UserInfoDTO user) {
    final ModelMapper model=new ModelMapper();
    final UserInfo users=model.map(user,UserInfo.class);
    em.persist(users);
    return users;
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<UserInfo> findAll() {

    return em.createQuery("select user from UserInfo as user ").getResultList();
  }

  @SuppressWarnings("unchecked")
  public boolean findUserByEmail(UserInfoDTO user)
  {
    final List<UserInfoDTO> users=em.createQuery("select user from UserInfo as user where email=:emailParam ")
        .setParameter("emailParam", user.getEmail())
        .getResultList();
    if(users.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  public boolean findUserByPhoneNumber(UserInfoDTO user)
  {
    final List<UserInfoDTO> users=em.createQuery("select user from UserInfo as user where contactNo=:contactParam ")
        .setParameter("contactParam", user.getContactNo())
        .getResultList();
    if(users.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

}
