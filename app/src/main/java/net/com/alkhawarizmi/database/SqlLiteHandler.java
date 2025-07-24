package net.com.alkhawarizmi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHandler extends SQLiteOpenHelper {

    public static final String id = "id";
    public static final String name = "name";
    public static final String email = "email";
    public static final String password = "password";

    public static final String user_id = "user_id";
    public static final String course_id = "course_id";

    public static final String users = "users";
    public static final String reservations = "reservations";

    String createUsersStat = "CREATE TABLE " + users + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + name + " TEXT ,"
            + email + " TEXT ,"
            + password + " INTEGER );";

    String createReservationsStat = "CREATE TABLE " + reservations + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + user_id + " TEXT ,"
            + course_id + " TEXT );";



    public SqlLiteHandler(Context context) {
        super(context, "alkhawarizmi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createUsersStat);
        sqLiteDatabase.execSQL(createReservationsStat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + createUsersStat);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + createReservationsStat);

        onCreate(sqLiteDatabase);
    }
}
