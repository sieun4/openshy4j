package com.openshy4j.web;


import com.openshy4j.service.CinderService;
import com.openshy4j.service.IdentityService;
import lombok.RequiredArgsConstructor;

import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.Volume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CinderController {
    private IdentityService identityService;
    private CinderService cinderService;

    @GetMapping("/cinder")
    public List<? extends Volume> getVolumes() {
        Token token = identityService.getToken();
        List<? extends Volume> volumes = cinderService.getVolumes(token);
        return volumes;
    }

    @GetMapping("/cinder/{imageID}")
    public Volume getVolume(@PathVariable CinderService cinderService) {
        Token token = identityService.getToken();
        Volume volume = cinderService.getVolume(token);
        return volume;
    }

    @PostMapping("/cinder/volume")
    public Volume createVolume(@RequestBody CinderService cinderService) {
        Token token = identityService.getToken();
        Volume volume = cinderService.createVolume(token);
        return volume;
    }

    @PostMapping("/cinder/boot")
    public Volume createBootVolume(@RequestBody CinderService cinderService) {
        Token token = identityService.getToken();
        Volume volume = cinderService.createBootVolume(token);
        return volume;
    }

    @DeleteMapping("/cinder/{ImageID}")
    public String deleteVolume(@PathVariable CinderService cinderService) {
        Token token = identityService.getToken();
        cinderService.deleteVolume(token);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/cinder/{ImageID}")
    public String updateVolume(@PathVariable String ImageID, @RequestBody CinderService cinderService) {
        Token token = identityService.getToken();
        cinderService.updateVolume(token);
        return "성공적으로 수정되었습니다.";
    }

}
