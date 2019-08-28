/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.caseStudy.ChatBot.dal.QuestionDAOInterface;
import com.philips.caseStudy.ChatBot.domain.Question;

@Service
public class QuestionService implements QuestionServiceInterface {

  @Autowired
  QuestionDAOInterface quesDao;


  @Override
  public Question returnQuestion(int index) throws IOException
  {
    return quesDao.getQuestionByIndex(index);
  }




}
