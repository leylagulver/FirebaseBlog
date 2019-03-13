package com.example.user.firebaseblog;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mAuth;

EditText emailText;
EditText passwordText;


            @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText =findViewById(R.id.passwordText);
        }

    public void  signUp(View view) {

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


     @Override

    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()) {

        Toast.makeText(getApplicationContext(), "Kullanıcı Oluşturuldu" , Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),PostActivity.class);
        startActivity(intent);
        } }

         }).addOnFailureListener(this, new OnFailureListener() {


     @Override
    public void onFailure(@NonNull Exception e) {

        Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
     }
     });
            }

     public  void signIn(View view) {
         mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

     @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
         if (task.isSuccessful()) {


          FirebaseUser user = mAuth.getCurrentUser();
          Intent intent = new Intent(getApplicationContext(),PostActivity.class);
          startActivity(intent);

          }}
          }).addOnFailureListener(this, new OnFailureListener() {

      @Override
    public void onFailure(@NonNull Exception e) {

          Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                   }
               });



            }

         }



