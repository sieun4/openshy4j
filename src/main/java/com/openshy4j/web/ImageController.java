package com.openshy4j.web;

import com.openshy4j.service.IdentityService;
import com.openshy4j.service.ImageService;
import com.openshy4j.web.dto.ImageCreateRequestDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/images")
public class ImageController {


  private final ImageService imageService;
  private final IdentityService identityService;



  @GetMapping
  public ModelAndView indexImage(ModelAndView modelAndView) {
    String message = "TEST";
    modelAndView.addObject("message", message);
    modelAndView.addObject("listImage", imageService.listImage(
        identityService.getToken()));
    modelAndView.setViewName("/image/index");
    return modelAndView;
  }

  @PostMapping("/createImageRequest")
  public RedirectView createImage(@ModelAttribute("dto") ImageCreateRequestDto dto,
      RedirectAttributes redirectAttributes) throws IOException {
    if(dto.getFile().getName().contains("1")){

    }
    if(dto.getFile().getName().isEmpty()){

    }
    imageService.createImage(identityService.getToken(), dto);
    //ToDo 생성이 되었는지 에 대한 결과가 반환되어야할 것 같음.
    return new RedirectView("/v2/images");
  }

  @GetMapping("/updateImage/{imageId}")
  public ModelAndView goToUpdateImage(@PathVariable String imageId, ModelAndView modelAndView) {
    String message = "UPDATE";
    modelAndView.addObject("message", message);
    modelAndView.addObject("image", imageService.getImage(
        identityService.getToken(), imageId));
    modelAndView.addObject("imageId",imageId);
    modelAndView.setViewName("image/update");

    return modelAndView;
  }

  @PostMapping("/updateImageRequest")
  public RedirectView updateImage(@RequestParam String name, @RequestParam String description,
      @RequestParam String imageId) {
    imageService.updateImage(identityService.getToken(), name, description, imageId);
    return new RedirectView("/v2/images");
  }

  @PostMapping("/deleteImageRequest")
  public RedirectView deleteImage(@RequestParam String imageId) {
    imageService.deleteImage(identityService.getToken(), imageId);
    return new RedirectView("/v2/images");
  }


}
