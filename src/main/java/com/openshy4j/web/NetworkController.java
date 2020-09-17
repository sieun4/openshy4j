package com.openshy4j.web;

import com.openshy4j.service.IdentityServiceImpl;
import com.openshy4j.service.NetworkServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.network.Network;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/network")
public class NetworkController {

  private final NetworkServiceImpl networkServiceImpl;
  private final IdentityServiceImpl identityServiceImpl;

  @GetMapping("/")
  public List<? extends Network> getNetworks() {
    return networkServiceImpl.getNetworks(identityServiceImpl.getToken());
  }

  @GetMapping("/{id}")
  public Network getNetwork(@RequestParam String id) {
    return networkServiceImpl.getNetwork(id, identityServiceImpl.getToken());
  }

  @PostMapping("/create")
  public Network createNetwork(@RequestBody NetworkDto networkDto) {
    return networkServiceImpl.createNetwork(networkDto, identityServiceImpl.getToken());
  }

  @PutMapping("/{id}")
  public Network updateNetwork(@PathVariable String id, @RequestBody NetworkDto networkDto) {
    return networkServiceImpl.updateNetwork(id, networkDto, identityServiceImpl.getToken());
  }

  @DeleteMapping("/{id}")
  public void deleteNetwork(@PathVariable String id) {
    networkServiceImpl.deleteNetwork(id, identityServiceImpl.getToken());
  }
}

