package com.csse.procureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    public static final String TAG="TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); /* overide */

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){

            Intent intent1=new Intent(this, Dashboard.class);
            startActivity(intent1);

            return;

        }
    }

    public void login(View view){

        EditText lEmail=findViewById(R.id.emailL);
        EditText lPassword=findViewById(R.id.passwordL);


        String email=lEmail.getText().toString().trim();
        String password= lPassword.getText().toString();



        if(TextUtils.isEmpty(email)){
            lEmail.setError("Email cannot be empty");
            return;

        }

        if(TextUtils.isEmpty(password)){
            lPassword.setError("Password cannot be empty");
            return;

        }  /*validation */












        //check to login
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this,"Logged In", Toast.LENGTH_SHORT).show();

//                startActivity(new Intent(Login.this, Dashboard.class));

                startActivity(new Intent(Login.this, UserDashboard.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "OnFailure Login: "+e.toString());
                Toast.makeText(Login.this,"Error "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });



// login



    }


}