package com.example.lab2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "breeds")
public class Breed {
    private static int count = 1;

    @PrimaryKey
    @ColumnInfo(name = "breed_id")
    private int id;
    @ColumnInfo(name = "breed_name")
    private String mName;
    @ColumnInfo(name = "temperament")
    private String mTemperament;
    @ColumnInfo(name = "description")
    private String mDescription;

    public Breed(String mName, String mTemperament, String mDescription) {
        this.id = count++;
        this.mName = mName;
        this.mTemperament = mTemperament;
        this.mDescription = mDescription;
    }

    public Breed() {
    }

    public String getName() {
        return mName;
    }

    public String getTemperament() {
        return mTemperament;
    }

    public static long getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return mDescription;
    }

    public static void setCount(int count) {
        Breed.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setTemperament(String mTemperament) {
        this.mTemperament = mTemperament;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
