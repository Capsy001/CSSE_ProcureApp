package com.csse.procureapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.csse.procureapp.R;
import com.csse.procureapp.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    public static final String TAG = "TAG";
    TextView name;
    TextView email;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;



private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

    binding = FragmentProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        /////////
        name= (TextView)root.findViewById(R.id.name_profile);
        email= (TextView)root.findViewById(R.id.email_profile);

        fAuth= FirebaseAuth.getInstance();  //initialize firebase authentication instance
        fStore= FirebaseFirestore.getInstance();    ////initialize firebase database instance
        userID= fAuth.getCurrentUser().getUid();    //get current user's UserID

        DocumentReference documentReference=fStore.collection("users").document(userID);


        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot value=task.getResult();

                    name.setText(value.getString("name").toString());
                    email.setText(value.getString("email").toString());
                }
            }
        });

        ////////

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}