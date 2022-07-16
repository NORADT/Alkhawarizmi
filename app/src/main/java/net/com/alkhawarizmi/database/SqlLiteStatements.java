package net.com.alkhawarizmi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import net.com.alkhawarizmi.models.AppUser;

public class SqlLiteStatements {

    private SqlLiteHandler sqlLiteHandler;
    private SQLiteDatabase sqLiteDatabase;

    private Context mContext;

    public SqlLiteStatements(Context context) {
        sqlLiteHandler = new SqlLiteHandler(context);
        this.mContext = context;
    }


    private void openDatabase() throws SQLException {
        sqLiteDatabase = sqlLiteHandler.getWritableDatabase();
    }

    private void closeDatabase() {
        sqLiteDatabase.close();
        sqlLiteHandler.close();
    }

    public boolean userValidation(String email) {
        openDatabase();
        boolean found = false;

        String statement = "SELECT " + SqlLiteHandler.id + " FROM " + SqlLiteHandler.users
                + " WHERE " + SqlLiteHandler.email + " = '" + email + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            found = true;
        }

        cursor.close();
        closeDatabase();
        return found;
    }

    public AppUser newUser(AppUser user) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SqlLiteHandler.fName, user.getName());
        cv.put(SqlLiteHandler.password, user.getPassword());
        cv.put(SqlLiteHandler.email, user.getEmail());

        int id = (int) sqLiteDatabase.insert(SqlLiteHandler.users, null, cv);
        user.setId(id);

        closeDatabase();
        return user;
    }

    public AppUser userLogin(String email, String password) {
        openDatabase();
        AppUser user = null;

        String statement = "SELECT * FROM " + SqlLiteHandler.users
                + " WHERE " + SqlLiteHandler.email + " = '" + email + "'"
                + " AND " + SqlLiteHandler.password + " = '" + password + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);
        if (cursor.moveToFirst()) {
            user = new AppUser();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));

        }

        cursor.close();
        closeDatabase();
        return user;
    }

}
