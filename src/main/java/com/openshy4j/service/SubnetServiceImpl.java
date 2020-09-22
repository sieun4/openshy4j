package com.openshy4j.service;

import com.openshy4j.web.dto.SubnetDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

@Service
public class SubnetServiceImpl implements SubnetService {

  @Override
  public List<? extends Subnet> getSubnets(Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().subnet().list();
  }

  @Override
  public Subnet getSubnet(String subnetId, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().subnet().get(subnetId);
  }

  @Override
  public Subnet createSubnet(SubnetDto subnetDto, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().subnet().create(Builders.subnet()
        .name(subnetDto.getName())
        .networkId(subnetDto.getNetworkId())
        .addPool(subnetDto.getStartIp(), subnetDto.getEndIp())
        .gateway(subnetDto.getGateway())
        .addDNSNameServer(subnetDto.getDNSNameServer())
        .cidr(subnetDto.getCidr())
        .ipVersion(IPVersionType.V4)
        .build());
  }

  @Override
  public Subnet updateSubnet(String subnetId, SubnetDto subnetDto, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    Subnet updatedSubnet = Builders.subnet().build().toBuilder()
        .addDNSNameServer(subnetDto.getDNSNameServer())
        .addPool(subnetDto.getStartIp(), subnetDto.getEndIp())
        .cidr(subnetDto.getCidr())
        .enableDHCP(subnetDto.isEnableDHCP())
        .networkId(subnetDto.getNetworkId())
        .build();
    return osClientV3.networking().subnet().update(subnetId, updatedSubnet);
  }

  @Override
  public void deleteSubnet(String subnetId, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    osClientV3.networking().subnet().delete(subnetId);
  }
}
