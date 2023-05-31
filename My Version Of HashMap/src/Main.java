import datastructure.MyHashMap;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

        String str = "aabbccdddef";
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.replace(c, map.get(c) + 1);
            }
            else{
                map.put(c,1);
            }
        }


        System.out.println(map);

        Map<Character, Integer> map2 = new MyHashMap<>();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(map2.containsKey(c)){
                map2.put(c, map2.get(c) + 1);
            }
            else{
                map2.put(c,1);
            }
        }

        System.out.println(map2);

        for(Map.Entry<Character, Integer> entry : map2.entrySet())
            System.out.println("KEY: " + entry.getKey() + " VALUE: " + entry.getValue());
        //callMe();
    }

    private static void callMe(){
        callMe();
    }

}
