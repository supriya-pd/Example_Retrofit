package com.example.pokemon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PokeDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="pokeList.db";
    public static final int DATABASE_VERSION=1;

    public PokeDBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE="CREATE TABLE "+ PokeContract.PokeEntry.TABLE_NAME+" ("+
                PokeContract.PokeEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ PokeContract.PokeEntry.COLUMN_NAME+" TEXT NOT NULL, "+
                PokeContract.PokeEntry.COLUMN_IMAGE+" TEXT NOT NULL, "+ PokeContract.PokeEntry.COLUMN_TIMESTAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+ PokeContract.PokeEntry.TABLE_NAME);
       onCreate(db);
    }
}
