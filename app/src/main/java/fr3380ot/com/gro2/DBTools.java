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
import android.util.Log;

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

        String habits = "CREATE TABLE habits ( habitId INTEGER PRIMARY KEY, " +
                                              "title TEXT, " +
                                              "difficulty TEXT, " +
                                              "frequency TEXT)";

        //In order for CustomListAdapter to work in Rewards.java (See onCreate)
        //rewardsId must be named plantId to pull the same column mapping
        //when combining HashMaps from both tables into one ArrayList
        //TODO: If customListAdapter returns null error for ImageView or picId, add picId here
        String rewards = "CREATE TABLE rewards ( plantId INTEGER PRIMARY KEY, " +
                                                "title TEXT, " +
                                                "price TEXT)";

        String plants = "CREATE TABLE plants ( plantId INTEGER PRIMARY KEY, " +
                                                "title TEXT, " +
                                                "description TEXT, " +
                                                "price TEXT, " +
                                                "oxygenRate TEXT, " +
                                                "waterCost TEXT, " +
                                                "seedId TEXT, " +
                                                "youngId TEXT, " +
                                                "grownId TEXT)";

        String tile = "CREATE TABLE tile ( tileId INTEGER PRIMARY KEY, " +
                                          "plantId TEXT," +
                                          "growthLevel TEXT)";

        String user = "CREATE TABLE user ( userId INTEGER PRIMARY KEY, " +
                                          "name TEXT, " +
                                          "waterLevel TEXT, " +
                                          "oxygenLevel TEXT)";

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data
        // Hard code these initially into the DB so they exist on installation
        String userInsert = "INSERT INTO user ('name', 'waterLevel', 'oxygenLevel') VALUES ('John Smith', '0', '100')";

        String sunflowerInsert = "INSERT INTO plants ('title', 'description', 'price', 'oxygenRate', 'waterCost', 'seedId', 'youngId', 'grownId') " +
                                 "VALUES ('Sunflower', 'A cute sunflower', '25', '2','3'," +
                                 "'R.mipmap.ic_sunflower_seeds','1','1')";

        String camelliaInsert = "INSERT INTO plants ('title', 'description', 'price', 'oxygenRate', 'waterCost', 'seedId', 'youngId', 'grownId') " +
                                "VALUES ('Camellia', 'A lovely Camellia','50', '4','7'," +
                                "'R.mipmap.ic_camellia_seeds', '2', '2')";

        String chrysanthemumInsert = "INSERT INTO plants ('title', 'description', 'price', 'oxygenRate', 'waterCost', 'seedId', 'youngId', 'grownId') " +
                                     "VALUES ('Chrysanthemum', 'A beautiful Chrysanthemum','75', '8','10'," +
                                     "'R.mipmap.ic_chrysanthemum_seeds','3','3')";


        database.execSQL(habits);
        database.execSQL(rewards);
        database.execSQL(plants);
        database.execSQL(tile);
        database.execSQL(user);
        database.execSQL(userInsert);
        database.execSQL(sunflowerInsert);
        database.execSQL(camelliaInsert);
        database.execSQL(chrysanthemumInsert);

    }

    // onUpgrade is used to drop tables, add tables, or do anything
    // else it needs to upgrade
    // This is dropping the table to delete the data and then calling
    // onCreate to make an empty table

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String habits = "DROP TABLE IF EXISTS habits";
        String rewards = "DROP TABLE IF EXISTS rewards";
        String plants = "DROP TABLE IF EXISTS plants";
        String tile = "DROP TABLE IF EXISTS tile";
        String user = "DROP TABLE IF EXISTS user";

        database.execSQL(habits);
        database.execSQL(rewards);
        database.execSQL(plants);
        database.execSQL(tile);
        database.execSQL(user);
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

        String selectQuery = "SELECT  * FROM habits";

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);

        // Move to the first row

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> habitMap = new HashMap<String, String>();

                // Store the key / value pairs in a HashMap
                // Access the Cursor data by index that is in the same order
                // as used when creating the table

                habitMap.put("habitId", cursor.getString(0));
                habitMap.put("title", cursor.getString(1));
                habitMap.put("difficulty", cursor.getString(2));
                habitMap.put("frequency", cursor.getString(3));

                habitArrayList.add(habitMap);
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


    public ArrayList<HashMap<String, String>> getAllRewards() {

        ArrayList<HashMap<String, String>> rewardArrayList;
        rewardArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM rewards";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> rewardMap = new HashMap<String, String>();

                rewardMap.put("plantId", cursor.getString(0));
                rewardMap.put("title", cursor.getString(1));
                rewardMap.put("price", cursor.getString(2));

                rewardArrayList.add(rewardMap);
            } while (cursor.moveToNext());
        }

        return rewardArrayList;
    }


    public ArrayList<HashMap<String, String>> getAllPlants() {

        ArrayList<HashMap<String, String>> plantArrayList;
        plantArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM plants";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> plantMap = new HashMap<String, String>();

                plantMap.put("plantId", cursor.getString(0));
                plantMap.put("title", cursor.getString(1));
                plantMap.put("description", cursor.getString(2));
                plantMap.put("price", cursor.getString(3));
                plantMap.put("oxygenRate", cursor.getString(4));
                plantMap.put("waterCost", cursor.getString(5));
                plantMap.put("seedId", cursor.getString(6));
                plantMap.put("youngId", cursor.getString(7));
                plantMap.put("grownId", cursor.getString(8));

                plantArrayList.add(plantMap);
            } while (cursor.moveToNext());
        }

        return plantArrayList;
    }

    public HashMap<String, String> getPlantInfo(String id) {
        HashMap<String, String> plantMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM plants where plantId='"+id+"'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                plantMap.put("plantId", cursor.getString(0));
                plantMap.put("title", cursor.getString(1));
                plantMap.put("description", cursor.getString(2));
                plantMap.put("price", cursor.getString(3));
                plantMap.put("oxygenRate", cursor.getString(4));
                plantMap.put("waterCost", cursor.getString(5));
                plantMap.put("seedId", cursor.getString(6));
                plantMap.put("youngId", cursor.getString(7));
                plantMap.put("grownId", cursor.getString(8));

            } while (cursor.moveToNext());
        }
        return plantMap;
    }


    public int insertTile(HashMap<String, String> plant){

        SQLiteDatabase database = this.getWritableDatabase();
        String checkEnvironNum = "SELECT Count(*) FROM tile";
        Cursor cursor  = database.rawQuery(checkEnvironNum, null);
        cursor.moveToFirst();
        //Log.d("Number of plants", String.valueOf(cursor.getInt(0)));
        if (cursor.getInt(0) >= 9) {
            Log.d("Plants", "You can't have more than nine plants");
            database.close();
            return -1;
        }

        ContentValues values = new ContentValues();

        values.put("plantId", plant.get("plantId"));
        values.put("growthLevel", "1");

        database.insert("tile", null, values);
        database.close();

        return 0;
    }

    public HashMap<String, String> getTile(String id){
        HashMap<String, String> tileMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM tile where tileId='"+id+"'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        tileMap.put("tileId",cursor.getString(0));
        tileMap.put("plantId", cursor.getString(1));
        tileMap.put("growthLevel", cursor.getString(2));
        return tileMap;
    }



    public HashMap<String, String> getUserInfo(){
        HashMap<String, String> userMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM user WHERE userID = 1 LIMIT 1";

        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        userMap.put("userId", cursor.getString(0));
        userMap.put("name", cursor.getString(1));
        userMap.put("waterLevel", cursor.getString(2));
        userMap.put("oxygenLevel", cursor.getString(3));

        return userMap;
    }

    public int updateUser(HashMap<String, String> user){

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data

        ContentValues values = new ContentValues();

        //values.put("userId", user.get("userId"));
        values.put("name", user.get("name"));
        values.put("waterLevel", user.get("waterLevel"));
        values.put("oxygenLevel", user.get("oxygenLevel"));

        // update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)

        return database.update("user", values, "userId" + " = ?", new String[] {user.get("userId")});

    }

    public HashMap<String, String> getRewardInfo(String id) {
        HashMap<String, String> rewardMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM plants where plantId='"+id+"'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                rewardMap.put("plantId", cursor.getString(0));
                rewardMap.put("title", cursor.getString(1));
                rewardMap.put("price", cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return rewardMap;
    }

    public void insertReward(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed

        ContentValues values = new ContentValues();

        values.put("title", queryValues.get("title"));
        values.put("price", queryValues.get("price"));

        database.insert("rewards", null, values);

        database.close();
    }

    public int updateReward(HashMap<String, String> queryValues) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data

        ContentValues values = new ContentValues();

        values.put("title", queryValues.get("title"));
        values.put("price", queryValues.get("price"));

        // update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)

        return database.update("rewards", values, "plantId" + " = ?", new String[] { queryValues.get("plantId") });
    }

    public void deleteReward(String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM rewards where plantId='"+ id +"'";
        database.execSQL(deleteQuery);

    }
}