package net.teslaa.teslaa.Palculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "historyManager";
    private static final String TABLE_HISTORY = "historyTable";
    private static final String KEY_ID = "_id";
    private static final String KEY_STATEMENT = "statement";
    private static final String KEY_EQUALITY = "equality";
    private static final String KEY_TIME = "time";
    private SQLiteDatabase db = getReadableDatabase();

    public static String getTableHistory() {
        return TABLE_HISTORY;
    }

    static String getKeyId() {
        return KEY_ID;
    }

    static String getKeyStatement() {
        return KEY_STATEMENT;
    }

    static String getKeyEquality() {
        return KEY_EQUALITY;
    }


    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    void dbCreator(){
        String CREATE_HISTORYMANAGER = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STATEMENT + " TEXT," + KEY_EQUALITY + " TEXT,time TEXT" + ")";
        db.execSQL(CREATE_HISTORYMANAGER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    void addRecord(HistoryDefinition history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_STATEMENT, history.getStatement());
        values.put(KEY_EQUALITY, history.getEquality());

        db.insert(TABLE_HISTORY, null, values);

        db.close();
    }

    HistoryDefinition getHistory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[]{KEY_ID, KEY_STATEMENT, KEY_EQUALITY}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        HistoryDefinition history = new HistoryDefinition(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return history;
    }

    Cursor fetchAllHistory() {

        Cursor mCursor = db.query(TABLE_HISTORY, new String[] {KEY_ID,
                        KEY_STATEMENT, KEY_EQUALITY},
                null, null, null, null, KEY_ID +" DESC");

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        //Log.d("Insert", "Fetch Inserted");
        //String testResult = DatabaseUtils.dumpCursorToString(mCursor);
        //Log.d("testResult", testResult);
        return mCursor;
    }

    List<HistoryDefinition> getAllHistory() {
        List<HistoryDefinition> historyList = new ArrayList<>();

        // TODO SELECT All
        String selectAllQuery = "SELECT * FROM " + TABLE_HISTORY + " ORDER BY _id DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HistoryDefinition history = new HistoryDefinition();
                history.setId(Integer.parseInt(cursor.getString(0)));
                history.setStatement(cursor.getString(1));
                history.setEquality(cursor.getString(2));

                historyList.add(history);
            } while (cursor.moveToNext());
        }

        return historyList;
    }

    public int getHistoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }


}
