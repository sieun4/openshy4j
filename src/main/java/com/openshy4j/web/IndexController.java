package com.openshy4j.web;

import com.openshy4j.service.IdentityService;
import com.openshy4j.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class IndexController {



  @GetMapping("/")
  public String index() {
    return "index";
  }






}
