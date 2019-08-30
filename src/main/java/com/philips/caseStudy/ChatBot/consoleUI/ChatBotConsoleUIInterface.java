/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.consoleUI;

import java.util.Map;


public interface ChatBotConsoleUIInterface {

  String fromKB(String question);

  Map<String,String> getUserDetails();

}
