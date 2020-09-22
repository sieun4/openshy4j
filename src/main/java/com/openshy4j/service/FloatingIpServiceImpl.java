package com.openshy4j.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FloatingIpServiceImpl implements FloatingIpService{

  @Override
  public List<? extends FloatingIP> getFloatingIps(Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.compute().floatingIps().list();
  }

  @Override
  public FloatingIP create(Token token, String pool) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.compute().floatingIps().allocateIP(pool).toBuilder().build();
  }

  @Override
  public void deleteFloatingIp(Token token, String id) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    osClientV3.compute().floatingIps().deallocateIP(id);
  }

  @Override
  public NetFloatingIP associateToPort(Token token, String id, String portId) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().floatingip().associateToPort(id, portId);
  }

}
