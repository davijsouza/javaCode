package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.entity.CurrentScript;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@CacheConfig(cacheNames="scripts")
public class CacheDaoImpl {
	Map<String, CurrentScript> map = new HashMap<String, CurrentScript>();
	@Cacheable(key="#user.getId()")
	public CurrentScript save(CurrentScript user) {
		System.out.println("save()被执行了....");
		map.put(user.getId(), user);
		return user;
	}
	@CachePut(key="#user.getId()")
    public CurrentScript update(CurrentScript user) {
        System.out.println("update()执行了=============");
        map.put(user.getId(), user);
		return user;
    }

    @CacheEvict(key="#user.getId()")
    public boolean delete(CurrentScript user) {
        System.out.println("delete()执行了=============");
        map.remove(user.getId());
		return true;
    }

    @CacheEvict(allEntries=true,value= {"MyTest","MM"})
    public void deleteAll() {
    	map.clear();
    }

    @Cacheable(value="scripts")
    public String selectFromOtherCache() {
    	System.out.println("其他缓存");
    	return "来自缓存";
    }
    @Cacheable(key="#userId")
    public CurrentScript select(String userId) {
        System.out.println("select()执行了=============");
        CurrentScript user = map.get(userId);
        if(user!=null)
        	return user;
        else
        	return new CurrentScript();
    }
   /* @Cacheable(allEntries=true)
    public String selectAll() {

	    return
    }*/
}
