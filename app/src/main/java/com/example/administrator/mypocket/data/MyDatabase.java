package com.example.administrator.mypocket.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 9/20/2016.
 */
public class MyDatabase {
    private SQLiteDatabase database;
    private LoginDbHelper helper;


   public MyDatabase(Context context) {
        helper = new LoginDbHelper(context);
    }

    private SQLiteDatabase openReadableDatabaseInstance() {
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase openWritableDatabaseInstance() {
        return helper.getWritableDatabase();
    }

    private void closeDatabaseConnection() {
        database.close();
        helper.close();
    }

    public long createUserDetails(String username, String password) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LoginContract.UserDetailsEntry.COLUMN_USERNAME, username);
        contentValues.put(LoginContract.UserDetailsEntry.COLUMN_PASSWORD, password);
        long value = database.insert(LoginContract.UserDetailsEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();

        return value;

    }
    private class LoginDbHelper extends SQLiteOpenHelper{

            //region SQL Statements
            private static final String SQL_CREATE_USER_DETAILS_TABLE = "CREATE TABLE " + LoginContract.UserDetailsEntry.TABLE_NAME + "("
                    + LoginContract.UserDetailsEntry._ID + " INTEGER PRIMARY KEY, "
                    + LoginContract.UserDetailsEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                    + LoginContract.UserDetailsEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

            private static final String SQL_DROP_USER_CREDENTIALS_TABLE = "DROP TABLE " + LoginContract.UserDetailsEntry.TABLE_NAME + ";";
            //endregion

            private static final String DATABASE_NAME = "LoginDetails.db";

            private static final int DATABASE_VERSION = 1;

            public LoginDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_USER_DETAILS_TABLE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                if (newVersion > oldVersion) {
                    db.execSQL(SQL_DROP_USER_CREDENTIALS_TABLE);
                    onCreate(db);
                }
            }


    }
    public boolean checkIfUsernameExists(String username){
        database = openReadableDatabaseInstance();

        String selection = LoginContract.UserDetailsEntry.COLUMN_USERNAME +" = ?";
        String[] selectionArgs = {username};

        Cursor cursor = database.query(LoginContract.UserDetailsEntry.TABLE_NAME, null, selection,
                selectionArgs, null, null, null);

        boolean userExists = false;

        if(cursor.moveToFirst()){
            userExists = true;
        } else {
            userExists = false;
        }

        closeDatabaseConnection();

        return userExists;
    }

   }
