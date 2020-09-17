package com.openshy4j.service;

import org.openstack4j.model.identity.v3.Token;

public interface IdentityService {

  Token getToken();

}
