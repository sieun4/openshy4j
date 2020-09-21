package com.openshy4j.huiyung;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;
import org.openstack4j.openstack.OSFactory;

import java.util.List;


public class Test_Router {

    @Test
    public void getRouters(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        List<? extends Router> routers = os.networking().router().list();
        System.out.println(routers);
    }

    @Test
    public void getRouter(){
//        ba56d9f4-33e2-44c2-90f0-eb25fbb35ebe
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        Router router = os.networking().router().get("ba56d9f4-33e2-44c2-90f0-eb25fbb35ebe");
        System.out.println(router);
    }

    @Test
    public void createRouter(){
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();

        Router router = os.networking().router().create(Builders.router()
                .name("test_net")
                .adminStateUp(true)
                .build());
        System.out.println(router);
    }

    @Test
    public void deleteRouter(){
        String routerID = "1db51263-8a5b-4694-bce9-fcc0d565a963";
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        os.networking().router().delete(routerID);
    }
    @Test
    public void attachSubnet(){
        Identifier domainIdentifier = Identifier.byId("default");
        String routerID = "fdda93ee-9077-4017-afef-c10a255a04f6";
        String subnetID = "e8568b21-fc1a-47af-a53c-275fd402724a";
        String publicID = "87876775-b609-4527-9f99-8e134284934f";
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        RouterInterface iface = os.networking().router()
                .attachInterface(routerID, AttachInterfaceType.SUBNET, subnetID);
        System.out.println(iface);
    }

    @Test
    public void detachSubnet(){
        Identifier domainIdentifier = Identifier.byId("default");
        String routerID = "1db51263-8a5b-4694-bce9-fcc0d565a963";
        String subnetID = "614814b7-6a41-478d-9df6-cfd0765520eb";
        String publicID = "87876775-b609-4527-9f99-8e134284934f";
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        RouterInterface iface = os.networking().router()
                .detachInterface(routerID, subnetID, null);
        System.out.println(iface);
    }

}
