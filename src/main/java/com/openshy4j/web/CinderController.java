package com.openshy4j.web;


import lombok.RequiredArgsConstructor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Path;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CinderController {

    public OSClient.OSClientV3 make_Token(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        return os;
    }

    @GetMapping("/cinder")
    public List<? extends Volume> list_Volume() {
        OSClient.OSClientV3 os = make_Token();
        List<? extends Volume> volumes = os.blockStorage().volumes().list();
        return volumes;
    }

    @GetMapping("/cinder/{id}")
    public Volume get_Volume(@PathVariable String id) {
        OSClient.OSClientV3 os = make_Token();
        Volume v = os.blockStorage().volumes().get(id);
        return v;
    }

    @PostMapping("/cinder/volume")
    public Volume create_Volume(@RequestParam String name,
                                @RequestParam String des,
                                @RequestParam int size) {
        OSClient.OSClientV3 os = make_Token();
        Volume v = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(name)
                        .description(des)
                        .size(size)
                        .build()
                );
        return v;
    }

    @PostMapping("/cinder/boot")
    public Volume create_BootVolume(@RequestParam String name,
                                    @RequestParam String des,
                                    @RequestParam String ImageID) {
        OSClient.OSClientV3 os = make_Token();
        Volume v= os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(name)
                        .description(des)
                        .imageRef(ImageID)
                        .bootable(true)
                        .build()
                );
        return v;
    }

    @DeleteMapping("/cinder/{id}")
    public String delete_Volume(@PathVariable String volumeId) {
        OSClient.OSClientV3 os = make_Token();
        os.blockStorage().volumes().delete(volumeId);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/cinder/{id}")
    public String update_Volume(@PathVariable String id,
                                @RequestParam String name,
                                @RequestParam String des) {
        OSClient.OSClientV3 os = make_Token();
        os.blockStorage()
                .volumes()
                .update(id, name, des);
        return "성공적으로 수정되었습니다.";
    }

}
