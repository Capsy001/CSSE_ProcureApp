package com.csse.dataadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.csse.models.Order;
import com.csse.procureapp.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

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

        // Populate the data into the template view using the data object
        item.setText(order.getItem());
        quantity.setText(order.getQuantity());
        // Return the completed view to render on screen


        Order order1=getItem(position);

        Button cancel=(Button) convertView.findViewById(R.id.cancel);
        cancel.setTag(order1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order=(Order)view.getTag();

                Toast.makeText(getContext(), order.getItem(), Toast.LENGTH_SHORT);
            }
        });



        return convertView;
    }
}
