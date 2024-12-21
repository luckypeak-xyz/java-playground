package xyz.luckypeak.map_learning;

import java.util.concurrent.ConcurrentHashMap;

public class MapLearning {

    public static void main(String[] args) {
        ConcurrentHashMap<Long, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1L, "1");
        System.out.println(concurrentHashMap.get(1L));
    }

}
