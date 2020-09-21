package com.openshy4j.web;

import com.openshy4j.service.FlavorService;
import com.openshy4j.service.IdentityService;
import com.openshy4j.web.Dto.FlavorDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.compute.Flavor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flavor")
public class FlavorController {

  private final IdentityService identityService;
  private final FlavorService flavorService;

  @GetMapping("/")
  public List<? extends Flavor> getFlavors() {
    return flavorService.getFlavors(identityService.getToken());
  }

  @PostMapping("/")
  public Flavor create(@RequestBody FlavorDto flavorDto) {
    return flavorService.create(identityService.getToken(), flavorDto);
  }

  @GetMapping("/{id}")
  public Flavor getFlavor(@PathVariable String id){
    return flavorService.getFlavor(identityService.getToken(), id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id){
    flavorService.delete(identityService.getToken(), id);
  }

}