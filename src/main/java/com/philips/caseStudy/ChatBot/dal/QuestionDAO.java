/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.dal;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.philips.caseStudy.ChatBot.domain.Question;

@Repository
public class QuestionDAO implements QuestionDAOInterface {

  @Override
  public void getQuestion() throws IOException {
    final byte[] jsonData = Files.readAllBytes(Paths.get("C:/Users/320065420/BootCamp/CaseStudyAfterLinting/ChatBotAfterFirstLinting/src/main/java/com/philips/casestudy/chatbot/domain/question.txt"
        ));
    final ObjectMapper objectMapper = new ObjectMapper();

    final List<Question> questionList = objectMapper.readValue(jsonData, new TypeReference<List<Question>>(){});

    final ListIterator<Question> questionListIterator=questionList.listIterator();
    while(questionListIterator.hasNext()) {
      System.out.println(questionListIterator.next());
    }

  }


  @Override
  public Question getQuestionByIndex(int index) throws IOException {
    final byte[] jsonData = Files.readAllBytes(Paths.get("C:/Users/320065771/eclipse-workspace/ChatBot/src/main/java/com/philips/caseStudy/ChatBot/domain/question.txt"
        ));

    final ObjectMapper objectMapper = new ObjectMapper();

    final List<Question> questionList = objectMapper.readValue(jsonData, new TypeReference<List<Question>>(){});

    return getRequiredQuestion(questionList,index);
  }


  @Override
  public void createQuestion() throws IOException {
    final ObjectMapper objectMapper = new ObjectMapper();

    final Question newQue = new Question();
    newQue.setQuestionString("New Question");
    newQue.setQuestionId(4);

    final Map<String, String> options = new HashMap<>();
    options.put("1", "Option 1");
    options.put("2", "Option 2");
    newQue.setOptions(options);

    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

    //writing to console, can write to any output stream such as file
    final StringWriter stringQuestion = new StringWriter();
    objectMapper.writeValue(stringQuestion, newQue);
    System.out.println("Question JSON is\n"+stringQuestion);


  }

  @Override
  public Question getRequiredQuestion(List<Question> questionList,int index)
  {
    final ListIterator<Question> questionListIterator=questionList.listIterator();
    while(questionListIterator.hasNext()) {

      final Question currentQuestion=questionListIterator.next();
      if(currentQuestion.getQuestionId()==index) {
        return  currentQuestion;
      }
    }
    return null;
  }

  @Override
  public boolean validate(int index,String answer) throws IOException
  {
    final Question que=getQuestionByIndex(index);
    final Map<String,String> options=que.getOptions();
    return optionPresent(options,answer);
  }

  @Override
  public boolean optionPresent(Map<String,String> options,String answer)
  {
    for (final String key : options.keySet()) {
      if(key.equals(answer)) {
        return true;
      }
    }

    return false;

  }

}
