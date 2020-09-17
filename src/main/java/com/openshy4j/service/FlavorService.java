package com.openshy4j.service;

import java.util.HashMap;
import java.util.List;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Flavor;

public interface FlavorService {

  public List<? extends Flavor> findAll(OSClient.OSClientV3 token);

  public Flavor create(OSClient.OSClientV3 token, HashMap<String, Object> values);

  public Flavor findById(OSClient.OSClientV3 token, String flavorId);

  public void delete(OSClient.OSClientV3 token, String flavorId);

  public Flavor update(OSClientV3 osClient, String id);

}