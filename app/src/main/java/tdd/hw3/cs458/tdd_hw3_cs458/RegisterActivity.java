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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText mEmail, mPassword, mName, mGender, mAge, mCity;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.emailRegister);
        mPassword = (EditText) findViewById(R.id.passwordRegister);
        mName = (EditText) findViewById(R.id.name);
        mGender = (EditText) findViewById(R.id.gender);
        mAge = (EditText) findViewById(R.id.age);
        mCity = (EditText) findViewById(R.id.city);
        mRegister = (Button) findViewById(R.id.register);




        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();
                final String age = mAge.getText().toString();
                final String gender = mGender.getText().toString();
                final String city = mCity.getText().toString();
                systemRegister(email, password,name, age, gender, city);


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
    ///////////////////////////
    public boolean systemRegister(final String email, final String password, final String name, final String age, final String gender, final String city) {

        if((email.equals("") || password.equals("") || name.equals("")|| age.equals("") || gender.equals("")|| city.equals("")) && !isEmailValid(email) && !isPasswordValid(password) && !isGenderValid(gender)){
            //Toast.makeText(RegisterActivity.this, "Write true and valid input!!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Write true and valid input!!!",Toast.LENGTH_SHORT).show();
                    }


                }
            });

            return true;
        }

    }

///////////////////////////////////////
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
    public boolean isPasswordValid(String password){
        if(password.length() == 6){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isGenderValid(String gender) {
        if(gender.toUpperCase().equals("MALE") || gender.toUpperCase().equals("FEMALE")){
            return true;
        }
        else{
            return false;
        }
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
}
