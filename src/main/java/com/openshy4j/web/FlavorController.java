package com.openshy4j.web;

import com.openshy4j.service.FlavorService;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.compute.Flavor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flavor")
public class FlavorController {

  private final TokenController os;
  private final FlavorService flavorService;

  @GetMapping("/")
  public List<? extends Flavor> list() {
    return flavorService.findAll(os.getOSClient());
  }

  @PostMapping("/")
  public Flavor create() {

    String name = "flavor3";
    int ram = 1024;
    int disk = 10;
    int vcpus = 1;

    HashMap<String, Object> values = new HashMap<String, Object>();
    values.put("name", name);
    values.put("ram", ram);
    values.put("disk", disk);
    values.put("vcpus", vcpus);

    return flavorService.create(os.getOSClient(), values);
  }

  @GetMapping("/{id}")
  public Flavor findById(@PathVariable String id){
    return flavorService.findById(os.getOSClient(), id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id){
    flavorService.delete(os.getOSClient(), id);
  }

  @PutMapping("/{id}")
  public Flavor update(@PathVariable String id) {
    return flavorService.update(os.getOSClient(), id);
  }
  /*
  @GetMapping("/flavors")
  public ModelAndView findAll() {
    ModelAndView mv = new ModelAndView();
    List<? extends Flavor> flavors = flavorService.findAll();
    mv.addObject("flavors", flavors);
    mv.setViewName("flavor/list");
    return mv;
  }
  */

}