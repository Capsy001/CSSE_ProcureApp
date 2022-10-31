package com.csse.procureapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.csse.dbaccess.DBAccess;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void signIn(View view){

//        if(fAuth.getCurrentUser() != null){
//
//            Intent intent1=new Intent(this, StudentDashboard.class);
//            startActivity(intent1);
//
//            return;
//
//        }

        Intent intent1=new Intent(this, Login.class);
        startActivity(intent1);

    }

    public void signUp(View view){

        Intent intent1=new Intent(this, SignUp.class);
        startActivity(intent1);

    }
}