package com.example.tutorSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface scheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("SELECT s FROM Schedule s WHERE s.id = ?1")
    public Schedule findById(int id);
}
