package com.openshy4j.service;

import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Flavor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("flavorService")
public class FlavorServiceImpl implements FlavorService {

  @Override
  public List<? extends Flavor> findAll(OSClientV3 token) {
    return token.compute().flavors().list();
  }

  @Override
  public Flavor create(OSClientV3 token, HashMap<String, Object> values) {
    org.openstack4j.model.compute.Flavor flavor = Builders.flavor()
        .name((String) values.get("name"))
        .ram((Integer) values.get("ram"))
        .disk((Integer) values.get("disk"))
        .vcpus((Integer) values.get("vcpus"))
        .build();

    return token.compute().flavors().create(flavor);
  }

  @Override
  public Flavor findById(OSClientV3 token, String flavorId) {
    return token.compute().flavors().get(flavorId);
  }

  @Override
  public void delete(OSClientV3 token, String flavorId) {
    token.compute().flavors().delete(flavorId);
  }

  @Override
  public Flavor update(OSClientV3 osClient, String id) {
    return null;
  }

}