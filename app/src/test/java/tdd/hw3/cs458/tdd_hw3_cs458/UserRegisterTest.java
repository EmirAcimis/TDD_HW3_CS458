package tdd.hw3.cs458.tdd_hw3_cs458;

import org.junit.Test;
import static org.junit.Assert.*;


public class UserRegisterTest {

    @Test
    public void isEmailValid_notValid(){
        RegisterActivity register = new RegisterActivity();
        String email = "asdfgthrnkfl";
        assertFalse(register.isEmailValid(email));

    }
    @Test
    public void isEmailValid_Valid(){
        RegisterActivity register = new RegisterActivity();
        String email = "test@test.com";
        assertTrue(register.isEmailValid(email));

    }
    @Test
    public void isRegisterFieldAreNull_null(){
        RegisterActivity register = new RegisterActivity();
        String email = "";
        String password = "";
        String name = "";
        String age = "";
        String gender = "";
        String city = "";
        assertFalse(register.systemRegister(email,password,name,age,gender,city));

    }
    @Test
    public void isPasswordLimitValid_Valid(){
        RegisterActivity register = new RegisterActivity();
        String password = "asd123";
        assertTrue(register.isPasswordValid(password));

    }
    @Test
    public void isPasswordLimitValid_notValid(){
        RegisterActivity register = new RegisterActivity();
        String password = "asd12345";
        String password2 = "asdfg";
        assertFalse(register.isPasswordValid(password));
        assertFalse(register.isPasswordValid(password2));
    }
    @Test
    public void isGenderValid_Correct(){
        RegisterActivity register = new RegisterActivity();
        String gender = "MALE";
        String gender2 = "FEMALE";
        assertTrue(register.isGenderValid(gender));
        assertTrue(register.isGenderValid(gender2));
    }

    @Test
    public void isGenderValid_False(){
        RegisterActivity register = new RegisterActivity();
        String gender = "fdsfsaf";
        String gender2 = "dasdssfsdfa";
        assertFalse(register.isGenderValid(gender));
        assertFalse(register.isGenderValid(gender2));
    }



}
