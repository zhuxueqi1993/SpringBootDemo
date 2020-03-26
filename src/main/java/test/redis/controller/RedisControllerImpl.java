package test.redis.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.domain.Student;
import test.redis.interfc.controller.RedisController;
import test.redis.service.RedisServiceImpl;

@RestController
@RequestMapping("/redis")
public class RedisControllerImpl implements RedisController{
	private static final Logger log = LoggerFactory.getLogger(RedisControllerImpl.class);
	
	@Autowired
	RedisServiceImpl redisService;
	
	@Override
	@RequestMapping("/redisSetDemo")
	public void redisSetDemo(@RequestBody Map<String,Object> messageIn) throws InterruptedException {
		log.info("开始执行redis写入操作");
		redisService.redisWrite(messageIn);
		log.info("redis写入完成");
	}
	
	@Override
	@RequestMapping("/autoCache")
	@Cacheable(value = "autoCache",key="#key")
    public Student autoCache(@RequestBody String key) {
		Student r =  redisService.autoCache();
		return r;
        
    }
	
	@RequestMapping("/delAutoCache")
	@CacheEvict(value = "autoCache",key="#key")
    public String delAutoCache(@RequestBody String key) {
        return "ok";
    }
}
