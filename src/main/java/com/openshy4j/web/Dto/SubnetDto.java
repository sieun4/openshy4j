package com.openshy4j.web.dto;

import lombok.*;

@Getter
@Setter
public class SubnetDto {

  private String name;
  private String networkId;
  private String StartIp;
  private String EndIp;
  private String gateway;
  private String DNSNameServer;
  private String cidr;
  private boolean enableDHCP;

}
