package com.openshy4j.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FlavorDto {

  private String name;
  private int ram;
  private int disk;
  private int vcpu;

  @Builder
  public FlavorDto(String name, int ram, int disk, int vcpu){
    this.name = name;
    this.ram = ram;
    this.disk = disk;
    this.vcpu = vcpu;
  }

}
