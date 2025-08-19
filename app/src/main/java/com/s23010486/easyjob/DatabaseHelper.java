package com.s23010486.easyjob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REMEMBER_ME = "remember_me";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_REMEMBER_ME + " INTEGER,"
                + COLUMN_DOB + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_MOBILE + " TEXT,"
                + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long addUser(String username, String password, String email, String name, int rememberMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REMEMBER_ME, rememberMe);
        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();
        return newRowId;
    }

    public void logAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.close();
    }

    public int updateUser(String username, String password, String email, String name, String dob, String gender, String mobile, String address, int rememberMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (password != null && !password.isEmpty()) values.put(COLUMN_PASSWORD, password);
        if (email != null && !email.isEmpty()) values.put(COLUMN_EMAIL, email);
        if (name != null && !name.isEmpty()) values.put(COLUMN_NAME, name);
        if (dob != null && !dob.isEmpty()) values.put(COLUMN_DOB, dob);
        if (gender != null && !gender.isEmpty()) values.put(COLUMN_GENDER, gender);
        if (mobile != null && !mobile.isEmpty()) values.put(COLUMN_MOBILE, mobile);
        if (address != null && !address.isEmpty()) values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_REMEMBER_ME, rememberMe);
        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{username});
        db.close();
        return rowsAffected;
    }

    public boolean isValidUser(String email, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
            cursor = db.rawQuery(query, new String[]{email, password});
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error validating user: " + e.getMessage());
            return false;
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

    }


}