package com.example.a00807017.thinkfast;

/**
 * This class extends the SQLiteOpenHelper and creates the database.
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public DataBaseHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    // Called to create a new SQLiteDatabase
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(DataBaseAdapter.DATABASE_CREATE);

    }
    // Updates database version if there is a version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version by droppingb the old table
        // and creating a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }

}