package com.example.quizforkids;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;


import androidx.annotation.Nullable;

public class databaseHelper_register extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quiz.db";
    public static final String TABLE_NAME = "accounts"; //Use for storing users' account in the database
    public static final String TABLE_NAME_QUESTIONS = "questions"; //Use for storing questions in the database
    public static final String TABLE_NAME_SCORE = "scores"; //Use for storing the users' score in the database

    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";

    public static final String COL_1_T2 = "ID";
    public static final String COL_2_T2 = "USERNAME";
    public static final String COL_3_T2 = "SCORE";
    public static final String COL_4_T2 = "CORRECT";
    public static final String COL_5_T2 = "INCORRECT";

    public databaseHelper_register(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //super(context, name, factory, version); //for testing purposes
        //When you are creating the instance of the databaseHelper you need to send the name of the database as input parameter. If null than you are using in-memory database
        super(context, DATABASE_NAME, factory, version);
    }

    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //First table
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT, PASSWORD TEXT)");

        //Second table
        db.execSQL("CREATE TABLE " + TABLE_NAME_SCORE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT, SCORE INTEGER, CORRECT INTEGER, INCORRECT INTEGER)");
    }

    // called when the database needs to be upgraded to the new schema version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCORE);
        onCreate(db);
    }

    //adding a record
    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //This class is used to store a
        //set of values that the ContentResolver can process
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        // null - the framework does not insert a row when there are no values

        if (result == -1)
            return false;
        else
            return true;
    }

    //adding a record_Table 2
    public boolean insertDataTable2(String username, String score, String correct, String incorrect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //This class is used to store a
        //set of values that the ContentResolver can process
        contentValues.put(COL_2_T2, username);
        contentValues.put(COL_3_T2, score);
        contentValues.put(COL_4_T2, correct);
        contentValues.put(COL_5_T2, incorrect);

        long result = db.insert(TABLE_NAME_SCORE, null, contentValues);
        // null - the framework does not insert a row when there are no values

        if (result == -1)
            return false;
        else
            return true;
    }

    //retrieving all records - Table 1
    public Cursor viewAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursors contain the resultset of a query, and basically point to a single row in the resultset
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    //retrieving all records - Table 2
    public Cursor viewAllRecordsTable2() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursors contain the resultset of a query, and basically point to a single row in the resultset
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_SCORE, null);
        return res;
    }

    //retrieving single record matching the id - Table 1
    public Cursor viewRecord(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=" +id, null);

        String[] projection = {COL_2, COL_3};
        String selection = COL_2 + " = ? AND " + COL_3 + " = ?";
        String[] selectionArgs = {username, password};
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

    //retrieving single record matching the id - Table 2
    public Cursor viewRecordTable2(String username, String score, String correct, String incorrect) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=" +id, null);

        String[] projection = {COL_2_T2, COL_3_T2, COL_4_T2, COL_5_T2};
        String selection = COL_2_T2 + " = ? AND " + COL_3 + " = ? AND " + COL_4_T2 + " = ? AND " + COL_5_T2 + " = ?";
        String[] selectionArgs = {username, score, correct, incorrect};
        Cursor res = db.query(
                TABLE_NAME_SCORE,         // The table to query
                projection,         // The array of columns to return (pass null to get all)
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order (or null in no need to sort)
        );
        return res;
    }

    //updating the record matching the id - Table 1
    public boolean updateRecord(String id, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);

        db.update(TABLE_NAME, contentValues,"ID = ?",
                new String[] {id});
        return true;
    }

    //updating the record matching the id - Table 2
    public boolean updateRecordTable2(String id, String username, String score, String correct, String incorrect){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_T2, id);
        contentValues.put(COL_2_T2, username);
        contentValues.put(COL_3_T2, score);
        contentValues.put(COL_4_T2, correct);
        contentValues.put(COL_5_T2, incorrect);

        db.update(TABLE_NAME_SCORE, contentValues,"ID = ?",
                new String[] {id});
        return true;
    }

    //deleting the record matching the id - table 1
    public Integer deleteRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    //deleting the record matching the id - table 2
    public Integer deleteRecordTable2(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME_SCORE, "ID = ?",new String[] {id});
    }
}