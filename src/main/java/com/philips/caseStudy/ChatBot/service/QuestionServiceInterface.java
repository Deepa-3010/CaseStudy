/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.service;

import java.io.IOException;
import com.philips.caseStudy.ChatBot.domain.Question;

public interface QuestionServiceInterface {

  Question returnQuestion(int index) throws IOException;

  public boolean validate(int index,String answer) throws IOException;


}
