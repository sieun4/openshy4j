package com.openshy4j.huiyung;

import static org.openstack4j.api.OSClient.OSClientV3;

import org.junit.Test;

import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test_Cinder {
    @Test
    public OSClientV3 make_Token(){
        Identifier domainIdentifier = Identifier.byId("default");

        OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://211.183.3.155:5000/v3")
                .credentials("admin", "test123", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        return os;
    }

    @Test
    public void 테스트(){
        make_Token();
        System.out.println("hello world");
    }
}
