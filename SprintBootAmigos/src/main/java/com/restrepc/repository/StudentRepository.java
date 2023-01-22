package com.restrepc.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restrepc.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {  //Student es la instancia que buscamos, el Long es porque el ID es de tipo long
	
	@Query("select s from student s where s.email = ?1")
	Optional<Student> findStudentByEmail(String email) ;
}
