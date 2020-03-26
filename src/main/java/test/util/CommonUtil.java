package test.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Component
public class CommonUtil {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	CommonQuery commonQuery;
	
	private final String numberJudge = "^-{0,1}\\d+$";
	private final String floatJudge = "^-{0,1}\\d+(\\.+\\d+)*$";
	public boolean strIsNumber(String infoNeedJudge) {
		if(null==infoNeedJudge||"".equals(infoNeedJudge)) {
			return false;
		}else {
			return infoNeedJudge.matches(numberJudge);
		}
	}
	public boolean spstrIsNumber(String infoNeedJudge) {
		if(null==infoNeedJudge||"".equals(infoNeedJudge)) {
			return false;
		}else {
			return infoNeedJudge.matches(floatJudge);
		}
	}
	
	public Integer spstrToInt(String infoNeedJudge) {
		if(spstrIsNumber(infoNeedJudge))
			return (int)Double.parseDouble(infoNeedJudge);
		else return 0;
	}
	
	public Integer strToInt(String infoNeedJudge) {
		if(strIsNumber(infoNeedJudge))
			return Integer.parseInt(infoNeedJudge);
		else return 0;
	}
	public Long strToLong(String infoNeedJudge) {
		if(strIsNumber(infoNeedJudge))
			return Long.parseLong(infoNeedJudge);
		else return 0l;
	}
	public Double strToDouble(String infoNeedJudge) {
		if(spstrIsNumber(infoNeedJudge))
			return Double.parseDouble(infoNeedJudge);
		else return 0d;
	}
	
	
	public String objToStr(Object obj) {
		return obj == null?"":"null".equals(obj)?"":(obj+"");
	}
	
	
	/**
	 * 发送get请求
	 * @param urlCall
	 * @return
	 */
	public Map<String,Object> callUrlByGetWithJson(String urlCall) {
		String result = "";
        JSONObject result2 = null;
        int status = 0;
        try {  
        	URL urlObj = new URL(urlCall);
        	HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        	status = connection.getResponseCode();
        	if (connection.getResponseCode() != 200 ) {
                @SuppressWarnings("resource")
                Scanner s = new Scanner(connection.getErrorStream(), "utf-8").useDelimiter("\\A");
            	try {
            		result = s.hasNext() ? s.next() : "true";
            	} finally {
            		s.close();
            	}
               }else{
            	   @SuppressWarnings("resource")
            	   Scanner s = new Scanner(connection.getInputStream(), "utf-8").useDelimiter("\\A");
                   try {
                   	result = s.hasNext() ? s.next() : "true";
                   } finally {
                       s.close();
                   }
               }
            result2 = JSON.parseObject(result); 
        } catch (IOException e) {  
            e.printStackTrace();  
        }  catch (Exception e) {  
            e.printStackTrace();  
        } 
        //System.out.println(result2.get("took"));
        Map<String,Object> mapResult = new HashMap<String,Object>();
        for(Entry<String, Object> en : result2.entrySet()) {
        	mapResult.put(en.getKey(), en.getValue());
        }
        mapResult.put("code", status);
        
        return mapResult; 
	}
	/**
	 * post 请求
	 * @param urlCall
	 * @return
	 */
	public Map<String,Object> callUrlByPostWithJson(String urlCall,String params) {
		String  result = "";
        JSONObject result2 = null;
        try {  
            URL url = new URL(urlCall);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            //connection.connect(); 
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close(); 
            if (connection.getResponseCode() != 200 ) {
                @SuppressWarnings("resource")
                Scanner s = new Scanner(connection.getErrorStream(), "utf-8").useDelimiter("\\A");
            	try {
            		result = s.hasNext() ? s.next() : "true";
            	} finally {
            		s.close();
            	}
            }else{
            	   @SuppressWarnings("resource")
            	   Scanner s = new Scanner(connection.getInputStream(), "utf-8").useDelimiter("\\A");
                   try {
                   	result = s.hasNext() ? s.next() : "true";
                   } finally {
                       s.close();
                   }
             }
            if(!"true".equals(result))
            	result2 = JSON.parseObject(result); 
           
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        //System.out.println(result2.get("took"));
        //System.out.println(result2.get("took"));
        Map<String,Object> mapResult = new HashMap<String,Object>();
        if(result2!=null) {
        	mapResult.put("successful", true);
        	for(Entry<String, Object> en : result2.entrySet()) {
            	mapResult.put(en.getKey(), en.getValue());
            }
        }else {
        	mapResult.put("successful", false);
        }
        
        return mapResult; 
	}
	
	public String mapToJsonStr(Map<String,Object> objConvert) {
		StringBuffer sbReturn = new StringBuffer("{") ;
		for(Entry<String, Object> entry : objConvert.entrySet()) {
			sbReturn.append("\"").append(entry.getKey()).append("\"").append(":\"").append(entry.getValue()).append("\",");
		}
		sbReturn.deleteCharAt(sbReturn.length()-1);
		sbReturn.append("}");
		return sbReturn.toString();
	}
	
	/**
	 * 移位运算内含数值
	 */
	public List<Integer> getIntContain(int num) {
		List<Integer> liReturn = new ArrayList<Integer>();
		
		ok :
		for(int i = 0 ; i < 31 ; i++) {
			if(1<<i == num) {
				liReturn.add(num);
				break;
			}else if(1<<i < num) {
				continue;
			}else if(1<<i > num) {
				for(int j = (i-1) ; j>=0 ; j--) {
					if(1<<j < num) {
						liReturn.add(1<<j);
						num = num - (1<<j);
					}else if(1<<j == num) {
						liReturn.add(1<<j);
						break ok;
					}
				}
			}
		}
		return liReturn;
	}
	
	/**
	 * 移位运算内含数值
	 */
	public List<Integer> getContainInt(int num,int size) {
		List<Integer> liReturn = new ArrayList<Integer>();
		liReturn.add(num);
		for(int i = 0 ; i < 32 ; i++) {
			if(1<<i > size) {
				break;
			}else if(1<<i == num) {
				continue;
			}else {
				liReturn.add((1<<i)+num);
			}
		}
		return liReturn;
	}
	SimpleDateFormat formatterDay = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 自动转换为echart格式
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> convertEchart(List<Object[]> liResult,int type,int time,int num) {
		Map<String,Integer> typeIndex = new HashMap<String,Integer>();
		List<String> liTime = new ArrayList<String>();
		List<Map<String,Object>> liReturn = new ArrayList<Map<String,Object>>();
		for(int i = 0 ; i < liResult.size() ; i ++ ) {
			//typeIndex get
			int typeIndexNow = 0;
			if(typeIndex.containsKey(liResult.get(i)[type]+"")) {
				typeIndexNow = typeIndex.get(liResult.get(i)[type]+"");
			}else {
				//init all list in this stream
				List<String> streamValueLost = new ArrayList<String>();
				for(int j = 0 ; j < liTime.size() ; j++) {
					streamValueLost.add(null);
				}
				
				Map<String,Object> mapResult = new HashMap<String,Object>();
				mapResult.put("name", liResult.get(i)[type]+"");
				mapResult.put("value",streamValueLost);
				liReturn.add(mapResult);
				
				typeIndexNow = typeIndex.size();
				typeIndex.put(liResult.get(i)[type]+"", typeIndexNow);
			}
			
			//timeIndex insert
			if(liTime.contains(formatterDay.format((Date)liResult.get(i)[time]))) {
				//when in timeList,update info in this index
				int timeIndex = liTime.indexOf(formatterDay.format((Date)liResult.get(i)[time]));
				((List<String>)liReturn.get(typeIndexNow).get("value")).set(timeIndex,(liResult.get(i)[num]==null?0:liResult.get(i)[num])+"");
				
			}else {
				//when out of timeList,insert one message to everyBody
				int timeIndex = liTime.size();
				liTime.add(formatterDay.format((Date)liResult.get(i)[time]));
				for(int j = 0 ; j < liReturn.size() ; j++) {
					((List<String>)liReturn.get(j).get("value")).add(null);
				}
				((List<String>)liReturn.get(typeIndexNow).get("value")).set(timeIndex,(liResult.get(i)[num]==null?0:liResult.get(i)[num])+"");

			}
			
		}
		
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("name", "time");
		mapResult.put("value",liTime);
		liReturn.add(mapResult);
		return liReturn;
	}
	
	
	
	/**
	 * @param tableName
	 * @return false = exist,true = none
	 */
	public boolean tableExist(String tableName) {
		String sqlForTable = "select table_name from information_schema.tables where "
				+ "  table_type='base table' and table_name = ?";
		List<Object> liFind = new ArrayList<Object>();
		liFind.add(tableName);
		List<Object> liResult = commonQuery.findSingleInfoBySql(sqlForTable,liFind);
		return liResult.isEmpty();
	}
	
	
	public boolean nullCharge(Object obj) {
		String objStr = obj+"";
		if("null".equals(objStr)||"".equals(objStr)) {
			return false;
		}
		return true;
	}
}
