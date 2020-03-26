package test.mySql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import test.dao.StudentRepository;
import test.domain.Student;
import test.util.CommonQuery;
import test.util.CommonUtil;



@Service
public class MySqlService {
	private static final Logger log = LoggerFactory.getLogger(MySqlService.class);
	
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	CommonQuery commonQuery;
	
	@Autowired
	StudentRepository studentRepository;
	
	public Map<String,Object> insertOrUpdate(Map<String,Object> messageIn,Map<String,Object> mapReturn) {
		log.info("进入mySqlService");
		mapReturn.put("successful", false);
		String name = messageIn.get("name")+"";
		String sex = messageIn.get("sex")+"";
		int age = commonUtil.strToInt(messageIn.get("age")+"");
		
		Student st = new Student();
		
		st.setName(name);
		st.setSex(sex);
		st.setAge(age);
		
		if(commonUtil.nullCharge(messageIn.get("id"))) {
			st.setId(commonUtil.strToInt(messageIn.get("id")+""));
		}
		studentRepository.save(st);
		List<Student> liSaveAllStudent = new ArrayList<Student>();
		liSaveAllStudent.add(st);
		liSaveAllStudent.add(st);
		studentRepository.saveAll(liSaveAllStudent);
		mapReturn.put("successful", true);
		log.info("离开mySqlService");
		return mapReturn;
	}
	
	public Map<String,Object> queryStudent(Map<String,Object> messageIn,Map<String,Object> mapReturn) {
		log.info("进入queryStudent");
		mapReturn.put("successful", false);
		String name = messageIn.get("name")+"";
		String sex = messageIn.get("sex")+"";
		
		List<Student> liStu = studentRepository.findStudentBySexAndName(sex, name);
		System.out.println(JSON.toJSONString(liStu));
		mapReturn.put("resultValue", liStu);
		mapReturn.put("successful", true);
		log.info("离开queryStudent");
		return mapReturn;
	}
	
	public Map<String,Object> delStudent(Map<String,Object> messageIn,Map<String,Object> mapReturn) {
		log.info("进入delStudent");
		mapReturn.put("successful", false);
		int idQuery = 0;
		if(commonUtil.nullCharge(messageIn.get("id"))) {
			idQuery=commonUtil.strToInt(messageIn.get("id")+"");
		}else {
			mapReturn.put("errorInfo", "所需参数id不存在");
			return mapReturn;
		}
		
		Student st = studentRepository.getOne(idQuery);
		studentRepository.delete(st);
		
		/*
		 *	根据id删除
		 *	studentRepository.deleteById(idQuery);
		 */
		
		/*
		 * 删除多个
		 * List<Student> liDel = new ArrayList<Student>();
		 * studentRepository.deleteAll(liDel);
		 */
		
		mapReturn.put("successful", true);
		log.info("离开delStudent");
		return mapReturn;
	}
	
	public Map<String,Object> nativeQuery(Map<String,Object> messageIn,Map<String,Object> mapReturn) {
		log.info("进入nativeQuery");
		mapReturn.put("successful", false);
		String name = messageIn.get("name")+"";
		String sex = messageIn.get("sex")+"";
		List<Object> liFind = new ArrayList<Object>();
		String sqlQuery = "select id,age from student where name = ? and sex = ?";
		liFind.add(name);
		liFind.add(sex);
		List<Object[]> liResult = commonQuery.findInfoBySql(sqlQuery, liFind);
		System.out.println(JSON.toJSONString(liResult));
		mapReturn.put("resultValue", liResult);
		mapReturn.put("successful", true);
		log.info("离开nativeQuery");
		return mapReturn;
	}
	
	
}
