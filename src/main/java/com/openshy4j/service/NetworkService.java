package com.openshy4j.service;

import com.openshy4j.web.dto.NetworkDto;
import java.util.List;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Network;

public interface NetworkService {
  List<? extends Network> getNetworks(Token token);
  Network getNetwork(String networkId, Token token);
  Network createNetwork(NetworkDto networkDto, Token token);
  Network updateNetwork(String networkId, NetworkDto networkDto,Token token);
  void deleteNetwork(String networkId, Token token);
}
