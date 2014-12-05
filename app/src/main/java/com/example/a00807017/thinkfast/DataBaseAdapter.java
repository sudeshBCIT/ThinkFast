package com.example.a00807017.thinkfast;

/**
 * This class is the DAO (Data Access Object) class for the Database
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {
    private static DataBaseAdapter oneInstance;    // One instance of db
    private static final String DATABASE_NAME = "thinkfast.db";
    private static final int DATABASE_VERSION = 1;


    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "THINKFAST" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text, PASSWORD text, " +
            "SCORE1 integer, SCORE2 integer, SCORE3 integer ); ";

    public SQLiteDatabase db;      // Context of the application using the database.
    private final Context context;    // Database open/upgrade helper
    private DataBaseHelper dbHelper;  // New database created

    // Method for call to DataBaseHelper to create database
    private DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to retrieve current instance of database adapter if one already exists
    public static DataBaseAdapter getOneInstance(Context _context) {
        if (oneInstance == null) {
            oneInstance = new DataBaseAdapter(_context.getApplicationContext());
        }
        return oneInstance;
    }

    // Open database method
    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Close database method
    public void close() {
        db.close();
    }

    // Method to get instance of database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // Inserts a new row with default values into database
    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("SCORE1", 0);
        newValues.put("SCORE2", 0);
        newValues.put("SCORE3", 0);

        // Insert the row into your table
        db.insert("THINKFAST", null, newValues);
    }

    // Deletes a complete row from the database
    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("THINKFAST", where, new String[]{UserName});
        // Toast.makeText(context, "Number of Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    // Password getter from database
    public String getPassword(String userName) {
        Cursor cursor = db.query("THINKFAST", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    // Method to update user and password in database
    public void updateLogin(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        String where = "USERNAME = ?";
        db.update("THINKFAST", updatedValues, where, new String[]{userName});
    }

   // Retrieves the user's scores and returns them in an array
   public int[] getScores(String userName) {

        int[] scores = new int[3];  // Array for scores

        // Create cursor
        Cursor cursor = db.query("THINKFAST", null, " USERNAME=?", new String[]{userName}, null, null, null);

        // Assign retrieved scores to array
        cursor.moveToFirst();
        scores[0] = cursor.getInt(cursor.getColumnIndex("SCORE1"));
        scores[1] = cursor.getInt(cursor.getColumnIndex("SCORE2"));
        scores[2] = cursor.getInt(cursor.getColumnIndex("SCORE3"));
        cursor.close();
        return scores;
    }

    // Saves the new score into database if it is one of the top 3 scores
    public void updateScore(String userName, int[] scores, int score, int index)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();

        // Check if current score is higher than all previous stored scores
        if (score > scores[index]) {
            // Determine lowest score column for current score to be stored into
            switch (index) {
                case 0:
                    updatedValues.put("SCORE1", score);
                    break;
                case 1:
                    updatedValues.put("SCORE2", score);
                    break;
                case 2:
                    updatedValues.put("SCORE3", score);
                    break;
                default:
                    break;
            }
            // Save value in database
            String where = "USERNAME = ?";
            db.update("THINKFAST", updatedValues, where, new String[]{userName});
        }
    }


}