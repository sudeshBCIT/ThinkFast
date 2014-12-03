package com.example.a00807017.thinkfast;

/**
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {
    static final String DATABASE_NAME = "thinkfast.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "THINKFAST" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text, PASSWORD text, " +
            "SCORE1 integer, SCORE2 integer, SCORE3 integer ); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

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
        ///Toast.makeText(context, "Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("THINKFAST", where, new String[]{UserName});
        // Toast.makeText(context, "Number of Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

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

    public void updateLogin(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("THINKFAST", updatedValues, where, new String[]{userName});
    }

    public int getLowScoreIndex(int[] scores) {
        int lowScore = 0;
        int index = 0;

        scores[0] = lowScore;
        index = 0;
        if (scores[1] < lowScore) {
            lowScore = scores[1];
            index = 1;
        }
        if (scores[2]< lowScore) {
            index = 2;
        }
        return index;
    }

   public int[] getScores(String userName) {

        int[] scores = new int[3];

        Cursor cursor = db.query("THINKFAST", null, " USERNAME=?", new String[]{userName}, null, null, null);

        cursor.moveToFirst();
        scores[0] = cursor.getInt(cursor.getColumnIndex("SCORE1"));
        scores[1] = cursor.getInt(cursor.getColumnIndex("SCORE2"));
        scores[2] = cursor.getInt(cursor.getColumnIndex("SCORE3"));

        cursor.close();
        return scores;
    }

    public void updateScore(String userName, int[] scores, int score)
    {
        int index;

        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();

        // Check if current score is higher than all previous stored scores
        index = getLowScoreIndex(scores);

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
            // Save value
            String where = "USERNAME = ?";
            db.update("THINKFAST", updatedValues, where, new String[]{userName});
        }
    }


}