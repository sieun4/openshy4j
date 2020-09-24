package com.openshy4j.web;

import com.openshy4j.service.IdentityServiceImpl;
import com.openshy4j.service.ServerServiceImpl;
import com.openshy4j.web.dto.ServerDto;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ServerController {
    private final ServerServiceImpl serverServiceImpl;
    private final IdentityServiceImpl identityServiceImpl;

    @GetMapping("/servers")
    List<? extends Server> getServers() {
        return serverServiceImpl.getServers(identityServiceImpl.getToken());
    }
    @GetMapping("/server/{serverId}")
    Server getServer(@PathVariable String serverId) {
        return serverServiceImpl.getServer(identityServiceImpl.getToken(), serverId);
    }

    @PostMapping("/server")
    Server createServer(@RequestBody ServerDto serverDto) {
        return serverServiceImpl.createServer(identityServiceImpl.getToken(), serverDto);
    }

    @PostMapping("/server/block")
    Server createBlockServer(@RequestBody ServerDto serverDto) {
        return serverServiceImpl.createBlockServer(identityServiceImpl.getToken(),  serverDto);
    }

    @PutMapping("/server/{serverId}/action")
    String actionServer(@PathVariable String serverId, @RequestBody ServerDto serverDto) {
        return serverServiceImpl.actionServer(identityServiceImpl.getToken(), serverId, serverDto);
    }

    @GetMapping("/server/console/{serverId}")
    VNCConsole consoleServer(@PathVariable String serverId) {
        return serverServiceImpl.consoleServer(identityServiceImpl.getToken(), serverId);
    }

    @DeleteMapping("/server/{serverId}")
    String deleteServer(@PathVariable String serverId) {
        return serverServiceImpl.deleteServer(identityServiceImpl.getToken(), serverId);
    }
}
