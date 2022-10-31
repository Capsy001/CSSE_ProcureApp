package com.csse.dbaccess;

import com.google.firebase.firestore.FirebaseFirestore;

public class DBAccess {

    private static FirebaseFirestore db=FirebaseFirestore.getInstance();

    private DBAccess(){
    }

    public static FirebaseFirestore getDB(){

        return db;
    }


}
