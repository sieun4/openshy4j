package com.openshy4j.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class ImageCreateRequestDto {

  @NonNull
  private String name;

  private String description;

  @NonNull
  private String diskFormat;

  @NonNull
  private MultipartFile file;



}
