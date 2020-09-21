package com.openshy4j.web;

import static org.openstack4j.api.OSClient.OSClientV3;

import lombok.RequiredArgsConstructor;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

  public OSClientV3 getOSClient() {
    Identifier domainIdentifier = Identifier.byName("Default");

    return OSFactory.builderV3()
        .endpoint("http://211.183.3.250:5000/v3")
        .credentials("admin", "test123", domainIdentifier)
        .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
        .authenticate();

  }
}