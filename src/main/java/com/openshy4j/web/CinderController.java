package com.openshy4j.web;


import com.openshy4j.service.CinderService;
import com.openshy4j.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.storage.block.Volume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CinderController {
    private final IdentityService identityService;
    private final CinderService cinderService;

    @GetMapping("/cinder")
    public List<? extends Volume> getVolumes() {
        return cinderService.getVolumes(identityService.getToken());
    }

    @GetMapping("/cinder/{volumeID}")
    public Volume getVolume(@PathVariable com.openshy4j.web.dto.CinderDto.getVolume dto) {
        return cinderService.getVolume(identityService.getToken(), dto.getVolumeID());
    }

    @PostMapping("/cinder/volume")
    public Volume createVolume(@RequestBody com.openshy4j.web.dto.CinderDto.createVolume dto) {
        return cinderService.createVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getSize());
    }

    @PostMapping("/cinder/boot")
    public Volume createBootVolume(@RequestBody com.openshy4j.web.dto.CinderDto.createBootVolume dto) {
        return cinderService.createBootVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getImageID());
    }

    @DeleteMapping("/cinder/{volumeID}")
    public String deleteVolume(@PathVariable String volumeID) {
        cinderService.deleteVolume(identityService.getToken(), volumeID);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/cinder/{volumeID}")
    public String updateVolume(@PathVariable String volumeID, @RequestBody com.openshy4j.web.dto.CinderDto.updateVolume dto) {
        dto.setVolumeID(volumeID);
        cinderService.updateVolume(identityService.getToken(), dto.getVolumeID(), dto.getName(), dto.getDescription());
        return "성공적으로 수정되었습니다.";
    }

}
