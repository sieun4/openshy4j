package com.openshy4j.service;

import lombok.Getter;
import lombok.Setter;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.Volume;

import java.util.List;

public interface CinderService {
    String name = null;
    String description = null;
    String imageID = null;
    String volumeID = null;
    int size = 10;


    List<? extends Volume> getVolumes(Token token);
    Volume getVolume(Token token);
    Volume createVolume(Token token);
    Volume createBootVolume(Token token);
    String deleteVolume(Token token);
    String updateVolume(Token token);

}
