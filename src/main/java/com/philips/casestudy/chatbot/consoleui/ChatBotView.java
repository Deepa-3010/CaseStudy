/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */

package com.philips.casestudy.chatbot.consoleui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ChatBotView implements ChatBotConsoleUIInterface {

  Map<Integer, Integer> userAnswers = new HashMap<>();
  Scanner sc = new Scanner(System.in);

  @Override
  public String fromKB(String question) {
    System.out.println(question);
    return sc.nextLine();
  }

  public Logger loggerHandler() {
    final LogManager lgmngr = LogManager.getLogManager();

    final Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    return log;
  }

  @Override
  public Map<String, String> getUserDetails() {


    final Map<String, String> userDetails = new HashMap<>();

    final Logger log=loggerHandler();

    log.log(Level.ALL,"We need the following details to provide good service");
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

  public int askQuestion(int index) {
    int responseCode = 400;

    try {
      final URL urlForGetRequest = new URL("http://localhost:8080/api/questions/" + index);
      String readLine = null;
      final HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      responseCode = conection.getResponseCode();
      final String question = conection.getHeaderField("que");
      if (responseCode == HttpURLConnection.HTTP_OK) {
        final BufferedReader in =
            new BufferedReader(new InputStreamReader(conection.getInputStream()));
        final StringBuilder response = new StringBuilder();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();
        final String answer = fromKB(question);
        userAnswers.put(index, Integer.parseInt(answer));
      } else {
        System.out.println("GET NOT WORKED");
      }


    } catch (final Exception e) {
      System.out.println(e);
    }
    return responseCode;

  }

  public void startChatBot() {

    try {
      final Map<String,String> newUser=getUserDetails();
      final String json=new ObjectMapper().writeValueAsString(newUser);

      final URL obj = new URL("http://localhost:8080/api/users"); final HttpURLConnection
      postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST"); postConnection.setRequestProperty("Content-Type","application/json"); postConnection.setDoOutput(true); final OutputStream os =
          postConnection.getOutputStream(); os.write(json.getBytes()); os.flush(); os.close(); final
          int responseCode = postConnection.getResponseCode();
          System.out.println("POST Response Code :  " + responseCode);
          System.out.println("POST Response Message : " + postConnection.getResponseMessage());
          if(responseCode == HttpURLConnection.HTTP_CREATED) { //success
            final BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            String inputLine;
            final StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) { response.append(inputLine); } in .close(); // print result
            System.out.println(response.toString());

          }
          else {
            System.out.println("POST NOT WORKED");
          }


    }catch (final Exception e) { System.out.println(e); }

  }


  public void startQuestion() {
    int index = -1;
    int responseCode = 0;
    do {
      index++;
      responseCode = askQuestion(index);
    } while (responseCode != 400);

  }

  public static void main(String []args) {


    final ChatBotView view = new ChatBotView();

    view.startChatBot();
    view.startQuestion();

  }
}