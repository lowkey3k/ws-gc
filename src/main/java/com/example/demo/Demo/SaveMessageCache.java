package com.example.demo.Demo;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.google.common.cache.*;
import com.google.common.collect.Maps;



public class SaveMessageCache {

    /**
     * 谷歌LoadingCache缓存使用
     *                                             这是一个独立demo
     *
     * @param args
     */
    static LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
//            .expireAfterAccess(2, TimeUnit.DAYS)//设置时间对象没有被读/写访问则对象从内存中删除
//            .expireAfterWrite(10, TimeUnit.MINUTES)//设置时间对象没有被写访问则对象从内存中删除

            //移除监听器,缓存项被移除时会触发
            /*.removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(RemovalNotification<Object, Object> removalNotification) {

                }
            })*/

            //CacheLoader类 实现自动加载

            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return cacheMap.get(key);
                }

            });


    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 1; i++) {
            if(cahceBuilder.size()<1){
                test();
            }
            for (String s:cahceBuilder.asMap().keySet()) {
                System.out.println(cahceBuilder.get(s));

            }
        }


        test2();
        for(int i = 0; i < 2; i++){
            for (String s:cahceBuilder.asMap().keySet()) {
                System.err.println(s+"+++222::"+cahceBuilder.get(s));
            }
        }

        /*ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cahceBuilder.asMap().keySet().forEach(cahceBuilder::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cahceBuilder.get("test1"));
        cahceBuilder.invalidate(1);//消除key为1的缓存
        Thread.sleep(2000);
        System.out.println("v2:" + cahceBuilder.get("test1"));//重新执行compute
*/


//        cahceBuilder.invalidateAll();
    }



    private static Map<String,String> cacheMap = Maps.newHashMap();


    public static void test() throws Exception{
        cacheMap.put("test1", "test11");
        cacheMap.put("test2", "test12");
        cacheMap.put("test3", "test13");
        for (String s:cacheMap.keySet()) {
            if(!cahceBuilder.asMap().keySet().contains(s)){
                cahceBuilder.get(s);
            }
        }
    }

    public static void test2() throws Exception{
        cacheMap.put("test5", "test21");
        cacheMap.put("test6", "test22");
        cacheMap.put("test4", "test23");
        for (String s:cacheMap.keySet()) {
            if(!cahceBuilder.asMap().keySet().contains(s)){
                cahceBuilder.get(s);
            }
        }
    }



}

