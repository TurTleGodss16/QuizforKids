package com.example.quizforkids;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;


import androidx.annotation.Nullable;

public class databaseHelper_score extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "a.db";
    public static final String TABLE_NAME = "scores"; //Use for storing the users' score in the database

    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "SCORE";
    public static final String COL_4 = "CORRECT";
    public static final String COL_5 = "INCORRECT";

    public databaseHelper_score(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //super(context, name, factory, version); //for testing purposes
        //When you are creating the instance of the databaseHelper you need to send the name of the database as input parameter. If null than you are using in-memory database
        super(context, DATABASE_NAME, null, version);
    }

    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT, SCORE INTEGER, CORRECT INTEGER, INCORRECT INTEGER)");
    }

    // called when the database needs to be upgraded to the new schema version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //adding a record
    public boolean insertData(String username, int score, int correct, int incorrect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //This class is used to store a
        //set of values that the ContentResolver can process
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, score);
        contentValues.put(COL_4, correct);
        contentValues.put(COL_5, incorrect);

        long result = db.insert(TABLE_NAME, null, contentValues);
        // null - the framework does not insert a row when there are no values

        if (result == -1)
            return false;
        else
            return true;
    }


    //retrieving all records
    public Cursor viewAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursors contain the resultset of a query, and basically point to a single row in the resultset
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    //retrieving single record matching the id
    public Cursor viewRecord(String username, String score, String correct, String incorrect) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=" +id, null);

        String[] projection = {COL_2, COL_3, COL_4, COL_5};
        String selection = COL_2 + " = ? AND " + COL_3 + " = ? AND " + COL_4 + " = ? AND " + COL_5 + " = ?";
        String[] selectionArgs = {username, score, correct, incorrect};
        Cursor res = db.query(
                TABLE_NAME,         // The table to query
                projection,         // The array of columns to return (pass null to get all)
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order (or null in no need to sort)
        );
        return res;
    }

    //updating the record matching the id
    public boolean updateRecord(String id, String username, String score, String correct, String incorrect){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, score);
        contentValues.put(COL_4, correct);
        contentValues.put(COL_5, incorrect);

        db.update(TABLE_NAME, contentValues,"ID = ?",
                new String[] {id});
        return true;
    }

    // Get the total score of a specific user
    public int getTotalScore(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int totalScore = 0;

        String selection = COL_2 + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME, new String[]{"SUM(" + COL_3 + ")"}, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            totalScore = cursor.getInt(0);
        }
        cursor.close();

        return totalScore;
    }

    //deleting the record matching the id
    public Integer deleteRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}