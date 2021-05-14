package com.example.laptrinhandroid_roomdatabase_customlistview.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laptrinhandroid_roomdatabase_customlistview.room.entity.Location;

import java.util.List;

@Dao
public interface LocationDAO {
    @Query("select * from location")
    List<Location> getAll();

    @Insert
    void insertAll(Location... locations);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

}
