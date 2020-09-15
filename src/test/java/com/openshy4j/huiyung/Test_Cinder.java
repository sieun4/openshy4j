package com.openshy4j.huiyung;

import static org.openstack4j.api.OSClient.OSClientV3;

import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.Errors.ErrorMessagesException;
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

//    @Test
//    public OSClientV3 make_Token(){
//        Identifier domainIdentifier = Identifier.byId("default");
//        OSClientV3 os = OSFactory.builderV3()
//                .endpoint("http://211.183.3.155:5000/v3")
//                .credentials("admin", "test123", domainIdentifier)
//                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
//                .authenticate();
//        return os;
//
//    }
//    @Test
//    public List list_Volume(){
//        OSClientV3 os = make_Token();
//        List<? extends Volume> volumes = os.blockStorage().volumes().list();
//        System.out.println(volumes);
//        return volumes;
//    }
//
//    @Test
//    public Volume get_Volume(String volumeId){
//        OSClientV3 os = make_Token();
//        Volume volume = os.blockStorage().volumes().get(volumeId);
//        return volume;
//    }
//
//    @Test
//    public Volume create_Volume(){
//        OSClientV3 os = make_Token();
//        Volume v = os.blockStorage().volumes()
//                .create(Builders.volume()
//                        .name("test Volume")
//                        .description("Simple volume to store backups on")
//                        .size(10)
//                        .build()
//                );
//        return v;
//    }
//
//    @Test
//    public Volume create_BootVolume(String name, String des, String ImageID){
//        OSClientV3 os = make_Token();
//        Volume v= os.blockStorage().volumes()
//                .create(Builders.volume()
//                        .name(name)
//                        .description(des)
//                        .imageRef(ImageID)
//                        .bootable(true)
//                        .build()
//                );
//        return v;
//    }
//
//    @Test
//    public void delete_Volume(String volumeId){
//        OSClientV3 os = make_Token();
//        os.blockStorage().volumes().delete(volumeId);
//    }
//
//    @Test
//    public void update_Volume(String volumeId, String name, String des){
//        OSClientV3 os = make_Token();
//        os.blockStorage()
//                .volumes()
//                .update(volumeId, name, des);
//    }
//

    @Test
    public void Testing(){
        System.out.println("123");
    }
}
