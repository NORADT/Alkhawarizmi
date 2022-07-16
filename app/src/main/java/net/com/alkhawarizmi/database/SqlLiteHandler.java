package net.com.alkhawarizmi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHandler extends SQLiteOpenHelper {

    public static final String id = "id";
    public static final String fName = "firstname";
    public static final String lName = "lastname";
    public static final String email = "email";
    public static final String password = "password";
    public static final String mobile = "mobile";
    public static final String type = "type";
    public static final String active = "active";

    public static final String user_id = "user_id";
    public static final String user_name = "user_name";
    public static final String speciality = "speciality";
    public static final String problem_msg = "problem_msg";
    public static final String expert_id = "expert_id";
    public static final String expert_name = "expert_name";
    public static final String problem_solution = "problem_solution";

    public static final String users = "users";
    public static final String problems = "problems";

    String createUsersStat = "CREATE TABLE " + users + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + fName + " TEXT ,"
            + lName + " TEXT ,"
            + email + " TEXT ,"
            + password + " TEXT ,"
            + mobile + " TEXT ,"
            + speciality + " TEXT ,"
            + active + " INTEGER ,"
            + type + " INTEGER );";

    String createProblemStat = "CREATE TABLE " + problems + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + user_id + " INTEGER ,"
            + user_name + " TEXT ,"
            + problem_msg + " TEXT ,"
            + expert_id + " INTEGER ,"
            + expert_name + " TEXT ,"
            + problem_solution + " TEXT );";


    public SqlLiteHandler(Context context) {
        super(context, "alkhawarizmi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createUsersStat);
        sqLiteDatabase.execSQL(createProblemStat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + createUsersStat);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + createProblemStat);

        onCreate(sqLiteDatabase);
    }
}
