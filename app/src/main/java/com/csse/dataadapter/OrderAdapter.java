package com.csse.dataadapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.csse.dbaccess.DBAccess;
import com.csse.models.Order;
import com.csse.procureapp.R;
import com.csse.procureapp.UserDashboard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    FirebaseFirestore fStore= DBAccess.getDB();


    public OrderAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Order order = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
        }
        // Lookup view for data population
        TextView item = (TextView) convertView.findViewById(R.id.itemview1);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantityview);
        TextView status = (TextView) convertView.findViewById(R.id.statusview);
        TextView delivery = (TextView) convertView.findViewById(R.id.deliveryview);
        TextView date = (TextView) convertView.findViewById(R.id.expectedview);


        // Populate the data into the template view using the data object
        item.setText(order.getItem());
        quantity.setText(order.getQuantity());
        status.setText("Status : "+order.getStatus());
        delivery.setText("Delivered : "+order.getDelivered());
        date.setText("Expected : "+order.getExpectedDate());

        // Return the completed view to render on screen


        Order order1=getItem(position);

        Button cancel=(Button) convertView.findViewById(R.id.cancel);
        Button delivered=(Button) convertView.findViewById(R.id.deliveredbtn);
        Button delete=(Button) convertView.findViewById(R.id.deletebtn);


        TextView item1=convertView.findViewById(R.id.itemview1);
        View layout=convertView.findViewById(R.id.layout1);

        cancel.setTag(order1);
        delivered.setTag(order1);
        delete.setTag(order1);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order=(Order)view.getTag();

                fStore.collection("order").document(order.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Order deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Order order=(Order)view.getTag();

                fStore.collection("order").document(order.getId()).update("delivery", "delivered").
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "Marked as delivered!", Toast.LENGTH_SHORT).show();



                            }
                        });

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order=(Order)view.getTag();

                fStore.collection("order").document(order.getId()).update("status", "cancelled").
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Order cancelled!", Toast.LENGTH_SHORT).show();



                    }
                });
            }
        });



        return convertView;
    }
}
