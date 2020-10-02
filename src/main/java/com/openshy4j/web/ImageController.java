package com.openshy4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openshy4j.service.IdentityService;
import com.openshy4j.service.ImageService;
import com.openshy4j.web.dto.ImageCreateRequestDto;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.image.v2.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/images")
public class ImageController {


  private final ImageService imageService;
  private final IdentityService identityService;


  @GetMapping
  public ModelAndView indexImage(ModelAndView modelAndView) {
    modelAndView.addObject("listImage", imageService.listImage(
        identityService.getToken()));
    modelAndView.setViewName("/image/index");
    return modelAndView;
  }

  @GetMapping("/create")
  public ModelAndView createPage(ModelAndView modelAndView) {
    modelAndView.setViewName("/image/create");
    return modelAndView;
  }

  @GetMapping("/update/{imageId}")
  public ModelAndView updatePage(@PathVariable String imageId, ModelAndView modelAndView) {
    Image image = imageService.getImage(
        identityService.getToken(), imageId);
    String imageName = image.getName();
    modelAndView.addObject("imageName", imageName);
    modelAndView.addObject("image", image);
    modelAndView.addObject("imageId", imageId);
    modelAndView.setViewName("image/update");

    return modelAndView;
  }

  @PostMapping
  public RedirectView createImage(@ModelAttribute("dto") ImageCreateRequestDto dto)
      throws IOException {
    imageService.createImage(identityService.getToken(), dto);
    //ToDo 생성이 되었는지 에 대한 결과가 반환되어야할 것 같음.
    return new RedirectView("/v2/images");
  }


  @PutMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateImage(@RequestBody String data) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String,String> convertedData = objectMapper.readValue(data, Map.class);
    System.out.printf("pringting data %s",data);
    String name = convertedData.get("name");
    String description = convertedData.get("description");
    String imageId = convertedData.get("imageId");
    System.out.printf("pringting convertedData name %s, desc %s, id %s",name,description,imageId);
    imageService.updateImage(identityService.getToken(), name, description, imageId);
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteImage(@RequestBody String data) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String,String> convertedData = objectMapper.readValue(data, Map.class);
    String imageId = convertedData.get("imageId");
    System.out.printf("received imageId is %s", imageId);
    imageService.deleteImage(identityService.getToken(),imageId);
  }


}
