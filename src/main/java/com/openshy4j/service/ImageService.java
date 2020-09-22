package com.openshy4j.service;

import com.openshy4j.web.dto.ImageCreateRequestDto;
import java.io.IOException;
import java.util.List;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.image.v2.Image;

public interface ImageService {

  List<? extends Image> listImage(Token token);
  Image getImage(Token token, String iamgeId);
  void createImage(Token token, ImageCreateRequestDto dto) throws IOException;
  void updateImage(Token token, String name, String description, String imageId);
  void deleteImage(Token token, String imageId);

}
