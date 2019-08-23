/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.consoleUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
//@Component
public class ChatBotView implements ChatBotConsoleUIInterface {

  Scanner sc=new Scanner(System.in);

  @Override
  public String fromKB(String question) {
    System.out.println(question);
    final String answer=sc.nextLine();
    return answer;
  }

  @Override
  public Map <String,String> getUserDetails(){

    final Map<String,String> userDetails=new HashMap<>();

    System.out.println("We need the following details to provide good service");
    final String name=fromKB("May I Know your name ");
    final String contactNo=(fromKB("your contact number "));
    final String email=fromKB("your email id ");
    final String city=fromKB("Your city ");


    userDetails.put("name",name);
    userDetails.put("contactNo",contactNo);
    userDetails.put("email",email);
    userDetails.put("city",city);


    return userDetails;

  }

  public static void main(String args[]) {
    try {


      final ChatBotView view=new ChatBotView();
      final Map<String,String> newUser=view.getUserDetails();
      final String json=new ObjectMapper().writeValueAsString(newUser);

      final URL obj = new URL("http://localhost:8080/api/users");
      final HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
      postConnection.setRequestMethod("POST");
      postConnection.setRequestProperty("Content-Type", "application/json");
      postConnection.setDoOutput(true);
      final OutputStream os = postConnection.getOutputStream();
      os.write(json.getBytes());
      os.flush();
      os.close();
      final int responseCode = postConnection.getResponseCode();
      System.out.println("POST Response Code :  " + responseCode);
      System.out.println("POST Response Message : " + postConnection.getResponseMessage());
      if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
        final BufferedReader in = new BufferedReader(new InputStreamReader(
            postConnection.getInputStream()));
        String inputLine;
        final StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
          response.append(inputLine);
        } in .close();
        // print result
        System.out.println(response.toString());
      } else {
        System.out.println("POST NOT WORKED");
      }


    }catch (final Exception e) {
      System.out.println(e);
    }


  }
}
