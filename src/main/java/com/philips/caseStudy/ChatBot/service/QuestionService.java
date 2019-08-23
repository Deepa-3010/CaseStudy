/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.service;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements QuestionServiceInterface {


  ArrayList<String> questions = new ArrayList<>( Arrays.asList("alex", "brian", "charles") );

  public void setQuestions(ArrayList<String> questions) {
    this.questions = questions;
  }


  @Override
  public String returnQuestion(int index)
  {
    return questions.get(index);
  }

}
