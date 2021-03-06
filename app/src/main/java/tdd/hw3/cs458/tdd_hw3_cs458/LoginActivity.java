package tdd.hw3.cs458.tdd_hw3_cs458;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mLogin, mRegisterButton, mGoogle, mFacebook, mTwitter,mInstagram, mGithub;
    private String email, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        mGoogle = (Button) findViewById(R.id.googleButton);
        mFacebook = (Button) findViewById(R.id.facebookButton);
        mTwitter = (Button) findViewById(R.id.twitterButton);
        mInstagram = (Button) findViewById(R.id.instagramButton);
        mGithub = (Button) findViewById(R.id.githubButton);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); //REFACTOR CODE
                    startActivity(intent); //REFACTOR CODE
                    finish();
                    return;
                }
            }
        };

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                return;
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                signIn(email,password); //REFACTOR CODE

            }
        });

        //REFACTOR METHOD
        mGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GoogleSignIn_Login()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
        //REFACTOR METHOD
        mFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FacebookSignIn_Login()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
        //REFACTOR METHOD
        mTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TwitterSignIn_Login()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
        //REFACTOR METHOD
        mInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InstagramSignIn_Login()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
        //REFACTOR METHOD
        mGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GitHubSignIn_Login()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    //Methods For Login
    //REFACTOR METHOD
    public boolean signIn(String email, String password){

        if(isEmailValid(email) && !email.equals("") && !password.equals("") && isPasswordValid(password)){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        //Toast.makeText(LoginActivity.this, "Email or Password is Incorrect",Toast.LENGTH_SHORT).show(); REFACTOR CODE
                    }
                    else{
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)); //REFACTOR CODE
                        finish();
                        return;
                    }
                }
            });
            return true;
        }
        else{
            //Toast.makeText(LoginActivity.this, "Email or Password is Incorrect",Toast.LENGTH_SHORT).show(); REFACTOR CODE
            return false;
        }
    }

    //REFACTOR CODE
    public boolean isEmailValid(String email){
        if(!email.equals("")){
            if(EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }
    //REFACTOR METHOD
    public boolean isPasswordValid(String password){
        if(password.length() == 6){
            return true;
        }
        else{
            return false;
        }
    }
    //REFACTOR METHOD
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    //REFACTOR METHOD
    public boolean GoogleSignIn_Login() {
        return true;
    }

    //REFACTOR METHOD
    public boolean FacebookSignIn_Login() {
        return true;
    }

    //REFACTOR METHOD
    public boolean InstagramSignIn_Login() {
        return true;
    }

    //REFACTOR METHOD
    public boolean TwitterSignIn_Login() {
        return true;
    }

    //REFACTOR METHOD
    public boolean GitHubSignIn_Login() {
        return true;
    }
}
