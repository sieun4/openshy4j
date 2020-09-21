package com.openshy4j.web;

import com.openshy4j.service.FloatingIpService;
import com.openshy4j.service.IdentityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.network.NetFloatingIP;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/floating-ip")
public class FloatingIpController {

  private final IdentityService identityService;
  private final FloatingIpService floatingIpService;

  @GetMapping("/")
  public List<? extends FloatingIP> getFloatingIps() {
    return floatingIpService.getFloatingIps(identityService.getToken());
  }

  @PostMapping("/{pool}")
  public FloatingIP create(@PathVariable String pool) {
    return floatingIpService.create(identityService.getToken(), pool);
  }

  @DeleteMapping("/{id}")
  public void deleteFloatingIp(@PathVariable String id){
    floatingIpService.deleteFloatingIp(identityService.getToken(), id);
  }

  @PostMapping("/associateToPort")
  public NetFloatingIP associateToPort(@PathVariable String id, String portId){
    return floatingIpService.associateToPort(identityService.getToken(), id, portId);
  }

}
