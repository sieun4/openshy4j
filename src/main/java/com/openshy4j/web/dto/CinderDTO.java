package com.openshy4j.web.Dto;

import lombok.*;


@Data
public class CinderDto {

    @Setter
    @Getter
    public static class getVolume{
        @NonNull
        private String volumeID;
    }

    @Setter
    @Getter
    public static class createVolume{
        @NonNull
        private String name;
        @NonNull
        private String description;

        private int size = 10;
    }

    @Setter
    @Getter
    public static class createBootVolume{
        @NonNull
        private String name;

        private String description;

        @NonNull
        private String imageID;

    }

    @Setter
    @Getter
    public static class updateVolume{
        @NonNull
        private String volumeID;

        @NonNull
        private String name;

        @NonNull
        private String description;
    }

}
