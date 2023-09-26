package com.moon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultiPartConfig {
    @Bean
    public CommonsMultipartResolver resolver(){
        CommonsMultipartResolver r = new CommonsMultipartResolver();
        r.setDefaultEncoding("UTF-8");
        r.setMaxUploadSizePerFile(200*1024*1024);
        r.setMaxInMemorySize(40960);
        return r;
    }
}
