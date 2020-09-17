package com.openshy4j.web;

public class SubnetDto {

  private String name;
  private String networkId;
  private String StartIp;
  private String EndIp;
  private String gateway;
  private String DNSNameServer;
  private String cidr;
  private boolean enableDHCP;

  public boolean isEnableDHCP() {
    return enableDHCP;
  }

  public void setEnableDHCP(boolean enableDHCP) {
    this.enableDHCP = enableDHCP;
  }

  public String getStartIp() {
    return StartIp;
  }

  public void setStartIp(String startIp) {
    StartIp = startIp;
  }

  public String getEndIp() {
    return EndIp;
  }

  public void setEndIp(String endIp) {
    EndIp = endIp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNetworkId() {
    return networkId;
  }

  public void setNetworkId(String networkId) {
    this.networkId = networkId;
  }

  public String getGateway() {
    return gateway;
  }

  public void setGateway(String gateway) {
    this.gateway = gateway;
  }

  public String getDNSNameServer() {
    return DNSNameServer;
  }

  public void setDNSNameServer(String DNSNameServer) {
    this.DNSNameServer = DNSNameServer;
  }

  public String getCidr() {
    return cidr;
  }

  public void setCidr(String cidr) {
    this.cidr = cidr;
  }

}
