package com.openshy4j.web;

import com.openshy4j.service.IdentityServiceImpl;
import com.openshy4j.service.SubnetServiceImpl;
import com.openshy4j.web.dto.SubnetDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.network.Subnet;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subnet")
public class SubnetController {

  private final SubnetServiceImpl subnetServiceImpl;
  private final IdentityServiceImpl identityServiceImpl;

  @GetMapping("/")
  public List<? extends Subnet> getSubnets() {
    return subnetServiceImpl.getSubnets(identityServiceImpl.getToken());
  }

  @GetMapping("/{id}")
  public Subnet getSubnet(@PathVariable String id) {
    return subnetServiceImpl.getSubnet(id, identityServiceImpl.getToken());
  }

  @PostMapping("/create")
  public Subnet createSubnet(@RequestBody SubnetDto subnetDto) {
    return subnetServiceImpl.createSubnet(subnetDto, identityServiceImpl.getToken());
  }

  @PutMapping("/{id}")
  public Subnet updateSubnet(@PathVariable String id, @RequestBody SubnetDto subnetDto) {
    return subnetServiceImpl.updateSubnet(id, subnetDto, identityServiceImpl.getToken());
  }

  @DeleteMapping("/{id}")
  public void deleteSubnet(@PathVariable String id) {
    subnetServiceImpl.deleteSubnet(id, identityServiceImpl.getToken());
  }
}
