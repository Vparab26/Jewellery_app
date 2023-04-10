package com.example.kisan_suvidha.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kisan_suvidha.R;
import com.example.kisan_suvidha.models.MyCartModel;
import com.example.kisan_suvidha.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    Context context;
    List<MyOrdersModel> myOrdersModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyOrdersAdapter(Context context, List<MyOrdersModel> myOrdersModelList) {
        this.context = context;
        this.myOrdersModelList = myOrdersModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myOrdersModelList.get(position).getProductName());
        holder.price.setText(myOrdersModelList.get(position).getProductPrice());
        holder.date.setText(myOrdersModelList.get(position).getCurrentDate());
        holder.time.setText(myOrdersModelList.get(position).getCurrentTime());
        holder.quantity.setText(myOrdersModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myOrdersModelList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Currentuser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder")
                        .document(myOrdersModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    myOrdersModelList.remove(myOrdersModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        totalPrice = totalPrice + myOrdersModelList.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("TotalAmount", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }


    @Override
    public int getItemCount() {
        return myOrdersModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date, time, quantity, totalPrice;
        ImageView deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            deleteItem = itemView.findViewById(R.id.delete_s);

        }
    }
}

