package net.student.info.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.student.info.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    //List<Student> findByName(String name);
    
}
