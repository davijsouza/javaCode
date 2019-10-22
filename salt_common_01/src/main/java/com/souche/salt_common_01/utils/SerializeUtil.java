package com.souche.salt_common_01.utils;

import java.io.*;

/**
 * @author shifengyuan
 * @date 创建时间：2018年12月3日下午4:39:56
 */
public class SerializeUtil {

	/**
	 * 对象转数组
	 * @param obj
	 * @return
	 */
	public static byte[] objectToArray(Object obj){
		byte[] bytes = null;
		//输出流：把数据写到一个byte[]
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			//用于把对象转换为byte[](二进制)
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(oos!=null){
					oos.close();
				}
				if(bos!=null){
					bos.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 数组转JavaBean
	 * @param <T> 要转型的对象类型
	 * @param bytes
	 * @return 目标JavaBean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T arrayToObject(byte[] bytes,Class<T> cls){
		T obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			//用于把byte[](二进制)转化为JavaBean
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ois!=null){
					ois.close();
				}
				if(bis!=null){
					bis.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return obj;
	}



}
