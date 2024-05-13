package com.example.thebloomroom;

import static com.example.thebloomroom.R.id.recycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Order> orderList;
    MyAdapter myAdapter;
    FirebaseFirestore fs;

    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);



            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Fetching Data...");
            progressDialog.show();

            recyclerView = findViewById(R.id.recycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            fs = FirebaseFirestore.getInstance();
            orderList = new ArrayList<Order>();
            myAdapter = new MyAdapter(ViewOrder.this, orderList);

            recyclerView.setAdapter(myAdapter);

            order();


        }

        private void order() {

            fs.collection("ordersDb").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("firebase error", error.getMessage());
                        return;
                    }

                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            orderList.add(dc.getDocument().toObject(Order.class));
                        }

                        myAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }
            });
        }
    }
