package com.example.prm3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_ID = "email";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NAME1 = "password";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_ID +
                " TEXT PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_NAME1 + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getEmail());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_NAME1, user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public User findHandler(String email) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + "'" + email + "'";
        System.out.println(email);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            System.out.println("tutaj" + cursor.getString(0));
            user.setEmail(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public List<String> loadData(){
        List<String> result = new ArrayList<>();
        String query = "Select "+COLUMN_ID+","+COLUMN_NAME1 + " FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            result.add(result_0);
            result.add(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }
}
