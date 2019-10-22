package com.souche.salt_common_01.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class  Date {

    public static java.util.Date string2Date(String string) {
       try {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

           return  sdf.parse(string);

       }catch (Exception e){
           System.err.println("string2Date()发生错误");
           e.printStackTrace();
           return new java.util.Date();
       }
    }





    public static String  date2String(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(date) ;
    }


    public static  String Tdate2Common(String Tdate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        java.util.Date date = null;
        try {
            date = formatter.parse(Tdate);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate=sdf.format(date);
            return sDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return  null;
    }
}
