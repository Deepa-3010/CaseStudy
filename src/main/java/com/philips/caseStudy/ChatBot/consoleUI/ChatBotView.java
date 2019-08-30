/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */

package com.philips.caseStudy.ChatBot.consoleUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.caseStudy.ChatBot.domain.Question;
import com.philips.caseStudy.ChatBot.dto.AnswerDTO;

public class ChatBotView  {

  static List<String> userAnswers=new ArrayList<>();
  static Scanner sc = new Scanner(System.in);

  public static String fromKB(String question) {
    System.out.println(question);
    return sc.nextLine();
  }

  public static Map<String, String> getUserDetails() {

    final Map<String, String> userDetails = new HashMap<>();

    System.out.println("");
    final String name = fromKB("May I Know your name ");
    final String contactNo = (fromKB("your contact number "));
    final String email = fromKB("your email id ");
    final String city = fromKB("Your city ");


    userDetails.put("name", name);
    userDetails.put("contactNo", contactNo);
    userDetails.put("email", email);
    userDetails.put("city", city);


    return userDetails;

  }

  public static int askQuestion(int index) {
    int responseCode=400;
    try {
      final URL urlForGetRequest = new URL("http://localhost:8080/api/questions/" + index);
      String readLine = null;
      final HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      responseCode = conection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {

        final BufferedReader in =
            new BufferedReader(new InputStreamReader(conection.getInputStream()));
        final StringBuilder response = new StringBuilder();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();


        final ObjectMapper objectMapper =new ObjectMapper();
        final Question question=objectMapper.readValue(response.toString(), Question.class);
        final String answer=fromKB(question.getQuestionString()+"\n"+question.getOptions());
        final boolean IfAnswer=validate(question.getQuestionId(),answer);

        if(!IfAnswer) {
          askQuestion(index);
        }

      }

    } catch (final Exception e) {
      System.out.println(e);
    }
    return responseCode;

  }

  public static boolean validate(int questionId,String answer) throws IOException
  {

    try {
      final URL urlForGetRequest = new URL("http://localhost:8080/api/questions/" + questionId+"/"+answer);
      final HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      final int responseCode = conection.getResponseCode();


      if (responseCode == HttpURLConnection.HTTP_OK) {
        userAnswers.add(answer);
        return true;
      }
      else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST){
        System.out.println(conection.getHeaderField("ErrorMessage"));
        return false;
      }
      else {
        System.out.println("This is not because of bad request ");
        return false;
      }
    } catch (final Exception e) {
      System.out.println(e);
      return false;
    }


  }

  public static void startChatBot() {
    try {
      userAnswers.clear();

      final Map<String,String> newUser=getUserDetails();
      final String json=new ObjectMapper().writeValueAsString(newUser);

      final URL obj = new URL("http://localhost:8080/api/users");
      final HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST");
      postConnection.setRequestProperty("Content-Type","application/json");
      postConnection.setDoOutput(true); final OutputStream os =postConnection.getOutputStream();
      os.write(json.getBytes());
      os.flush();
      os.close();
      final int responseCode = postConnection.getResponseCode();
      if(responseCode == HttpURLConnection.HTTP_CREATED) { //success
        System.out.println("Thank You for registering with us! ");
      }
      else {
        System.out.println("You have already registered! Please Continue");
      }


    }catch (final Exception e) { System.out.println(e); }

  }
  public static void terminateChatBot()
  {
    userAnswers.clear();
    askQuestion(0);
    switch(userAnswers.get(0))
    {
      case "1":
        System.out.println("Thank you");
        break;
      case "2":
        startQuestion();
        mapUserInputToParametersOfDevicesTouch();
        mapUserInputToParametersOfDevicesScreenSize();
        final AnswerDTO answerSet=new AnswerDTO(userAnswers);
        getDevices(answerSet);

        break;
    }

  }

  public static void startQuestion() {
    userAnswers.clear();

    int index = 0;
    int responseCode = 0;
    do {
      index++;
      responseCode = askQuestion(index);
    } while (responseCode != 400);

  }

  public static void mapUserInputToParametersOfDevicesTouch()
  {

    switch(userAnswers.get(0))
    {
      case "1":
        userAnswers.set(0,"touch");
        break;
      case "2":
        userAnswers.set(0,"nontouch");
        break;
      case "3":
        userAnswers.set(0,null);
        break;

    }
  }

  public static void mapUserInputToParametersOfDevicesScreenSize()
  {
    switch(userAnswers.get(1))
    {
      case "1":
        userAnswers.set(1,null);
        break;
      case "9":
        userAnswers.set(1,"9");
        break;
      case "10":
        userAnswers.set(1,"10");
        break;
      case "12":
        userAnswers.set(1,"12");
        break;
      case "14":
        userAnswers.set(1,"14");
        break;
      case "15":
        userAnswers.set(1,"15");
        break;
    }
  }



  public static void getDevices(AnswerDTO answerSet) {

    try {
      final String json=new ObjectMapper().writeValueAsString(answerSet);
      final URL obj = new URL("http://localhost:8080/api/getDevices");
      final HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST");
      postConnection.setRequestProperty("Content-Type","application/json");
      postConnection.setDoOutput(true);
      final OutputStream os =postConnection.getOutputStream();
      os.write(json.getBytes());
      os.flush();
      os.close();
      final int responseCode = postConnection.getResponseCode();
      if(responseCode == HttpURLConnection.HTTP_OK) { //success
        final BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
        String inputLine;
        final StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in .close();
        System.out.println(response.toString());

      }
      else {
        System.out.println("This device is not present try again");

      }

      terminateChatBot();

    }catch (final Exception e) { System.out.println(e); }

  }



  public static void main(String []args) throws IOException {

    startChatBot();
    startQuestion();
    mapUserInputToParametersOfDevicesTouch();
    mapUserInputToParametersOfDevicesScreenSize();
    final AnswerDTO answerSet=new AnswerDTO(userAnswers);
    getDevices(answerSet);

  }


}