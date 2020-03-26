package test.mongo.service.interfc;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface MongoService {
	public void mongoInsertOrUpdate(Map<String,Object> messageIn,Map<String,Object> mapReturn);
	public 	void mongoQuery(Map<String,Object> messageIn,Map<String,Object> mapReturn);
	public 	void mongoDel(Map<String,Object> messageIn,Map<String,Object> mapReturn);
}
