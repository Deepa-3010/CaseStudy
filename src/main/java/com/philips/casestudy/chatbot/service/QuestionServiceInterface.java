/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.service;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public interface QuestionServiceInterface {

  JSONArray returnQuestion() throws IOException, ParseException;


}
