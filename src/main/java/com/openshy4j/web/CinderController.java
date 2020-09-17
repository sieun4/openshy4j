package com.openshy4j.web;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Setter
@Getter
class Cinder {
    String name;
    String description;
    int size;
    String imageID;
}

@RequiredArgsConstructor
@RestController
public class CinderController {



    public OSClient.OSClientV3 createToken(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        return os;
    }

    @GetMapping("/cinder")
    public List<? extends Volume> getVolumes() {
        OSClient.OSClientV3 os = createToken();
        List<? extends Volume> volumes = os.blockStorage().volumes().list();
        return volumes;
    }

    @GetMapping("/cinder/{id}")
    public Volume getVolume(@PathVariable String id) {
        OSClient.OSClientV3 os = createToken();
        Volume volume = os.blockStorage().volumes().get(id);
        return volume;
    }

    @PostMapping("/cinder/volume")
    public Volume createVolume(@RequestBody Cinder cinder) {
        System.out.println(cinder);
        OSClient.OSClientV3 os = createToken();
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(cinder.name)
                        .description(cinder.description)
                        .size(cinder.size)
                        .build()
                );
        return volume;
    }

    @PostMapping("/cinder/boot")
    public Volume createBootVolume(@RequestBody Cinder cinder) {
        OSClient.OSClientV3 os = createToken();
        Volume volume = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(cinder.name)
                        .description(cinder.description)
                        .imageRef("fc82ac33-899f-4afb-a250-1d1fc099fdc0")
                        .bootable(true)
                        .build()
                );
        return volume;
    }

    @DeleteMapping("/cinder/{id}")
    public String deleteVolume(@PathVariable String volumeId) {
        OSClient.OSClientV3 os = createToken();
        os.blockStorage().volumes().delete(volumeId);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/cinder/{id}")
    public String updateVolume(@PathVariable String id,
                                @RequestBody String name,
                                @RequestBody String description) {
        OSClient.OSClientV3 os = createToken();
        os.blockStorage()
                .volumes()
                .update(id, name, description);
        return "성공적으로 수정되었습니다.";
    }

}
