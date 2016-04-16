package com.hengsu.uliketu.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hengsu.uliketu.core.model.AuthModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
public class CacheConfig {

    @Bean
    @Qualifier("sessionCache")
    public Cache<String, AuthModel> sessionCache() {
        Cache<String, AuthModel> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES).build();
        AuthModel authModel = new AuthModel(1L,1);
        //TODO 去掉
        cache.put("aaaaa",authModel);
        return cache;
    }

    @Bean
    @Qualifier("identifyCodeCache")
    public Cache identifyCodeCache() {
        Cache cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.MINUTES).build();
        return cache;
    }
}
