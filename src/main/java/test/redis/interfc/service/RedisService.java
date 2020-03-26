package test.redis.interfc.service;

import java.util.Map;

import test.domain.Student;

public interface RedisService {
	public boolean redisWrite(Map<String,Object> messageIn) throws InterruptedException;
	public Student autoCache();
	public Object getAutoCache();
}
