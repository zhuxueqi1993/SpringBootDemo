package test.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class CommonQuery {
	@PersistenceContext
	private  EntityManager em;
	
	@SuppressWarnings({ "unchecked" })
	public List<Object[]> findInfoBySql(String sql,List<Object> list){
		List<Object[]> liResult = new ArrayList<Object[]>();
		Query query = em.createNativeQuery(sql);
		for(int i = 0 ; i < list.size() ; i++) {
			query.setParameter(i+1, list.get(i));
		}
		liResult = query.getResultList();
		return  liResult;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findInfoBySql(String sql){
		Query query = em.createNativeQuery(sql);
		List<Object[]> liResult = query.getResultList();
		return  liResult;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findSingleInfoBySql(String sql){
		Query query = em.createNativeQuery(sql);
		List<Object> liResult = query.getResultList();
		return  liResult;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findSingleInfoBySql(String sql,List<Object> list){
		List<Object> liResult = new ArrayList<Object>();
		Query query = em.createNativeQuery(sql);
		for(int i = 0 ; i < list.size() ; i++) {
			query.setParameter(i+1, list.get(i));
		}
		liResult = query.getResultList();
		return  liResult;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<T> findEntityBySql(String sql,List<T> list){
		List<T> liResult = new ArrayList<T>();
		Query query = em.createNativeQuery(sql,T.class);
		for(int i = 0 ; i < list.size() ; i++) {
			query.setParameter(i+1, list.get(i));
		}
		liResult = query.getResultList();
		return  liResult;
	}*/
	
	@Modifying
	@Transactional
	public int insertBySql(String sql,List<Object> list){
		Query query = em.createNativeQuery(sql);
		for(int i = 0 ; i < list.size() ; i++) {
			query.setParameter(i+1, list.get(i));
		}
		return query.executeUpdate();
	}
	
	@Modifying
	@Transactional
	public int deleteBySql(String sql){
		Query query = em.createNativeQuery(sql);
		return query.executeUpdate();
	}
	
}
