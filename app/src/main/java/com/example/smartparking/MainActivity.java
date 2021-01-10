package com.example.smartparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String s;
    int isBookedByApp1;
    int isBookedByApp2;
    public static int vl1, vl2, vl3, vl4;

    Data d;
    Button btn;
    Spinner spinner;
    LinearLayout layout;
    TextView sl1, sl2, sl3, sl4;
    ProgressDialog progressDialog;
    TextView slot1BookTV, slot2BookTV;
    DatabaseReference databaseReference;
    String[] a = {"Select Location", "Parking 1", "Parking 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIAndVariables();
        setClickActions();

    }

    private void setClickActions() {

        slot1BookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Data data = new Data(1, d.isBookedByApp2, d.isBookedByApp3, d.isBookedByApp4, 0, d.slot2, d.slot3, d.slot4);
                bookSlot(data);

            }
        });

        slot2BookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Data data = new Data(d.isBookedByApp1, 1, d.isBookedByApp3, d.isBookedByApp4, d.slot1, 0, d.slot3, d.slot4);
                bookSlot(data);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String r = spinner.getSelectedItem().toString();
                s = "";

                if (r.equals("Select Location")) {
                    Toast.makeText(MainActivity.this, "Please Select Location !!", Toast.LENGTH_SHORT).show();
                    layout.setVisibility(View.GONE);
                } else if (r.equals("Parking 1")) {
                    s = "Parking1";
                } else {
                    s = "Parking2";
                }

                if (!s.equals("")) {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Getting available slots....");
                    progressDialog.setTitle("Loading Result");
                    progressDialog.show();

                    getResult(s);

                } else {
                    Toast.makeText(MainActivity.this, "Please Select Location !!", Toast.LENGTH_SHORT).show();
                    layout.setVisibility(View.GONE);
                }


            }
        });
    }

    private void bookSlot(Data data) {
        databaseReference.setValue(data);
    }

    private void initUIAndVariables() {
        btn = findViewById(R.id.btn);
        sl1 = findViewById(R.id.slot1);
        sl2 = findViewById(R.id.slot2);
        sl3 = findViewById(R.id.slot3);
        sl4 = findViewById(R.id.slot4);
        spinner = findViewById(R.id.spinner);
        layout = findViewById(R.id.result);

        slot1BookTV = findViewById(R.id.idTVBookNowSlot1);
        slot2BookTV = findViewById(R.id.idTVBookNowSlot2);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, a);
        spinner.setAdapter(ad);
    }

    public void getResult(String s) {
        databaseReference = FirebaseDatabase.getInstance().getReference(s);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                d = dataSnapshot.getValue(Data.class);

                if (d != null) {
                    vl1 = d.slot1;
                    vl2 = d.slot2;
                    isBookedByApp1 = d.isBookedByApp1;
                    isBookedByApp2 = d.isBookedByApp2;

                    setBookings(isBookedByApp1, isBookedByApp2);

                    if (vl1 == 1) {
                        sl3.setText("Available");
                        sl3.setTextColor(Color.parseColor("#4ed442"));
                    } else {
                        sl3.setText("Booked");
                        sl3.setTextColor(Color.parseColor("#de1818"));
                    }

                    if (vl2 == 1) {
                        sl4.setText("Available");
                        sl4.setTextColor(Color.parseColor("#4ed442"));
                    } else {
                        sl4.setText("Booked");
                        sl4.setTextColor(Color.parseColor("#de1818"));
                    }

                    progressDialog.dismiss();
                    layout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "No record Found....", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void setBookings(int isBookedByApp1, int isBookedByApp2) {

        if (isBookedByApp1 == 1) {
            slot1BookTV.setText(this.getResources().getString(R.string.booked));
            slot1BookTV.setBackgroundResource(R.drawable.button_booked_bg);
            slot1BookTV.setEnabled(false);
        } else {
            slot1BookTV.setText(this.getResources().getString(R.string.book_now));
            slot1BookTV.setBackgroundResource(R.drawable.button_book_now_bg);
            slot1BookTV.setEnabled(true);
        }

        if (isBookedByApp2 == 1) {
            slot2BookTV.setText(this.getResources().getString(R.string.booked));
            slot2BookTV.setBackgroundResource(R.drawable.button_booked_bg);
            slot2BookTV.setEnabled(false);
        } else {
            slot2BookTV.setText(this.getResources().getString(R.string.book_now));
            slot2BookTV.setBackgroundResource(R.drawable.button_book_now_bg);
            slot2BookTV.setEnabled(true);
        }
    }
}