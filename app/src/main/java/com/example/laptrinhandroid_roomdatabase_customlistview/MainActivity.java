package com.example.laptrinhandroid_roomdatabase_customlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.laptrinhandroid_roomdatabase_customlistview.room.AppDatabase;
import com.example.laptrinhandroid_roomdatabase_customlistview.room.dao.LocationDAO;
import com.example.laptrinhandroid_roomdatabase_customlistview.room.entity.Location;

public class MainActivity extends AppCompatActivity {
    LocationDAO locationDAO;
    RecyclerView rcv_location;
    EditText edt_location;
    Button btt_add,btt_cancel;
    LocationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"list-location")
                .allowMainThreadQueries().build();
        locationDAO = appDatabase.locationDAO();
        //
        rcv_location = findViewById(R.id.rcv_location);
        edt_location= findViewById(R.id.edt_location);
        btt_add= findViewById(R.id.btt_add);
        btt_cancel = findViewById(R.id.btt_cancel);

        //
//        adapter = new LocationAdapter(this,locationDAO.getAll());
//        rcv_location.setAdapter(adapter);
        setData();
        rcv_location.setLayoutManager(new GridLayoutManager(this,1));

        //

        btt_add.setOnClickListener(v->{
            String txt_location = edt_location.getText().toString();
            if (!txt_location.equalsIgnoreCase("")) {
                locationDAO.insertAll(new Location(txt_location));
                setData();
                edt_location.setText("");
            }
        });
    }

    private void setData() {
        adapter = new LocationAdapter(this,locationDAO.getAll(),locationDAO);
        rcv_location.setAdapter(adapter);
    }
}