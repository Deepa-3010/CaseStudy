/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.chatbot.dal.QuestionDAO;

@Service
public class QuestionService implements QuestionServiceInterface {

  @Autowired
  QuestionDAO quesDao;


  @Override
  public JSONArray returnQuestion() throws IOException, ParseException
  {
    return quesDao.getQuestion();
  }

}
