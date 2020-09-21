package com.openshy4j.service;

import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;

import java.util.List;

public interface RouterService {
    List<? extends Router> getRouters(Token token);
    Router getRouter(Token token, String routerID);
    Router createRouter(Token token, String name);
    String deleteRouter(Token token, String routerID);
    Router updateRouter(Token token, String routerID, String name);
    RouterInterface attachSubnet(Token token, String routerID, String subnetID);
    RouterInterface detachSubnet(Token token, String routerID, String subnetID);

}
