package com.example.tutorSite;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
     
    @Column(name = "password", nullable = false, length = 100)
    private String password;
     
    @Column(name = "label", nullable = false, length = 10)
    private String label;

    public void newUser(String name, String username, String password, String label) {
    	this.setName(name);
    	this.setUsername(username);
    	this.setPassword(password);
    	this.setLabel(label);
    }
    
    //Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	} 
    
}

