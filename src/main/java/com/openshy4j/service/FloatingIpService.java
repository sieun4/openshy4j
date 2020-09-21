package com.openshy4j.service;

import java.util.List;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.compute.FloatingIP;

public interface FloatingIpService {

  public List<? extends FloatingIP> getFloatingIps(Token token);

  public FloatingIP create(Token token, String pool);

  public void deleteFloatingIp(Token token, String id);

  public NetFloatingIP associateToPort(Token token, String id, String portId);

}
