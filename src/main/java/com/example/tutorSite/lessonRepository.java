package com.example.tutorSite;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface lessonRepository extends JpaRepository<Lesson, Long> {
	@Query("SELECT l FROM Lesson l WHERE l.tutorId = ?1")
    public List<Lesson> findAllByTutorId(int id);
	

	@Query("SELECT l FROM Lesson l WHERE l.studentId = ?1")
    public List<Lesson> findAllByStudentId(int id);
	
}