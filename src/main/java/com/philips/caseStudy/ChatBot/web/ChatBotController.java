/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.web;

import java.io.IOException;
import java.net.URI;
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
  public ResponseEntity<UserInfoDTO> addUsers(@RequestBody UserInfoDTO toBeSaved){
    final int id=service.saveUsers(toBeSaved).getId();
    if(id!=0) {
      final HttpHeaders headers=new HttpHeaders();
      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/api/devices")
  public ResponseEntity<MonitoringDeviceDTO> addDevices (@RequestBody MonitoringDeviceDTO toBeSaved){
    final int id=service.save(toBeSaved).getId();
    final HttpHeaders headers1=new HttpHeaders();
    headers1.setLocation(URI.create("/api/devices/"+id));

    return new ResponseEntity<>(headers1,HttpStatus.CREATED);
  }


  //  @GetMapping("/api/questions/{index}")
  //  public ResponseEntity<String> getQuestion(@PathVariable("index")int index){
  //    final HttpHeaders headers=new HttpHeaders();
  //    try {
  //      final String question= questionService.returnQuestion(index);
  //      headers.add("que",question);
  //      return new ResponseEntity<>(headers,HttpStatus.OK);
  //    }catch(final IndexOutOfBoundsException e) {
  //      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  //    }

  @GetMapping("/api/questions/{index}")
  public ResponseEntity<Question> getQuestionByIndex(@PathVariable("index") int index) throws IOException{
    System.out.println("In api :");
    final Question question=questionService.returnQuestion(index);
    if(question!=null) {
      System.out.println("Question ! nulll");
      return new ResponseEntity<>(question,HttpStatus.OK);
    }
    System.out.println("Question null");
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

  }


}





