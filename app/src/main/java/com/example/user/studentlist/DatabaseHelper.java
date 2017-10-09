package com.example.user.studentlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.UserModel;

/**
 * Created by User on 29/9/2560.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "student";
    private static final String ID = "id";
    private static final String TABLE_USER = "user";
    private static final String COL_ID = "stu";
    private static final String COL_NAME = "name";
    private static final String COL_CLASS = "room";

    private static final String CREATE_DATABASE =
        "CREATE TABLE" + TABLE_USER + "(" + ID +"INTEGER PRIMARY KEY,"
                +COL_ID+"VARCHAR(20),"+COL_NAME + "VARCHAR(50),"
                +COL_CLASS+"VARCHAR(20)"+")";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_DATABASE);
        Log.i(TAG,"CREATE DATABASE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int i,int i1){
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_USER);
        Log.i(TAG,"UPGRADE DATABASE");
        onCreate(db);
    }

    public List<UserModel>readUser(){
        List<UserModel> listUser=new ArrayList<>();
        String select = "SELECT * FROM"+TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        if (cursor.moveToFirst()) {
            do {
                UserModel model = new UserModel();
                model.setId(cursor.getString(cursor.getColumnIndex(ID)));
                model.setStu(cursor.getString(cursor.getColumnIndex(COL_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                model.setRoom(cursor.getString(cursor.getColumnIndex(COL_CLASS)));
                listUser.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return listUser;
    }

    public void insertUser(String stu,String name,String room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stu",stu);
        values.put("name",name);
        values.put("room",room);

        db.insert(TABLE_USER,null,values);
    }

    public void updateUser(String id,String stu,String name,String room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stu",stu);
        values.put("name",name);
        values.put("room",room);

        db.update(TABLE_USER,values,ID+"=?",new String[]{id});
        Log.i(TAG,"UPDATE DATABASE");
    }

    public void deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,ID+"=?",new String[]{id});
        Log.i(TAG,"DELETE DATABASE");
    }
}
