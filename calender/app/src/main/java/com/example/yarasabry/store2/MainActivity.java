package com.example.yarasabry.store2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (TextInputEditText)findViewById(R.id.login_email);
        password =(TextInputEditText)findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        /*if(currentUser != null) {//lw hwa mesh b null yb2a fe user asln 3ndii fe database
            Intent home = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }*/
    }
    public void Login(View view){
        String emailText,passwordText;
        emailText = email.getText().toString();
        passwordText = password.getText().toString();


        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.e("my_store", "signInWithEmail:success");
                            Intent home = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(home);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("my_store", "signInWithEmail:failure", task.getException());

                        }
                    }
                });


    }
    public void openRegister(View view){
        Intent register = new Intent ( this,RegisterActivity.class);
        startActivity(register);
    }
}
