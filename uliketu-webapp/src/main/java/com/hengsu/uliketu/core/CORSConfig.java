package com.hengsu.uliketu.core;

import com.hengsu.uliketu.core.interceptor.CORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by haiquanli on 16/5/14.
 */

@Configuration
public class CORSConfig {

    @Bean
    public Filter corsFilter(){
        return new CORSFilter();
    }
}
