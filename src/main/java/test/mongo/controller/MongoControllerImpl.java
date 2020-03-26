package test.mongo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.mongo.controller.interfc.MongoController;
import test.mongo.service.interfc.MongoService;

@RestController
@RequestMapping("/Mongo")
public class MongoControllerImpl implements MongoController {
	
	private static final Logger log = LoggerFactory.getLogger(MongoControllerImpl.class);
	
	@Autowired
	MongoService MongoService;
	/**
	 * 增加与修改
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/insertOrUpdate")
	public 	Map<String,Object> mongoInsertOrUpdate(@RequestBody Map<String,Object> messageIn){
		log.info("进入mongoInsertOrUpdate");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		MongoService.mongoInsertOrUpdate(messageIn,mapReturn);
		return mapReturn;
	}
	
	/**
	 * 查询
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/mongoQuery")
	public 	Map<String,Object> mongoQuery(@RequestBody Map<String,Object> messageIn){
		log.info("进入mongoQuery");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		MongoService.mongoQuery(messageIn,mapReturn);
		return mapReturn;
	}
	
	/**
	 * 删除
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/mongoDel")
	public 	Map<String,Object> mongoDel(@RequestBody Map<String,Object> messageIn){
		log.info("进入mongoDel");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		MongoService.mongoDel(messageIn,mapReturn);
		return mapReturn;
	}
}
