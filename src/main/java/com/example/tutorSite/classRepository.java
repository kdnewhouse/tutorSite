package com.example.tutorSite;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface classRepository extends JpaRepository<Class, Long> {
	@Query("SELECT c FROM Class c")
    public List<Class> findAllClasses();

	@Query("SELECT c.name FROM Class c WHERE c.id = ?1")
    public String findNameById(int id);
	
}
