package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    public List <Request> findByStudentId(int id);
    public List <Request> findByTutorId(int id);
}