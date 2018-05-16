package tdd.hw3.cs458.tdd_hw3_cs458;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserLoginWithEmailAndPasswordTest {

    //Valid Input Sign-In
    /////////////
    @Test
    public void isPasswordValid_valid(){
        LoginActivity login = new LoginActivity();
        String password = "asd123";
        assertTrue(login.isPasswordValid(password));
    }

    @Test
    public  void isEmailValid_valid(){
        LoginActivity login = new LoginActivity();
        String email = "test@test.com";
        assertTrue(login.isEmailValid(email));
    }

    //Null Input Sign-In
    /////////////////////////
    @Test
    public void signInWithNullInput_notSignIn(){
        //Sign In function should assert false
        LoginActivity login = new LoginActivity();
        String email = "";
        String password = "";
        assertFalse(login.signIn(email,password));
    }


    //NotValid Input Sign-In
    /////////////
    @Test
    public void isPasswordValid_notValid(){
        //Sign In function should assert false
        LoginActivity login = new LoginActivity();
        String password = "asd12345";
        assertFalse(login.isPasswordValid(password));
    }

    @Test
    public void isEmailValid_notValid(){
        LoginActivity login = new LoginActivity();
        String password = "fdsfsafsgfg";
        assertFalse(login.isEmailValid(password));
    }


}
