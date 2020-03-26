package test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.domain.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	// jpql
	@Query(value = "from Student where name = ?2 and sex = ?1")
	List<Student> findStudentBySexAndName(String sex, String name);
	
}
