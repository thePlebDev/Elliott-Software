package com.elliott.software.repositories;

import com.elliott.software.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;


    @Test
    public void createUserNFindByUsername(){
        //GIVEN
        String EXPECTED_USERNAME = "BOB";
        User user = new User(EXPECTED_USERNAME,"bob@bobmail.com","12345",false);

        //WHEN
        underTest.save(user);
        User foundUser = underTest.findUserByUsername(EXPECTED_USERNAME).get();

        //THEN
        assertThat(foundUser.getUsername()).isEqualTo(EXPECTED_USERNAME);

    }

    @Test
    public void findByEmail(){
        //GIVEN
        String EXPECTED_EMAIL = "BOB@BOBMAIL.COM";
        User user = new User("EXPECTED_USERNAME",EXPECTED_EMAIL,"123456789",false);

        //WHEN
        underTest.save(user);
        User foundUser = underTest.findUserByEmail(EXPECTED_EMAIL).get();

        //THEN
        assertThat(foundUser.getEmail()).isEqualTo(EXPECTED_EMAIL);

    }

    @Test
    public void findByCustomerId(){
        //GIVEN
        String EXPECTED_CUSTOMER_ID= "4443433343";
        User user = new User("EXPECTED_USERNAME","EXPECTED_EMAIL","123456789",false);
        user.setCustomerId(EXPECTED_CUSTOMER_ID);

        //WHEN
        underTest.save(user);
        User foundUser = underTest.findByCustomerId(EXPECTED_CUSTOMER_ID).get();

        //THEN
        assertThat(foundUser.getCustomerId()).isEqualTo(EXPECTED_CUSTOMER_ID);

    }

    @Test
    public void existsByCustomerId(){
        //GIVEN
        String EXPECTED_CUSTOMER_ID= "4443433343";
        User user = new User("EXPECTED_USERNAME","EXPECTED_EMAIL","123456789",false);
        user.setCustomerId(EXPECTED_CUSTOMER_ID);

        //WHEN
        underTest.save(user);
        Boolean foundUser = underTest.existsByCustomerId(EXPECTED_CUSTOMER_ID);

        //THEN
        assertThat(foundUser).isEqualTo(true);
    }

}
