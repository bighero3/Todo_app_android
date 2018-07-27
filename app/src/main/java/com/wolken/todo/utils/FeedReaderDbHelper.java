package com.wolken.todo.utils;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.BaseColumns;
        import android.util.Log;

        import com.wolken.todo.models.Events;

        import java.util.ArrayList;
        import java.util.List;

        import models.task;

        import static android.icu.text.MessagePattern.ArgType.SELECT;


public class FeedReaderDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private Long newRowId;

    public Long insertData(String name, String description, String date) {


        try {
            // Gets the data repository in write mode
            SQLiteDatabase db = this.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, name);
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION, description);
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, date);

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return newRowId;

    }

    public  List<Events> getAllNotes() {
        List<Events> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " ORDER BY " +
                FeedReaderContract.FeedEntry.COLUMN_NAME_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String taskName001 = null;
        String taskDescription001 = null;
        String taskDate001 = null;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                taskName001 = cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME));
                taskDescription001 = cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION));
                taskDate001 = cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE));

                Events event = new Events(taskName001,taskDescription001,taskDate001);

                notes.add(event);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


/*    public  List getData(){
        SQLiteDatabase db = this.getReadableDatabase();



        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_ID + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            itemIds.add(itemId);
            Log.d("Database value:", itemIds.toString());
        }
        cursor.close();
        return itemIds;
    }*/


    /*public void delete(Events note){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(note.get)});
    }*/
}
