package com.openshy4j.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping(value = {"/my"})
@RestController
public class FlavorController {
  @GetMapping("flavors")
  public ModelAndView go( ){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/flavor/list");
    return mv;
  }
}
