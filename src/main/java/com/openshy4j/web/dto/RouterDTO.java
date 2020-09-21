package com.openshy4j.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class RouterDTO {

    @Getter
    @Setter
    public static class getRouters{
    }

    @Data
    public static class getRouter{
        private String routerID;
    }

    @Data
    public static class createRouter{
        private String name;
    }

    @Data
    public static class deleteRouter{
        private String routerID;
    }

    @Data
    public static class updateRouter{
        private String routerID;
        private String name;
    }

    @Data
    public static class attachSubnet{
        private String routerID;
        private String subnetID;
    }

    @Data
    public static class detachSubnet{
        private String routerID;
        private String subnetID;
    }
}
