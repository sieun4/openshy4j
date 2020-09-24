package com.openshy4j.service;

import com.openshy4j.web.dto.ServerDto;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.identity.v3.Token;

import java.util.List;

public interface ServerService {
    List<? extends Server> getServers(Token token);
    Server getServer(Token token, String serverId);
    Server createServer(Token token, ServerDto serverDto);
    Server createBlockServer(Token token, ServerDto serverDto);
    void actionServer(Token token, String serverId, ServerDto serverDto);
    VNCConsole consoleServer(Token token, String serverId);
    void deleteServer(Token token, String serverId);

}
