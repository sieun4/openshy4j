package com.openshy4j.web;


import com.openshy4j.service.VolumeService;
import com.openshy4j.service.IdentityService;
import com.openshy4j.web.dto.CinderDto;
import lombok.RequiredArgsConstructor;
import org.openstack4j.model.storage.block.Volume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VolumeController {
    private final IdentityService identityService;
    private final VolumeService cinderService;

    @GetMapping("/volumes")
    public List<? extends Volume> getVolumes() {
        return cinderService.getVolumes(identityService.getToken());
    }

    @GetMapping("/volume/{volumeID}")
    public Volume getVolume(@PathVariable String volumeID) {
        return cinderService.getVolume(identityService.getToken(), volumeID);
    }

    @PostMapping("/volume")
    public Volume createVolume(@RequestBody CinderDto.createVolume dto) {
        return cinderService.createVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getSize());
    }

    @PostMapping("/volume/boot")
    public Volume createBootVolume(@RequestBody CinderDto.createBootVolume dto) {
        return cinderService.createBootVolume(identityService.getToken(), dto.getName(), dto.getDescription(), dto.getImageID());
    }

    @DeleteMapping("/volume/{volumeID}")
    public String deleteVolume(@PathVariable String volumeID) {
        cinderService.deleteVolume(identityService.getToken(), volumeID);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/volume/{volumeID}")
    public String updateVolume(@PathVariable String volumeID, @RequestBody CinderDto.updateVolume dto) {
        dto.setVolumeID(volumeID);
        cinderService.updateVolume(identityService.getToken(), dto.getVolumeID(), dto.getName(), dto.getDescription());
        return "성공적으로 수정되었습니다.";
    }

}
