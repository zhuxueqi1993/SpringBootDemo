package test.redis.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import test.domain.Student;
import test.redis.interfc.service.RedisService;



@Service
public class RedisServiceImpl implements RedisService{
	
    @Autowired
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplateString;
    
    @Override
	public boolean redisWrite(Map<String,Object> messageIn) throws InterruptedException {
		ValueOperations<String,Object> operations=redisTemplate.opsForValue();
        operations.set("testMap", messageIn);
        operations.set("testMap.lat", messageIn,1, TimeUnit.SECONDS);
        System.out.println("testMap : "+JSON.toJSONString(operations.get("testMap")) );
        System.out.println("testMap.lat : "+JSON.toJSONString(operations.get("testMap.lat")) );
        Thread.sleep(1000);
        System.out.println("1s后");
        System.out.println("testMap : "+JSON.toJSONString(operations.get("testMap")) );
        System.out.println("testMap.lat : "+JSON.toJSONString(operations.get("testMap.lat")) );
        
        redisTemplateString.opsForValue().set("StringTest", " a String Value");
        System.out.println("StringValue"+redisTemplateString.opsForValue().get("StringTest"));
        return true;
        //redisTemplate.delete("testMap.lat"); 删除操作
        //Assert.assertEquals("",messageIn, operations.get("aaa"));
	}
	
    @Override
    public Student autoCache() {
		Student tr = new Student();
		tr.setName("xiaoming");
		tr.setSex("男");
		tr.setAge(18);
        return tr;
    }
	
    @Override
    public Object getAutoCache() {
        return redisTemplate.opsForValue().get("autoCache");
    }
}
