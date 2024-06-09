package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "user.db";

    public static final String USERS = "Users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);

        // Insert 20 random users
        for (int i = 0; i <= 20; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "User " + i);
            values.put(COLUMN_DESCRIPTION, "Description " + i);
            values.put(COLUMN_FOLLOWED, new Random().nextBoolean() ? 1 : 0);
            db.insert(USERS, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        db.insert(USERS, null, values);
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(USERS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt((int)cursor.getColumnIndex(COLUMN_ID));

                String name = cursor.getString((int)cursor.getColumnIndex(COLUMN_NAME));

                String description = cursor.getString((int)cursor.getColumnIndex(COLUMN_DESCRIPTION));

                boolean followed = cursor.getInt((int)cursor.getColumnIndex(COLUMN_FOLLOWED)) == 1;

                User user = new User(id, description, name, followed);

                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());

        String clause = "id=?";
        String[] args = {String.valueOf(user.getId())};
        db.update(USERS, values, clause, args);
    }

    @Override
    public void close() {
        Log.i("Database Operations", "Database is closed.");
        super.close();
    }
}
