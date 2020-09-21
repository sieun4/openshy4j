package com.openshy4j.web;

import com.openshy4j.service.IdentityService;
import com.openshy4j.service.RouterService;
import com.openshy4j.web.dto.RouterDTO;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RouterController {
    private final IdentityService identityService;
    private final RouterService routerService;

    @GetMapping("/routers")
    public List<? extends Router> getRouters(){
        return routerService.getRouters(identityService.getToken());
    }

    @GetMapping("/router/{routerID}")
    public Router getRouter(@PathVariable String routerID){
        Router router = routerService.getRouter(identityService.getToken(), routerID);
        return router;
    }

    @PostMapping("/router")
    public Router createRouter(@RequestBody RouterDTO.createRouter dto){
        Router router = routerService.createRouter(identityService.getToken(), dto.getName());
        return router;
    }

    @DeleteMapping("/router/{routerID}")
    public String deleteRotuer(@PathVariable String routerID){
        return routerService.deleteRouter(identityService.getToken(), routerID);
    }

    @PutMapping("/router/{routerId}")
    public Router updateRouter(@PathVariable String routerId, @RequestBody RouterDTO.updateRouter dto){
        return routerService.updateRouter(identityService.getToken(), routerId, dto.getName());
    }

    @PostMapping("/router/{routerId}/attachsubnet")
    public RouterInterface attachSubnet(@PathVariable String routerId, @RequestBody RouterDTO.attachSubnet dto){
        return routerService.attachSubnet(identityService.getToken(), routerId, dto.getSubnetID());
    }

    @DeleteMapping("/router/{routerId}/detachsubnet")
    public RouterInterface detachSubent(@PathVariable String routerId, @RequestBody RouterDTO.detachSubnet dto){
        return routerService.detachSubnet(identityService.getToken(), routerId, dto.getSubnetID());
    }
}
