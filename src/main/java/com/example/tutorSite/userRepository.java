package com.example.tutorSite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u")
    public List<User> findAllUsers();
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.label = 'Tutor'")
    public List<User> findAllTutors();
	
	@Query("SELECT u FROM User u WHERE u.label = 'Student'")
    public List<User> findAllStudents();
	
	@Query("SELECT u.name FROM User u WHERE u.id = ?1")
    public String findNameById(int id);
}