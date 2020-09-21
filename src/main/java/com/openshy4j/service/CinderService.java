package com.openshy4j.service;

import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.Volume;

import java.util.List;

public interface CinderService {
    List<? extends Volume> getVolumes(Token token);
    Volume getVolume(Token token, String volumeID);
    Volume createVolume(Token token, String name, String description, int size);
    Volume createBootVolume(Token token, String name, String description, String imageID);
    String deleteVolume(Token token, String volumeID);
    String updateVolume(Token token, String volumeID, String name, String description);
}
