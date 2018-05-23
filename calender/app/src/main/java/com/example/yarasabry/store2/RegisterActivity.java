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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText email,password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = (TextInputEditText)findViewById(R.id.register_email);
        password = (TextInputEditText) findViewById(R.id.register_password);
        confirmpassword = (TextInputEditText) findViewById(R.id.register_confirm_password);
        //kda 3mlt refernece 3la el 3 TextInputEditText

    }
   public void Register(View view){//call back lma ados 3la register
       String emailText,passwordText,confirmpasswordText;
       emailText = email.getText().toString();//kda gap elli gwa email w 7to fe string
       passwordText = password.getText().toString();
       confirmpasswordText = confirmpassword.getText().toString();
       //------------hayshof b2a elli hay5osh sa7 wla --validation
       if(emailText.isEmpty() || emailText.equals(" ")){
           email.setError("Fill here please !");// hatl3 error 3ndl email TextInputEditText
           return ;
       }
       //hna 5li balk an elpassword fe firebase at least 8 characters
       if(passwordText.isEmpty() || passwordText.equals(" ")){
           password.setError("Fill here please !");// hatl3 error 3ndl password TextInputEditText
           return ;
       }
       if(confirmpasswordText.isEmpty() || confirmpasswordText.equals(" ")){
           confirmpassword.setError("Fill here please !");// hatl3 error 3ndl confirmpassword TextInputEditText
           return ;
       }
       // check if password at least 8 characters
       if(passwordText.length()<8){
           password.setError("length must be greater than 8!");// hatl3 error 3ndl password TextInputEditText
           return ;
       }
       //------------------check if password == confirm password
       if(!confirmpasswordText.equals(passwordText)){
           password.setError("Password not Match ");// hatl3 error 3ndl password TextInputEditText
           return ;

       }


       mAuth.createUserWithEmailAndPassword(emailText, passwordText)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           //Log.e( "my_store", "createUserWithEmail:success");
                           //tag(tag elli hsreach beha ,msg (elli hatzher fe android monitor))
                           Intent home = new Intent(RegisterActivity.this, HomeActivity.class);
                           startActivity(home);
                           finish();

                       } else {
                           // If sign in fails, display a message to the user.
                           Log.e("my_store", "createUserWithEmail:failure", task.getException());

                       }
                   }
               });
   }

}
