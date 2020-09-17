package com.openshy4j.web;

import org.openstack4j.model.network.NetworkType;

public class NetworkDto {

  private boolean isShared;
  private NetworkType networkType;
  private String name;
  private boolean externalRouter;

  public boolean isShared() {
    return isShared;
  }

  public void setShared(boolean shared) {
    isShared = shared;
  }

  public NetworkType getNetworkType() {
    return networkType;
  }

  public void setNetworkType(NetworkType networkType) {
    this.networkType = networkType;
  }

  public boolean isExternalRouter() {
    return externalRouter;
  }

  public void setExternalRouter(boolean externalRouter) {
    this.externalRouter = externalRouter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
