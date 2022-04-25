package com.example.tutorSite;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    @Column(name = "tutorid", nullable = false, unique = true, length = 6)
    private int tutorId;
    
    @Column(name = "studentid", nullable = false, unique = true, length = 6)
    private int studentId;
     
    @Column(name = "classid", nullable = false, unique = true, length = 10)
    private int classId;

    @Column(name = "classname", nullable = false, length = 100)
    private String className;

    @Column(name = "studentname", nullable = false, length = 100)
    private String studentName;

    @Column(name = "tutorname", nullable = false, length = 100)
    private String tutorName;
    
    @Column(name = "lessondate", length = 10)
    private String lessonDate;
    
    @Column(name = "lessontime", length = 8)
    private String lessonTime;

	public void newLesson(int tutorId, int studentId, int classId, String className, 
    		String lessonDate, String lessonTime, String studentName, String tutorName) {
    	this.setTutorId(tutorId);
    	this.setStudentId(studentId);
    	this.setClassId(classId);
    	this.setClassName(className);
    	this.setLessonDate(lessonDate);
    	this.setLessonTime(lessonTime);
    	this.setStudentName(studentName);
    	this.setTutorName(tutorName);
    }
    
    //Getters and setters
	public int getTutorId() {
		return tutorId;
	}

	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public int getId() {
		return id;
	}

    public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
}

