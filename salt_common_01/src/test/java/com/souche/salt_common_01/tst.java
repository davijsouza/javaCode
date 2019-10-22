package com.souche.salt_common_01;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tst {
    public static void main(String[] args) {

      /*  Set<String> set=new HashSet<>();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        System.err.println(set);*/
      List<String> list=new ArrayList<>();
      List<String> result=new ArrayList<>();
      list.add("w");
      list.add("e");
      list.add("e");
      list.add("w");
      list.add("w");
      Set<String> set =new HashSet<>(list) ;
        for (int i=0; i<set.size();i++
             ) {
            System.err.println(set);
        }
       // System.err.println(set);
    }
}
