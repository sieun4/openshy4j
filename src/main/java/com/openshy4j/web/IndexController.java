package com.openshy4j.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
public class IndexController {


  @GetMapping("/")
  public RedirectView index() {
    return new RedirectView("index");
  }


}
