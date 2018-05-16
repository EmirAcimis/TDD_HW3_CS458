package tdd.hw3.cs458.tdd_hw3_cs458;

import org.junit.Test;
import static org.junit.Assert.*;
public class SocialMediaAccountTest {

    @Test
    public void isGoogleLogin_Valid(){
        LoginActivity login = new LoginActivity();
        assertTrue(login.GoogleSignIn_Login());
    }

    @Test
    public void isFacebookLogin_Valid(){
        LoginActivity login = new LoginActivity();
        assertTrue(login.FacebookSignIn_Login());
    }

    @Test
    public void isInstagramLogin_Valid(){
        LoginActivity login = new LoginActivity();
        assertTrue(login.InstagramSignIn_Login());
    }

    @Test
    public void isTwitterLogin_Valid(){
        LoginActivity login = new LoginActivity();
        assertTrue(login.TwitterSignIn_Login());
    }

    @Test
    public void isGitHubLogin_Valid(){
        LoginActivity login = new LoginActivity();
        assertTrue(login.GitHubSignIn_Login());
    }
}
