package com.csse.procureapp.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.csse.dataadapter.OrderAdapter;
import com.csse.models.Order;
import com.csse.procureapp.R;
import com.csse.procureapp.databinding.FragmentListBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    public static final String TAG="TAG";
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    String userID=fAuth.getCurrentUser().getUid();

    ArrayList<Order> arrayOfOrders;


private FragmentListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ListViewModel listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);

    binding = FragmentListBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        // Construct the data source
        arrayOfOrders = new ArrayList<Order>();
        // Create the adapter to convert the array to views
        OrderAdapter adapter = new OrderAdapter(getContext(), arrayOfOrders);
        // Attach the adapter to a ListView
        ListView listView = (ListView) root.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //
        CollectionReference collectionReference=fStore.collection("order");

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    arrayOfOrders.clear();
                    List<DocumentSnapshot> listDocument = task.getResult().getDocuments();


                    for(DocumentSnapshot document : listDocument){

                        //getting institutes

                        String id=document.getId();
                        String itemName=document.get("itemName").toString();
                        String quantity=document.get("quantity").toString();
                        String status=document.get("status").toString();
                        String uid=document.get("uid").toString();
                        String expectedDate=document.get("expectedDate").toString();
                        String deliveryAddress=document.get("deliveryAddress").toString();
                        String delivery=document.get("delivery").toString();

                        Order newOrder=new Order(id, itemName, status, delivery, deliveryAddress, quantity, uid, expectedDate);
                        arrayOfOrders.add(newOrder);
                        //add all object items to a list

                        //custom function to add items to the adapter
                        listviewAdd(root);

                    }

                }
            }
        });

        //

        return root;
    }


    private void listviewAdd(View root) {
        if(arrayOfOrders.isEmpty()){
            Log.d(TAG, "Empty!");
        }

        OrderAdapter listAdapter=new OrderAdapter(getActivity(),arrayOfOrders);
        ListView listView=root.findViewById(R.id.listview);
        listView.setAdapter(listAdapter);


        Log.d(TAG, "Done!");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}