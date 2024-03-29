package com.rest.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Properties;

@Getter
@Setter
@ToString
public class KakaoProfile {
    private Long id;
    private Properties properties;

    @Getter
    @Setter
    @ToString
    private static class Properties{
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }
}
