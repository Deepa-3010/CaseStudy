/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO {

  private JSONObject parseQuestionObject(JSONObject question)
  {
    return (JSONObject) question.get("question");

  }

  public JSONArray getQuestion() throws IOException, ParseException {

    final JSONParser jsonParser = new JSONParser();



    final FileReader reader = new FileReader("C:/Users/320065420/BootCamp/CaseStudyAfterLinting/ChatBotAfterFirstLinting/src/main/java/com/philips/casestudy/chatbot/domain/question.json");
    final Object obj = jsonParser.parse(reader);
    final JSONArray questionArray= (JSONArray) obj;
    System.out.println(questionArray);

    //Iterate over employee array
    questionArray.forEach( que -> parseQuestionObject( (JSONObject) que ) );
    return questionArray;




  }


}
