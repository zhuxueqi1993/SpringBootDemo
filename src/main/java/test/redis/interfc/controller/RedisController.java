package test.redis.interfc.controller;

import java.util.Map;

import test.domain.Student;

public interface RedisController {
	public void redisSetDemo(Map<String,Object> messageIn) throws InterruptedException;
	public Student autoCache(String key);
}
