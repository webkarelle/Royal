package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import karelle.env.royal.models.User;


public class UserDAO {

    private SQLiteDatabase db;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(User user) {

        ContentValues values = new ContentValues();
        values.put(DBContract.UserCt.COL_ID, user.getUserId());
        values.put(DBContract.UserCt.COL_NAME, user.getName());
        values.put(DBContract.UserCt.COL_ADD, user.getAddHome());
        values.put(DBContract.UserCt.COL_EMAIL, user.getEmail());
        values.put(DBContract.UserCt.COL_TEL, user.getTel());

        long insertedID = db.insert(DBContract.UserCt.TABLE_NAME, null, values);

        return insertedID;
    }


    public User query() {
       User user = new User();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.UserCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {
                user = parseCursor(cursor);
        }
        return user;
    }



    public int update(User user) {
        ContentValues values = getContentValues(user);
        int rowsAffected = db.update(DBContract.UserCt.TABLE_NAME, values, null, null);
        return rowsAffected;
    }
    private User parseCursor(Cursor cursor) {
        //Read the Line:
        String idUser = cursor.getString(cursor.getColumnIndex(DBContract.UserCt.COL_ID));
        String email= cursor.getString(cursor.getColumnIndex(DBContract.UserCt.COL_EMAIL));
        String name= cursor.getString(cursor.getColumnIndex(DBContract.UserCt.COL_NAME));
        String tel= cursor.getString(cursor.getColumnIndex(DBContract.UserCt.COL_TEL));
        String addr = cursor.getString(cursor.getColumnIndex(DBContract.UserCt.COL_ADD));

        return new User(idUser,name,tel,email,addr);

    }


    public int delete() {
        int rowsAffected = db.delete(DBContract.UserCt.TABLE_NAME, null, null);
        return rowsAffected;
    }



    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(DBContract.UserCt.COL_ID, user.getUserId());
        values.put(DBContract.UserCt.COL_NAME, user.getName());
        values.put(DBContract.UserCt.COL_TEL, user.getTel());
        values.put(DBContract.UserCt.COL_EMAIL, user.getEmail());
        values.put(DBContract.UserCt.COL_ADD, user.getAddHome());
        return values;
    }

}
