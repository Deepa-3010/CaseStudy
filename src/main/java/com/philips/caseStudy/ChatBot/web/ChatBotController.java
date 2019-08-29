/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.web;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.caseStudy.ChatBot.consoleUI.ChatBotView;
import com.philips.caseStudy.ChatBot.domain.Question;
import com.philips.caseStudy.ChatBot.dto.MonitoringDeviceDTO;
import com.philips.caseStudy.ChatBot.dto.UserInfoDTO;
import com.philips.caseStudy.ChatBot.service.ChatBotServiceInterface;
import com.philips.caseStudy.ChatBot.service.QuestionServiceInterface;

@RestController
public class ChatBotController {

  @Autowired
  ChatBotServiceInterface service;

  @Autowired
  QuestionServiceInterface questionService;

  ChatBotView ui=new ChatBotView();


  @PostMapping("/api/users")
  public ResponseEntity<String> addUsers(@RequestBody UserInfoDTO toBeSaved){
    final int id=service.saveUsers(toBeSaved).getId();
    if(id!=0) {

      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/api/devices")
  public ResponseEntity<String> addDevices (@RequestBody MonitoringDeviceDTO toBeSaved){
    final int id=service.save(toBeSaved).getId();

    if(id!=0) {

      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }



  @GetMapping("/api/questions/{index}")
  public ResponseEntity<Question> getQuestionByIndex(@PathVariable("index") int index) throws IOException{
    final Question question=questionService.returnQuestion(index);
    if(question!=null) {
      return new ResponseEntity<>(question,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

  }

  @GetMapping("/api/questions/{index}/{answer}")
  public ResponseEntity<String> getQuestionByIndex(@PathVariable("index") int index,@PathVariable("answer") String answer) throws IOException{
    final boolean ifOptionPresent=questionService.validate(index, answer);
    final HttpHeaders headers=new HttpHeaders();
    headers.add("ErrorMessage","Please enter among the correct options");
    if(ifOptionPresent) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);

  }

}





