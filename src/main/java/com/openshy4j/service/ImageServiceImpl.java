package com.openshy4j.service;

import com.openshy4j.web.Dto.ImageCreateRequestDto;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

  @Override
  @Transactional
  public List<? extends Image> listImage(Token token) {
    OSClientV3 clientV3 = OSFactory.clientFromToken(token);
    return clientV3.imagesV2().list();
  }

  @Override
  @Transactional
  public Image getImage(Token token, String iamgeId) {
    OSClientV3 clientV3 = OSFactory.clientFromToken(token);
    return clientV3.imagesV2().get(iamgeId);
  }

  @Override
  @Transactional
  public void createImage(Token token, ImageCreateRequestDto dto) throws IOException {
    OSClientV3 clientV3 = OSFactory.clientFromToken(token);
    Image imageSetUp = clientV3.imagesV2().create(
        Builders.imageV2()
            .name(dto.getName())
            .additionalProperty("description", dto.getDescription())
            .containerFormat(ContainerFormat.BARE)
            .visibility(Image.ImageVisibility.PUBLIC)
            .diskFormat(DiskFormat.value(dto.getDiskFormat()))
            .build()
    );
    InputStream imageFileData = dto.getFile().getInputStream();
    Payload<InputStream> payload = Payloads.create(imageFileData);
    Image imageInstance = clientV3.imagesV2().get(imageSetUp.getId());
    clientV3.imagesV2().upload(imageInstance.getId(), payload, imageInstance);
  }

  @Override
  @Transactional
  public void updateImage(Token token, String name, String description, String imageId) {
    OSClientV3 clientV3 = OSFactory.clientFromToken(token);
    clientV3.imagesV2().update(clientV3.imagesV2().get(imageId)
        .toBuilder().name(name).additionalProperty("description", description).build());
  }

  @Override
  @Transactional
  public void deleteImage(Token token, String imageId) {
    OSClientV3 clientV3 = OSFactory.clientFromToken(token);
    clientV3.imagesV2().delete(imageId);
  }
}
