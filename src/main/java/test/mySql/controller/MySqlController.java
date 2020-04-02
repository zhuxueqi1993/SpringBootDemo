package test.mySql.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.mySql.service.MySqlService;

@RestController
@RequestMapping("/mySql")
public class MySqlController {
	private static final Logger log = LoggerFactory.getLogger(MySqlController.class);
	
	@Autowired
	MySqlService mySqlService;
	
	/**
	 * 增加与修改
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/insertOrUpdate")
	public Map<String,Object> insertOrUpdate(@RequestBody Map<String,Object> messageIn) {
		log.info("进入insertOrUpdate");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		mySqlService.insertOrUpdate(messageIn,mapReturn);
		log.info("离开insertOrUpdate");
		return mapReturn;
	}
	
	/**
	 * 查询
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/queryStudent")
	public Map<String,Object> queryStudent(@RequestBody Map<String,Object> messageIn) {
		log.info("进入queryStudent");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		mySqlService.queryStudent(messageIn,mapReturn);
		log.info("离开queryStudent");
		return mapReturn;
	}
	
	/**
	 * 删除
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/delStudent")
	public Map<String,Object> delStudent(@RequestBody Map<String,Object> messageIn) {
		log.info("进入delStudent");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		mySqlService.delStudent(messageIn,mapReturn);
		log.info("离开delStudent");
		return mapReturn;
	}
	
	/**
	 * 原生sql
	 */
	@RequestMapping("/nativeQuery")
	public Map<String,Object> nativeQuery(@RequestBody Map<String,Object> messageIn) {
		log.info("进入nativeQuery");
		Map<String,Object> mapReturn = new HashMap<String,Object>();
		mySqlService.nativeQuery(messageIn,mapReturn);
		log.info("离开nativeQuery");
		return mapReturn;
	}
}
