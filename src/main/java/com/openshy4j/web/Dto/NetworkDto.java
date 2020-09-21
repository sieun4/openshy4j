package com.openshy4j.web.Dto;

import org.openstack4j.model.network.NetworkType;

import lombok.*;

@Getter
@Setter
public class NetworkDto {

  private String name;
  private boolean isShared;
  private NetworkType networkType;
  private boolean externalRouter;

}
