package com.openshy4j.service;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolumeServiceImpl implements VolumeService {
    @Override
    public List<? extends Volume> getVolumes(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        List<? extends Volume> volumes = os.blockStorage().volumes().list();
        return volumes;
    }

    @Override
    public Volume getVolume(Token token, String volumeID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes().get(volumeID);
        return volume;
    }

    @Override
    public Volume createVolume(Token token, String name, String description, int size) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(name)
                        .description(description)
                        .size(size)
                        .build()
                );
        return volume;
    }

    @Override
    public Volume createBootVolume(Token token, String name, String description, String imageID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(name)
                        .description(description)
                        .imageRef(imageID)
                        .bootable(true)
                        .build()
                );
        return volume;
    }

    @Override
    public String deleteVolume(Token token, String volumeID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        os.blockStorage().volumes().delete(volumeID);
        return "성공적으로 삭제되었습니다.";
    }

    @Override
    public String updateVolume(Token token, String volumeID, String name, String description) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        os.blockStorage()
                .volumes()
                .update(volumeID, name, description);
        return "성공적으로 수정되었습니다.";
    }
}
