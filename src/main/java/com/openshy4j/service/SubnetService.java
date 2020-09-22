package com.openshy4j.service;

import com.openshy4j.web.dto.SubnetDto;
import java.util.List;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Subnet;

public interface SubnetService {
  List<? extends Subnet> getSubnets(Token token);
  Subnet getSubnet(String subnetId, Token token);
  Subnet createSubnet(SubnetDto subnetDto, Token token);
  Subnet updateSubnet(String subnetId, SubnetDto subnetDto,Token token);
  void deleteSubnet(String subnetId, Token token);

}
