package com.example.kisan_suvidha.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kisan_suvidha.R;

public class PaymentActivity extends AppCompatActivity {

    Button MakePayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        MakePayment=findViewById(R.id.payment_button);


        MakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, PlacedOrderActivity.class));
            }
        });

    }
}





