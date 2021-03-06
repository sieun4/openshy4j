package com.openshy4j.service;

import static org.openstack4j.api.OSClient.OSClientV3;

import com.openshy4j.web.dto.ServerDto;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.Port;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerServiceImpl implements ServerService{
    @Override
    public List<? extends Server> getServers(Token token) {
        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);
        return os.compute().servers().list();
    }

    @Override
    public Server getServer(Token token, String serverId) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        return os.compute().servers().get(serverId);
    }

    @Override
    public Server createServer(Token token, ServerDto serverDto) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        Port port = os.networking().port().create(Builders.port()
                .networkId(serverDto.getNetworkId())
                .build());
        ServerCreate serverCreate = Builders.server()
                .name(serverDto.getName())
                .flavor(serverDto.getFlavorId())
                .image(serverDto.getImageId())
                .addNetworkPort(port.getId())
                .build();
        return os.compute().servers().boot(serverCreate);
    }

    @Override
    public Server createBlockServer(Token token, ServerDto serverDto) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        Port port = os.networking().port().create(Builders.port()
                .networkId(serverDto.getNetworkId())
                .build());
        BlockDeviceMappingBuilder blockDeviceMappingBuilder = Builders.blockDeviceMapping()
                .uuid(serverDto.getVolumeId())
                .deviceName("/dev/vda")
                .bootIndex(0);
        ServerCreate sc = Builders.server()
                .name(serverDto.getName())
                .addNetworkPort(port.getId())
                .blockDevice(blockDeviceMappingBuilder.build())
                .build();
        return os.compute().servers().boot(sc);
    }

    @Override
    public String actionServer(Token token, String serverId, ServerDto serverDto) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        os.compute().servers().action(serverId, Action.valueOf(serverDto.getAction()));
        return serverDto.getAction();
    }

    @Override
    public VNCConsole consoleServer(Token token, String serverId) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        return os.compute().servers().getVNCConsole(serverId, VNCConsole.Type.NOVNC);
    }

    @Override
    public String deleteServer(Token token, String serverId) {
        OSClientV3 os = OSFactory.clientFromToken(token);
        os.compute().servers().delete(serverId);
        return "삭제되었습니다.";
    }
}
