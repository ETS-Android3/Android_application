package com.example.firstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FriendRequestDBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "friend_request";
    private static final String COLUMN_PHONENUM = "sender_phone_num";
    private static final String COLUMN_FIRST = "first";
    private static final String COLUMN_SECOND = "second";
    private static final String COLUMN_THIRD = "third";
    private static final String COLUMN_FOURTH = "fourth";
    private static final String COLUMN_FRIENDS = "friends";

    public FriendRequestDBHelper(Context context) {
        super(context, "FriendsRequestDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_PHONENUM + " TEXT, "
                + COLUMN_FIRST + " TEXT, "
                + COLUMN_SECOND + " TEXT, "
                + COLUMN_THIRD + " TEXT, "
                + COLUMN_FOURTH + " TEXT, "
                + COLUMN_FRIENDS + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        onCreate(db);

    }

    public void addTable(String myPhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void sendRequest1(String myPhoneNum, String targetPhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        cv.put(COLUMN_FIRST, targetPhoneNum);
        //db.insert(TABLE_NAME, null,cv);
        db.update(TABLE_NAME, cv, "sender_phone_num = ?", new String[]{myPhoneNum});
        db.close();
    }

    public void sendRequest2(String myPhoneNum, String targetPhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        cv.put(COLUMN_SECOND, targetPhoneNum);
        db.update(TABLE_NAME, cv, "sender_phone_num = ?", new String[]{myPhoneNum});
        db.close();
    }

    public void sendRequest3(String myPhoneNum, String targetPhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        cv.put(COLUMN_THIRD, targetPhoneNum);
        db.update(TABLE_NAME, cv, "sender_phone_num = ?", new String[]{myPhoneNum});
        db.close();
    }

    public void sendRequest4(String myPhoneNum, String targetPhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        cv.put(COLUMN_FOURTH, targetPhoneNum);
        db.update(TABLE_NAME, cv, "sender_phone_num = ?", new String[]{myPhoneNum});
        db.close();
    }

    public void acceptRequest(int index, String myPhoneNum, String friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv.put(COLUMN_PHONENUM, myPhoneNum);
        cv.put(COLUMN_FRIENDS, friend);
        switch(index) {
            case 1:
                cv2.put(COLUMN_FIRST,(String)null);
                break;
            case 2:
                cv2.put(COLUMN_SECOND,(String)null);
                break;
            case 3:
                cv2.put(COLUMN_THIRD,(String)null);
                break;
            case 4:
                cv2.put(COLUMN_FOURTH,(String)null);
                break;

        }
        db.insert(TABLE_NAME, null, cv);
        db.update(TABLE_NAME, cv2, "sender_phone_num = ?", new String[]{friend});
        db.close();
    }
}

