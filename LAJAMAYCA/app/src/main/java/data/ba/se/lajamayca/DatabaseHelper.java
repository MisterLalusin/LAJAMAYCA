package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DB HElper";
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeration";
    public static final String TABLE_NAME1="tblreserve";
    public static final String COL_1="ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Password";
    public static final String COL_5="Email";
    public static final String COL_6="Phone";
    public static final String COL_9="ID";
    public static final String COL_10="Show";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Email TEXT,FirstName TEXT,LastName TEXT,Password TEXT,Phone TEXT)");
        db.execSQL("CREATE TABLE tbladmin (ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,Password TEXT,Email TEXT,Phone TEXT)");
        db.execSQL("CREATE TABLE tblreserve (ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,Email TEXT,FirstName TEXT,LastName TEXT,Phone TEXT,Date TEXT,Details TEXT,Status TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName", "LAJAMAYCA");
        contentValues.put("LastName", "ADMINISTRATOR");
        contentValues.put("Password", "admin");
        contentValues.put("Email", "admin");
        contentValues.put("Phone", "+0436969");
        db.insert("tbladmin", null, contentValues);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME); //Drop older table if exists
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tbladmin"); //Dro  bp older table if exists
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblreserve"); //Dro  bp older table if exists
        onCreate(db);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME +
                " WHERE " + COL_5 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */


    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_1 + " = '" + id + "'" +
                " AND " + COL_5 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }







    ///RESERVATION///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Cursor getDataReservation(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM " + TABLE_NAME1;
        Cursor datareserve = db.rawQuery(query, null);
        return datareserve;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemIDReservation(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID FROM tblreserve WHERE " + COL_10 + " = '" + name + "'";
        Cursor datareserve = db.rawQuery(query, null);
        return datareserve;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */


    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteNameReservation(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tblreserve WHERE "
                + COL_9 + " = '" + id + "'" +
                " AND " + COL_10 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}
