package com.aurionpro.mappings.cloudera.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration

public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwlpbatwr",
                "api_key", "315696812819252",
                "api_secret", "z0emai7mNRDS75SEbsCqsxaaoL8"
        ));
    }
}
