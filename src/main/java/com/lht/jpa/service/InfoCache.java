package com.lht.jpa.service;

import com.google.common.collect.Maps;
import com.lht.jpa.cache.GuavaAbstractLoadingCache;

import java.util.Map;

public class InfoCache extends GuavaAbstractLoadingCache<String, String> {


    private static Map<String, String> cacheMap = Maps.newHashMap();

    public InfoCache() {

    }


    @Override
    protected String fetchData(String key) {

        return cacheMap.get(key);
    }


}

