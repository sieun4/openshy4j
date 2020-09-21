package com.openshy4j.service;

import com.openshy4j.web.Dto.FlavorDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FlavorServiceImpl implements FlavorService {

  @Override
  public List<? extends Flavor> getFlavors(Token token) {

    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);

    return osClientV3.compute().flavors().list();
  }

  @Override
  public Flavor create(Token token, FlavorDto flavorDto) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    Flavor flavor = Builders.flavor()
        .name(flavorDto.getName())
        .ram(flavorDto.getRam())
        .disk(flavorDto.getDisk())
        .vcpus(flavorDto.getVcpu())
        .build();

    return osClientV3.compute().flavors().create(flavor);
  }

  @Override
  public Flavor getFlavor(Token token, String flavorId) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.compute().flavors().get(flavorId);
  }

  @Override
  public void delete(Token token, String flavorId) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    osClientV3.compute().flavors().delete(flavorId);
  }

}