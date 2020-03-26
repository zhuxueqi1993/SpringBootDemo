package test.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //声明为实体类
@Table(name="student") //对应映射数据库表
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id //主键
	@GeneratedValue //主键自动生成 有AUTO、IDENTITY、SEQUENCE、Table四种方式
	private Integer id;
	
	private String name;
	
	@Column(name = "sex", length = 1) //数据库表 列映射，name为列名 length长度
	private String sex;
	
	private Integer age;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
