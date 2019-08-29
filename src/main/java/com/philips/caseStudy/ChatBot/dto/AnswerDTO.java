/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dto;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.philips.caseStudy.ChatBot.domain.Answer;


public class AnswerDTO {
  List <String> userAnswer;

  public List<String> getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(List<String> userAnswer) {
    this.userAnswer = userAnswer;
  }

  public AnswerDTO(Answer ans)
  {
    this(ans.getUserAnswer());
  }

  public AnswerDTO(List<String> answ)
  {
    this.userAnswer=answ;
  }

  public Answer changeDTOToEntity(AnswerDTO ans)
  {
    final ModelMapper model=new ModelMapper();
    final Answer answ=model.map(ans,Answer.class);
    return answ;

  }

  public AnswerDTO() {
    super();
    // TODO Auto-generated constructor stub
  }
}
