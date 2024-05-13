package com.example.thebloomroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OrderNow extends AppCompatActivity {

    Button order;
    EditText name, address, phone, category;
    FirebaseFirestore fs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        name = findViewById(R.id.Nameed);
        address = findViewById(R.id.Address);
        phone = findViewById(R.id.Phone);
        category = findViewById(R.id.Cate);
        order = findViewById(R.id.ord);

        fs = FirebaseFirestore.getInstance();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = name.getText().toString();
                String b = address.getText().toString();
                String c = phone.getText().toString();
                String d = category.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("name", a);
                map.put("address", b);
                map.put("phone", c);
                map.put("category", d);

                fs.collection("ordersDb").add(map)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(OrderNow.this, "Order Placed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OrderNow.this, "Erro - " + e , Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}