package com.openshy4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openshy4j.service.FlavorServiceImpl;
import com.openshy4j.service.IdentityServiceImpl;
import com.openshy4j.service.ImageServiceImpl;
import com.openshy4j.service.NetworkServiceImpl;
import com.openshy4j.service.ServerServiceImpl;
import com.openshy4j.web.dto.ServerDto;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.image.v2.Image;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class ServerController {
    private final ServerServiceImpl serverServiceImpl;
    private final IdentityServiceImpl identityServiceImpl;
    private final ImageServiceImpl imageServiceImpl;
    private final NetworkServiceImpl networkServiceImpl;
    private final FlavorServiceImpl flavorServiceImpl;


    @GetMapping("/servers")
    ModelAndView getServers(ModelAndView modelAndView) {
        modelAndView.addObject("listServer"
            ,serverServiceImpl.getServers(identityServiceImpl.getToken()));
        modelAndView.setViewName("/server/index");
        return modelAndView;
    }
    @GetMapping("/server/{serverId}")
    ModelAndView getServer(@PathVariable String serverId,ModelAndView modelAndView) {
        Server server = serverServiceImpl.getServer(identityServiceImpl.getToken(), serverId);
        String serverName = server.getName();
        modelAndView.addObject("serverName", serverName);
        modelAndView.addObject("server", server);
        modelAndView.addObject("serverId", serverId);
        modelAndView.setViewName("/server/update");

        return modelAndView;
    }

    @GetMapping("/server")
    ModelAndView createPage(ModelAndView modelAndView){
        modelAndView.addObject("listImage", imageServiceImpl.listImage(
            identityServiceImpl.getToken()));
        modelAndView.addObject("listNetwork"
            , networkServiceImpl.getNetworks(identityServiceImpl.getToken()));
        modelAndView.addObject("listFlavor"
            , flavorServiceImpl.getFlavors(identityServiceImpl.getToken()));
        modelAndView.setViewName("/server/create");
        return modelAndView;
    }
    @PostMapping("/server")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createServer(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> convertedData = objectMapper.readValue(data, Map.class);
        ServerDto serverDto = ServerDto.builder()
            .name(convertedData.get("name"))
            .flavorId(convertedData.get("flavor"))
            .imageId(convertedData.get("image"))
            .networkId(convertedData.get("network"))
            .build();
        serverServiceImpl.createServer(identityServiceImpl.getToken(), serverDto);
    }

    @PostMapping("/server/block")
    Server createBlockServer(@RequestBody ServerDto serverDto) {
        return serverServiceImpl.createBlockServer(identityServiceImpl.getToken(),  serverDto);
    }

    @PutMapping("/server")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    String actionServer(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> convertedData = objectMapper.readValue(data, Map.class);
        String serverId = convertedData.get("serverId");
        ServerDto serverDto = ServerDto.builder().action(convertedData.get("action")).build();
        return serverServiceImpl.actionServer(identityServiceImpl.getToken(), serverId, serverDto);
    }

    @GetMapping("/server/console/{serverId}")
    void consoleServer(@PathVariable String serverId, HttpServletResponse response)
        throws IOException {
        response.sendRedirect(serverServiceImpl.consoleServer(identityServiceImpl.getToken(), serverId).getURL());
    }

    @DeleteMapping("/server")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteServer(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> convertedData = objectMapper.readValue(data, Map.class);
        String serverId = convertedData.get("serverId");
        serverServiceImpl.deleteServer(identityServiceImpl.getToken(), serverId);
    }

}
