/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.caseStudy.ChatBot.web;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.philips.caseStudy.ChatBot.consoleUI.ChatBotView;
import com.philips.caseStudy.ChatBot.domain.MonitoringDevice;
import com.philips.caseStudy.ChatBot.domain.UserInfo;
import com.philips.caseStudy.ChatBot.service.ChatBotServiceInterface;
import com.philips.caseStudy.ChatBot.service.QuestionServiceInterface;

@RestController
public class ChatBotController {

  @Autowired
  ChatBotServiceInterface service;

  @Autowired
  QuestionServiceInterface questionService;

  //  @Autowired
  ChatBotView ui=new ChatBotView();

  @RequestMapping(value = "/api/devices",method = RequestMethod.GET)
  public List<MonitoringDevice> getAllDevices(){

    return service.findAll();
  }

  @RequestMapping(value="/api/devices",method=RequestMethod.POST)
  public ResponseEntity<MonitoringDevice> addDevice(@RequestBody MonitoringDevice toBeSaved){

    final int id=service.save(toBeSaved).getId();
    final HttpHeaders headers=new HttpHeaders();
    headers.setLocation(URI.create("/api/devices/"+id));

    return new ResponseEntity<>(headers,HttpStatus.CREATED);

  }


  @RequestMapping(value="/api/users",method=RequestMethod.POST)
  public ResponseEntity<UserInfo> addUsers(@RequestBody UserInfo toBeSaved){

    final int id=service.saveUsers(toBeSaved).getId();
    final HttpHeaders headers=new HttpHeaders();
    return new ResponseEntity<>(headers,HttpStatus.CREATED);
  }

  @RequestMapping(value = "/api/questions/{index}",method = RequestMethod.GET)
  public ResponseEntity<String> getQuestion(@PathVariable("index")int index){
    final HttpHeaders headers=new HttpHeaders();
    final String question=questionService.returnQuestion(index);
    headers.add("question", question);
    return new ResponseEntity<>(headers,HttpStatus.CREATED);
  }


  /*
   * @RequestMapping(value = "/api/introduction",method = RequestMethod.GET) public
   * ResponseEntity<String> introduction(){ final List<String> userDetails=ui.getUserDetails();
   *
   * final UserInfo newUser=new UserInfo(); newUser.setName(userDetails.get(0));
   * newUser.setContactNo(Integer.parseInt(userDetails.get(1)));
   * newUser.setEmail(userDetails.get(2)); newUser.setCity(userDetails.get(3));
   *
   * service.saveUsers(newUser);
   *
   * return new ResponseEntity<>(HttpStatus.CREATED); }
   */


}
