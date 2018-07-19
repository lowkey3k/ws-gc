package com.example.demo.service;

import com.google.common.collect.Maps;

import java.util.Map;

public class InfoCache extends BaseGuavaCache<String,Map> {


    private  Map<String,String> cacheMap = Maps.newHashMap();

    @Override
    public void loadValueWhenStarted() {

    }

    @Override
    protected Map<String, String> getValueWhenExpired(String key) throws Exception {

        return cacheMap;
    }

    public void set(Map<String,String> map){
        this.cacheMap=map;
    }
    public Map<String,String> getCacheMap(){
        return cacheMap;
    }

    public static void main(String[] args) {
        InfoCache infoCache = new InfoCache();
        infoCache.getCache().put("device_1",Maps.newHashMap());
    }

}
