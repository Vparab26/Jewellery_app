package com.example.kisan_suvidha;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.example.kisan_suvidha.R;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.net.Uri;


public class SchemesFragment extends Fragment {

    Button b1;


    public SchemesFragment() {
        // Required empty public constructor
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root = inflater.inflate(R.layout.fragment_schemes, container, false);

        b1 =  root.findViewById(R.id.lgt_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://pmkisan.gov.in/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        return root;
    }
}