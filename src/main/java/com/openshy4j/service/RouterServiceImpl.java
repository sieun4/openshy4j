package com.openshy4j.service;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouterServiceImpl implements RouterService {

    @Override
    public List<? extends Router> getRouters(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        List<? extends Router> routers = os.networking().router().list();
        return routers;
    }

    @Override
    public Router getRouter(Token token, String routerID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Router router = os.networking().router().get(routerID);
        return router;
    }

    @Override
    public Router createRouter(Token token, String name) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Router router = os.networking().router().create(Builders.router()
                .name(name)
                .adminStateUp(true)
                .build());
        return router;
    }

    @Override
    public String deleteRouter(Token token, String routerID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        os.networking().router().delete(routerID);
        return "삭제되었습니다.";
    }

    @Override
    public Router updateRouter(Token token, String routerID, String name) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        Router router = os.networking().router().get(routerID);
        router = os.networking().router().update(router.toBuilder().name(name).build());
        return router;
    }

    @Override
    public RouterInterface attachSubnet(Token token, String routerID, String subnetID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        RouterInterface iface = os.networking().router()
                .attachInterface(routerID, AttachInterfaceType.SUBNET, subnetID);
        return iface;
    }

    @Override
    public RouterInterface detachSubnet(Token token, String routerID, String subnetID) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        RouterInterface iface = os.networking().router()
                .detachInterface(routerID, subnetID, null);
        return iface;
    }
}
