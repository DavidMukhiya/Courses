package com.zorba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zorba.entitiy.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

}
