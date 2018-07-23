package com.lht.demo.service;

import com.google.common.collect.Maps;
import com.lht.demo.guavacache.GuavaAbstractLoadingCache;
//import com.lht.demo.guavacache.*;
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

