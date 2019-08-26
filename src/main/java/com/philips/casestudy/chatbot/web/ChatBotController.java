/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.web;

import java.net.URI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.chatbot.consoleui.ChatBotView;
import com.philips.casestudy.chatbot.domain.MonitoringDevice;
import com.philips.casestudy.chatbot.domain.MonitoringDeviceDTO;
import com.philips.casestudy.chatbot.domain.UserInfo;
import com.philips.casestudy.chatbot.domain.UserInfoDTO;
import com.philips.casestudy.chatbot.service.ChatBotServiceInterface;
import com.philips.casestudy.chatbot.service.QuestionServiceInterface;

@RestController
public class ChatBotController {

  @Autowired
  ChatBotServiceInterface service;

  @Autowired
  QuestionServiceInterface questionService;

  ChatBotView ui=new ChatBotView();


  @PostMapping("/api/users")
  public ResponseEntity<UserInfoDTO> addUsers(@RequestBody UserInfoDTO toBeSaved){

    final ModelMapper model=new ModelMapper();
    final UserInfo user=model.map(toBeSaved,UserInfo.class);
    final int id=service.saveUsers(user).getId();
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
    final ModelMapper model=new ModelMapper();
    final MonitoringDevice device=model.map(toBeSaved,MonitoringDevice.class);
    final int id=service.save(device).getId();
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

  //  }

  @GetMapping("/api/questions")
  public void getQuestion(){
    //    final HttpHeaders headers=new HttpHeaders();
    try {
      final JSONArray question= questionService.returnQuestion();
      // headers.add("que",question);
      for(int i = 0; i < question.size();i++) {
        final JSONObject innerObj = (JSONObject) question.get(i);
        System.out.println(innerObj);
      }

    }catch(final Exception e) {
      System.out.println("Sorry");
    }


  }
}

