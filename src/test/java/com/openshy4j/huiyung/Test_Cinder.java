package com.openshy4j.huiyung;

import static org.openstack4j.api.OSClient.OSClientV3;


import org.junit.Test;

import org.openstack4j.api.Builders;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeType;
import org.openstack4j.openstack.OSFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test_Cinder {

    @Test
    public OSClientV3 make_Token(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        String tokenID = os.getToken().getId();
        return os;
    }
    @Test
    void list_Volume(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://1.220.201.107:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        List<? extends Volume> volumes = os.blockStorage().volumes().list();
        System.out.println(volumes);
    }

    @Test
    void get_Volume(String volumeId){
        OSClientV3 os = make_Token();
        Volume volume = os.blockStorage().volumes().get(volumeId);
        System.out.println(volume);
    }

    @Test
    void create_Volume(){
        OSClientV3 os = make_Token();
        Volume v = os.blockStorage().volumes()
                .create(Builders.volume()
                        .name("test Volume")
                        .description("Simple volume to store backups on")
                        .size(10)
                        .build()
                );
        System.out.println(v);
    }

    @Test
    void create_BootVolume(String name, String des, String ImageID){
        OSClientV3 os = make_Token();
        Volume v= os.blockStorage().volumes()
                .create(Builders.volume()
                        .name(name)
                        .description(des)
                        .imageRef(ImageID)
                        .bootable(true)
                        .build()
                );
        System.out.println(v);
    }

    @Test
    public void delete_Volume(String volumeId){
        OSClientV3 os = make_Token();
        os.blockStorage().volumes().delete(volumeId);
    }

    @Test
    public void update_Volume(String volumeId, String name, String des){
        OSClientV3 os = make_Token();
        os.blockStorage()
                .volumes()
                .update(volumeId, name, des);
    }

    @Test
    public void Testing(){
        System.out.println("123");
    }
}
