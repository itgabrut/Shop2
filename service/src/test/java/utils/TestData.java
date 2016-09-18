package utils;

import com.ilya.model.Adress;
import com.ilya.model.Client;
import com.ilya.model.enums_utils.Role;

import java.util.Collections;
import java.util.Date;

/**
 * Created by ilya on 14.09.2016.
 */
public class TestData {


    public static class TestUser{

       public static final Client TEST_CLIENT = new Client();

        static {
            Adress adress = new Adress("Russia","Moscow","112222","Tverskaya","12","12");
            TEST_CLIENT.setRoles(Collections.singleton(Role.ROLE_USER));
            TEST_CLIENT.setPassword("12345");
            TEST_CLIENT.setBirth(new Date());
            TEST_CLIENT.setEmail("test@test");
            TEST_CLIENT.setAdress(adress);
            TEST_CLIENT.setSurname("Test");
            TEST_CLIENT.setName("Test");
        }


    }


}
