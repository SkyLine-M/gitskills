package com.ma.jpa.repository;

import com.ma.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-29 20:47:35
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
}
