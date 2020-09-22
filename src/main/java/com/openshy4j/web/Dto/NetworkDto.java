package com.openshy4j.web.dto;

import org.openstack4j.model.network.NetworkType;

import lombok.*;

@Getter
@Setter
@ToString
public class NetworkDto {

  private String name;
  private boolean isShared;
  private NetworkType networkType;
  private boolean externalRouter;

}
