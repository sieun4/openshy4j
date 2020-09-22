package com.openshy4j.service;

import static org.openstack4j.api.OSClient.OSClientV3;
import com.openshy4j.web.dto.NetworkDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkUpdate;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NetworkServiceImpl implements NetworkService {

  @Override
  public List<? extends Network> getNetworks(Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().network().list();
  }

  @Override
  public Network getNetwork(String networkId, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().network().get(networkId);
  }

  @Override
  public Network createNetwork(NetworkDto networkDto, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    return osClientV3.networking().network().create(Builders.network()
        .name(networkDto.getName())
        .isShared(networkDto.isShared())
        .networkType(networkDto.getNetworkType())
        .isRouterExternal(networkDto.isExternalRouter())
        .adminStateUp(true)
        .build());
  }

  @Override
  public Network updateNetwork(String networkId, NetworkDto networkDto, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    NetworkUpdate networkUpdate = Builders.networkUpdate().name(networkDto.getName()).shared(
        networkDto.isShared()).build();
    return osClientV3.networking().network().update(networkId, networkUpdate);
  }

  @Override
  public void deleteNetwork(String networkId, Token token) {
    OSClientV3 osClientV3 = OSFactory.clientFromToken(token);
    osClientV3.networking().network().delete(networkId);
  }
}
