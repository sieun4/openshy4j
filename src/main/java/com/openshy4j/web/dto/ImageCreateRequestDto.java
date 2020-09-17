package com.openshy4j.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageCreateRequestDto {

  @NonNull
  private String name;

  private String description;

  @NonNull
  private String diskFormat;

  @NonNull
  private MultipartFile file;



}
