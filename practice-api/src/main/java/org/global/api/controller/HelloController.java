package org.global.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.global.api.annotation.ElDescription;
import org.global.api.annotation.ElService;

@RestController
@RequestMapping("/api/global")
public class HelloController {

  @GetMapping("/hello")
  @ElService(key = "/api/global/hello")
  @ElDescription(sub = "Test Hello", desc = "API đầu tiên")
  public Map<String, Object> sayHello(){
    Map<String, Object> response = new HashMap<>();
    response.put("message", " Hello World");
    return response;
  }
}
