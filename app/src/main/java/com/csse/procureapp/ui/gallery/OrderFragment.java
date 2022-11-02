package com.csse.procureapp.ui.gallery;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.csse.dbaccess.DBAccess;
import com.csse.procureapp.R;
import com.csse.procureapp.databinding.FragmentOrderBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class OrderFragment extends Fragment {

    EditText itemName;
    EditText quantity;
    EditText expectedDate;
    EditText deliveryAdress;

    public static final String TAG="TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        OrderViewModel orderViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);

    binding = FragmentOrderBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        ////
        itemName=root.findViewById(R.id.itemname);
        quantity=root.findViewById(R.id.quantity);
        expectedDate=root.findViewById(R.id.expecteddate);
        deliveryAdress=root.findViewById(R.id.deliveryaddress);

        Button addOrder=(Button)root.findViewById(R.id.addorder);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(itemName.getText().toString())){
                    itemName.setError("This field cannot be empty!");
                    return;

                }
                if(TextUtils.isEmpty(quantity.getText().toString())){
                    quantity.setError("This field cannot be empty!");
                    return;

                }
                if(TextUtils.isEmpty(expectedDate.getText().toString())){
                    expectedDate.setError("This field cannot be empty!");
                    return;

                }
                if(TextUtils.isEmpty(deliveryAdress.getText().toString())){
                    deliveryAdress.setError("This field cannot be empty!");
                    return;

                }



                //firebase Authentication initialize
                fAuth=FirebaseAuth.getInstance();

                //firestore database initialize
                fStore= DBAccess.getDB();

                //get user id
                userID=fAuth.getCurrentUser().getUid();

                //database document reference
                DocumentReference documentReference=fStore.collection("order").document();

                Map<String, Object> order= new HashMap<>();
                order.put("itemName", itemName.getText().toString());
                order.put("quantity", quantity.getText().toString());
                order.put("expectedDate", expectedDate.getText().toString());
                order.put("deliveryAddress", deliveryAdress.getText().toString());
                order.put("status", "pending");
                order.put("delivery", "pending");
                order.put("uid", userID);

                //insert to database
                documentReference.set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Order added!");
                        Toast.makeText(getContext(),"Order Added!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Log.d(TAG, "OnFailure: "+e.toString());
                        Toast.makeText(getContext(),"Error!", Toast.LENGTH_SHORT).show();
                    }
                });


                EditText itemName=root.findViewById(R.id.itemname);
                EditText quantity=root.findViewById(R.id.quantity);
                EditText expectedDate=root.findViewById(R.id.expecteddate);
                EditText deliveryAdress=root.findViewById(R.id.deliveryaddress);

                itemName.getText().clear();
                quantity.getText().clear();
                expectedDate.getText().clear();
                deliveryAdress.getText().clear();


            }
        });
        ////



        return root;

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}