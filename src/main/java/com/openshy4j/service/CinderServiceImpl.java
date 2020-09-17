package com.openshy4j.service;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinderServiceImpl implements CinderService{
    @Override
    public List<? extends Volume> getVolumes(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        List<? extends Volume> volumes = os.blockStorage().volumes().list();
        return volumes;
    }

    @Override
    public Volume getVolume(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes().get(this.volumeID);
        return volume;
    }

    @Override
    public Volume createVolume(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(this.name)
                        .description(this.description)
                        .size(this.size)
                        .build()
                );
        return volume;
    }

    @Override
    public Volume createBootVolume(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(this.name)
                        .description(this.description)
                        .imageRef(this.imageID)
                        .bootable(true)
                        .build()
                );
        return volume;
    }

    @Override
    public String deleteVolume(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        os.blockStorage().volumes().delete(this.volumeID);
        return "성공적으로 삭제되었습니다.";
    }

    @Override
    public String updateVolume(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        os.blockStorage()
                .volumes()
                .update(this.volumeID, this.name, this.description);
        return "성공적으로 수정되었습니다.";
    }
}
