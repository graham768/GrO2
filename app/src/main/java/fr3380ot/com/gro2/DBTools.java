package fr3380ot.com.gro2;

/**
 * Created by Ben Graham on 2/29/16.
 *
 * Database Management Class for storing user data on device
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools  extends SQLiteOpenHelper {

    // Context : provides access to application-specific resources and classes

    public DBTools(Context applicationcontext) {

        // Call to use the database or to create it

        super(applicationcontext, "userdata.db", null, 1);

    }

    // onCreate is called the first time the database is created

    @Override
    public void onCreate(SQLiteDatabase database) {

        // Creates a table in SQLite
        // Make sure you don't put a ; at the end of the query

        String query = "CREATE TABLE habits ( habitId INTEGER PRIMARY KEY, " +
                                              "title TEXT, " +
                                              "difficulty INTEGER, " +
                                              "frequency TEXT)";

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data

        database.execSQL(query);

    }

    // onUpgrade is used to drop tables, add tables, or do anything
    // else it needs to upgrade
    // This is dropping the table to delete the data and then calling
    // onCreate to make an empty table

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query = "DROP TABLE IF EXISTS habits";

        database.execSQL(query);
        onCreate(database);
    }

    public void insertHabit(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed

        ContentValues values = new ContentValues();

        values.put("title", queryValues.get("title"));
        values.put("difficulty", queryValues.get("difficulty"));
        values.put("frequency", queryValues.get("frequency"));

        database.insert("habits", null, values);

        database.close();
    }

    // queryValues is a HashMap reference to the given habit
    // can be found with getHabitInfo()

    public int updateHabit(HashMap<String, String> queryValues) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data

        ContentValues values = new ContentValues();

        values.put("title", queryValues.get("title"));
        values.put("difficulty", queryValues.get("difficulty"));
        values.put("frequency", queryValues.get("frequency"));

        // update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)

        return database.update("habits", values, "habitId" + " = ?", new String[] { queryValues.get("habitId") });
    }

    // Used to delete a habit with the matching habitId

    public void deleteHabit(String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM  habits where habitId='"+ id +"'";
        database.execSQL(deleteQuery);

    }

    public ArrayList<HashMap<String, String>> getAllHabits() {

        // ArrayList that contains every row in the database
        // and each row key / value stored in a HashMap

        ArrayList<HashMap<String, String>> habitArrayList;

        habitArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM contacts";

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);

        // Move to the first row

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> contactMap = new HashMap<String, String>();

                // Store the key / value pairs in a HashMap
                // Access the Cursor data by index that is in the same order
                // as used when creating the table

                contactMap.put("habitId", cursor.getString(0));
                contactMap.put("title", cursor.getString(1));
                contactMap.put("difficulty", cursor.getString(2));
                contactMap.put("frequency", cursor.getString(3));

                habitArrayList.add(contactMap);
            } while (cursor.moveToNext()); // Move Cursor to the next row
        }

        return habitArrayList;
    }

    public HashMap<String, String> getHabitInfo(String id) {
        HashMap<String, String> habitMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM habits where habitId='"+id+"'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                habitMap.put("habitId", cursor.getString(0));
                habitMap.put("title", cursor.getString(1));
                habitMap.put("difficulty", cursor.getString(2));
                habitMap.put("frequency", cursor.getString(3));

            } while (cursor.moveToNext());
        }
        return habitMap;
    }
}