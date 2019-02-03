package de.sappich.mealomat;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealOMatApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findUserDawid() {
        User foundUser = this.userRepository.findById(1L).get();
        assertEquals("user is dawid", "Dawid", foundUser.getName());
    }

    @Test
    public void userDawidIsActive() {
        User foundUser = this.userRepository.findById(1L).get();
        assertEquals("user dawid is active", foundUser.getIsActive(), true);
    }

    @Test
    @DirtiesContext
    public void deleteUserById_Dawid() {
        User foundUser = this.userRepository.findById(1L).get();
        this.userRepository.delete(foundUser);
        User foundDeletedUser = this.userRepository.findById(1L).orElse(null);
        assertNull(foundDeletedUser);
    }

    @Test
    public void saveUserInRepository() {
        User user = new User();
        user.setName("yummi");
        user.setIsActive(false);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        this.userRepository.save(user);
        User savedUser = this.userRepository.findByName(user.getName());
        assertEquals(user, savedUser);
    }
}

