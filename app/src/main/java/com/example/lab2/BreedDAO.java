package com.example.lab2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BreedDAO {

    @Query("SELECT * FROM breeds")
    List<Breed> getAllBreeds();

    @Insert
    void insert(Breed breed);

    @Insert
    void insertAll(List<Breed> breeds);

}
