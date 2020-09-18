package com.openshy4j.web;


import com.openshy4j.service.CinderService;
import com.openshy4j.service.IdentityService;
import com.openshy4j.web.dto.CinderDTO;
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
    public Volume getVolume(@PathVariable CinderDTO.getVolume dto) {
        return cinderService.getVolume(identityService.getToken(), dto.getVolumeID());
    }

    @PostMapping("/cinder/volume")
    public Volume createVolume(@RequestBody CinderDTO.createVolume dto) {
        return cinderService.createVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getSize());
    }

    @PostMapping("/cinder/boot")
    public Volume createBootVolume(@RequestBody CinderDTO.createBootVolume dto) {
        return cinderService.createBootVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getImageID());
    }

    @DeleteMapping("/cinder/{volumeID}")
    public String deleteVolume(@PathVariable String volumeID) {
        cinderService.deleteVolume(identityService.getToken(), volumeID);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/cinder/{volumeID}")
    public String updateVolume(@PathVariable String volumeID, @RequestBody CinderDTO.updateVolume dto) {
        dto.setVolumeID(volumeID);
        cinderService.updateVolume(identityService.getToken(), dto.getVolumeID(), dto.getName(), dto.getDescription());
        return "성공적으로 수정되었습니다.";
    }

}
