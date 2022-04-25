// Adapted from CodeJava at https://codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial/
package com.example.tutorSite;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private userRepository repo;
     
    // test methods go below
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setName("Ravi Kumar");
        user.setLabel("Student");
         
        User savedUser = repo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getId());
         
        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
         
    }
}