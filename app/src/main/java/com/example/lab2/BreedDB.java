package com.example.lab2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Breed.class}, version = 1)
public abstract class BreedDB extends RoomDatabase {
    public abstract BreedDAO breedDAO();
    private static volatile BreedDB INSTANCE;

    public static BreedDB getDatabase (final Context context) {
        if (INSTANCE == null) {
            synchronized (BreedDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BreedDB.class, "breedDB").build();
                }
            }
        }
        return INSTANCE;
    }
}
