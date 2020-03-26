package test.mongo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import test.domain.t1;
import test.mongo.service.interfc.MongoService;
import test.util.CommonUtil;

@Service
public class MongoServiceImpl implements MongoService {
	
	private static final Logger log = LoggerFactory.getLogger(MongoServiceImpl.class);
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 增加与修改
	 * @param messageIn
	 * @return
	 */
	public void mongoInsertOrUpdate(Map<String,Object> messageIn,Map<String,Object> mapReturn){
		log.info("进入mongoInsertOrUpdate");
		mapReturn.put("successful", false);
		String name = messageIn.get("name")+"";
		String sex = messageIn.get("sex")+"";
		Integer age = commonUtil.strToInt(messageIn.get("age")+"");
		String id = messageIn.get("id")+"";
		
		t1 t = new t1();
		t.setId(id);
		t.setAge(age);
		t.setName(name);
		t.setSex(sex);
		mongoTemplate.save(t);
		mapReturn.put("successful", true);
	}
	
	/**
	 * 查询
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/mongoQuery")
	public 	void mongoQuery(@RequestBody Map<String,Object> messageIn,Map<String,Object> mapReturn){
		log.info("进入mongoQuery");
		mapReturn.put("successful", false);
		String sex = messageIn.get("sex")+"";
		Integer age = commonUtil.strToInt(messageIn.get("age")+"");
		Query query = Query.query(Criteria.where("sex").is(sex).and("age").lte(age));
		List<t1> liFind =  mongoTemplate.find(query, t1.class);
		mapReturn.put("successful", true);
		mapReturn.put("resultValue", liFind);
		
	}
	
	
	/**
	 * 删除
	 * @param messageIn
	 * @return
	 */
	@RequestMapping("/mongoDel")
	public 	void mongoDel(@RequestBody Map<String,Object> messageIn,Map<String,Object> mapReturn){
		log.info("进入mongoDel");
		mapReturn.put("successful", false);
		String sex = messageIn.get("sex")+"";
		Integer age = commonUtil.strToInt(messageIn.get("age")+"");
		Query query = Query.query(Criteria.where("sex").is(sex).and("age").lte(age));
		
		mongoTemplate.remove(query, t1.class);
		mapReturn.put("successful", true);
	}
}
