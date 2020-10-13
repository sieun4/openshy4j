package com.openshy4j.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerDto {
    private String name;
    private String flavorId;
    private String imageId;
    private String volumeId;
    private String networkId;
    private String serverId;
    private String action;
}
