package com.openshy4j.huiyung;

import com.openshy4j.web.dto.ServerDto;
import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Port;
import org.openstack4j.openstack.OSFactory;

import java.util.List;

public class Test_Server {

    @Test
    public void getServers() {
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        System.out.println(os.compute().servers().list());
    }

    @Test
    public void getServer() {
        String serverId = "9a7258b1-08da-48e9-aebf-cf078c1adf1c";
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        System.out.println(os.compute().servers().get(serverId));
    }

    @Test
    public void createServer() {
        String name = "testServer123123";
        String flavorId = "10186986-78f8-428e-b1b6-dcf9297a655b";
        String imageId = "fc82ac33-899f-4afb-a250-1d1fc099fdc0";

        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();

        Port port = os.networking().port().create(Builders.port()
                .networkId("89337158-29a6-4dd5-81de-6f612b55c436")
                .build());
        ServerCreate serverCreate = Builders.server()
                .name(name)
                .flavor(flavorId)
                .image(imageId)
                .addNetworkPort(port.getId())
                .build();
        System.out.println(os.compute().servers().boot(serverCreate));
    }

    @Test
    public void actionServer() {
        String serverId = "9a7258b1-08da-48e9-aebf-cf078c1adf1c";
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        System.out.println(os.compute().servers().action(serverId, Action.START));
    }

    @Test
    public void consoleServer() {
        String serverId = "9a7258b1-08da-48e9-aebf-cf078c1adf1c";
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        System.out.println(os.compute().servers().getVNCConsole(serverId, VNCConsole.Type.NOVNC));
    }

    @Test
    public void deleteServer() {
        String serverId = "9a7258b1-08da-48e9-aebf-cf078c1adf1c";
        Identifier domainIdentifier = Identifier.byId("default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.0.39:5000/v3")
                .credentials("admin", "openstack", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        os.compute().servers().delete(serverId);
    }
}
