/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.caseStudy.ChatBot.consoleUI.ChatBotView;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.domain.Question;
import com.philips.caseStudy.ChatBot.dto.AnswerDTO;
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
  public ResponseEntity<String> validateAnswer(@PathVariable("index") int index,@PathVariable("answer") String answer) throws IOException{
    final boolean ifOptionPresent=questionService.validate(index, answer);
    final HttpHeaders headers=new HttpHeaders();
    headers.add("ErrorMessage","Please enter among the correct options");
    if(ifOptionPresent) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
  }


  @PostMapping("/api/getDevices")
  public ResponseEntity<String> getDevices(@RequestBody AnswerDTO answerSet) throws JsonGenerationException, JsonMappingException, IOException{
    final List<MonitoringDevice> devices;
    if(answerSet.getUserAnswer().get(0)==null && answerSet.getUserAnswer().get(1)==null )
    {
      devices=service.findAll();
    }

    else if(answerSet.getUserAnswer().get(0)==null && answerSet.getUserAnswer().get(1)!=null )
    {
      devices=service.findByUserChoiceByScreenSizeOnly(Float.parseFloat(answerSet.getUserAnswer().get(1)));
    }

    else if(answerSet.getUserAnswer().get(0)!=null && answerSet.getUserAnswer().get(1)==null )
    {
      devices=service.findByUserChoiceByTouchOnly(answerSet.getUserAnswer().get(0));
    }

    else
    {
      devices=service.findByUserChoiceByBothTouchAndScreenSize(answerSet.getUserAnswer().get(0),Float.parseFloat(answerSet.getUserAnswer().get(1)));
    }
    if(devices.isEmpty()) {
      return new ResponseEntity<>("There are no such devices try again",HttpStatus.BAD_REQUEST);
    } else
    {
      final String jsonData=listToJsonConversion(devices);

      return new ResponseEntity<>(jsonData,HttpStatus.OK);
    }
  }

  public String listToJsonConversion(List<MonitoringDevice> devices) throws JsonGenerationException, JsonMappingException, IOException
  {
    final ByteArrayOutputStream out=new ByteArrayOutputStream();
    final ObjectMapper mapper=new ObjectMapper();
    mapper.writeValue(out, devices);

    final byte[] jsonData=out.toByteArray();
    return (new String(jsonData));

  }

}





