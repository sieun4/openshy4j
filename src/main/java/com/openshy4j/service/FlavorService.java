package com.openshy4j.service;

import com.openshy4j.web.Dto.FlavorDto;
import java.util.List;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.identity.v3.Token;

public interface FlavorService {

  public List<? extends Flavor> getFlavors(Token token);

  public Flavor create(Token token, FlavorDto flavorDto);

  public Flavor getFlavor(Token token, String flavorId);

  public void delete(Token token, String flavorId);

}