package com.example.laptrinhandroid_roomdatabase_customlistview.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laptrinhandroid_roomdatabase_customlistview.room.dao.LocationDAO;
import com.example.laptrinhandroid_roomdatabase_customlistview.room.entity.Location;

@Database(entities = {Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDAO locationDAO();
}
