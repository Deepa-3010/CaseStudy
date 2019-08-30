/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.philips.caseStudy.ChatBot.domain.Question;


public interface QuestionDAOInterface {

  void getQuestion() throws IOException;

  Question getQuestionByIndex(int index) throws IOException;

  void createQuestion() throws IOException;

  Question getRequiredQuestion(List<Question> questionList,int index);

  public boolean validate(int index,String answer) throws IOException;

  public boolean optionPresent(Map<String,String> options,String answer);

}
