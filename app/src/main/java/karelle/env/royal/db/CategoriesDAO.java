package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.models.Category;

/**
 * Created by Salomé on 14/08/2016.
 */
public class CategoriesDAO {

    private SQLiteDatabase db;
    private Context context;

    public CategoriesDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(Category cat) {

        ContentValues values = new ContentValues();
        values.put(DBContract.CategoriesCt.COL_NAME, cat.getNameCat());
        values.put(DBContract.CategoriesCt.COL_TYPE, cat.getTypeCat());
        values.put(DBContract.CategoriesCt.COL_PRICE, cat.getPriceCat());
        values.put(DBContract.CategoriesCt.COL_IMAGE_URI, cat.getImageCat());

        long insertedID = db.insert(DBContract.CategoriesCt.TABLE_NAME, null, values);

        return insertedID;
    }


    public ArrayList<Category> query() {
        ArrayList<Category> categories = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.CategoriesCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
               Category cat = parseCursor(cursor);
                Toast.makeText(context, "name"+cat.getNameCat(), Toast.LENGTH_SHORT).show();
               categories.add(cat);
            } while (cursor.moveToNext());
        }
        return categories;
    }

    public Category query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.CategoriesCt.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        return parseCursor(cursor);
    }

    public ArrayList<Category> queryByType(String type) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.CategoriesCt.TABLE_NAME,
                null,
                DBContract.CategoriesCt.COL_TYPE + " LIKE ?",
                new String[]{type + "%"},
                null, null,
                DBContract.CategoriesCt.COL_TYPE + " DESC"
        );

        ArrayList<Category> categories = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                categories.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }

        return categories;
    }
    public ArrayList<Category> queryByName(String name) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.CategoriesCt.TABLE_NAME,
                null,
                DBContract.CategoriesCt.COL_NAME + " LIKE ?",
                new String[]{name + "%"},
                null, null,
                DBContract.CategoriesCt.COL_NAME + " DESC"
        );

        ArrayList<Category> categories = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                categories.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }

        return categories;
    }

    private Category parseCursor(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.CategoriesCt.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DBContract.CategoriesCt.COL_NAME));
        String type = cursor.getString(cursor.getColumnIndex(DBContract.CategoriesCt.COL_TYPE));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.CategoriesCt.COL_PRICE));
        String imageURI = cursor.getString(cursor.getColumnIndex(DBContract.CategoriesCt.COL_IMAGE_URI));

        return new Category( id,name,type, price, imageURI);
    }

    public int delete(String id) {
        int rowsAffected = db.delete(DBContract.CategoriesCt.TABLE_NAME, DBContract.CategoriesCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }
    public int delete() {
        int rowsAffected = db.delete(DBContract.CategoriesCt.TABLE_NAME, null, null);
        return rowsAffected;
    }
    public int update(String id, Category cat) {
        ContentValues values = getContentValues(cat);
        int rowsAffected = db.update(DBContract.CategoriesCt.TABLE_NAME, values, DBContract.CategoriesCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

    private ContentValues getContentValues(Category cat) {
        ContentValues values = new ContentValues();
        values.put(DBContract.CategoriesCt.COL_NAME, cat.getNameCat());
        values.put(DBContract.CategoriesCt.COL_TYPE, cat.getTypeCat());
        values.put(DBContract.CategoriesCt.COL_PRICE, cat.getPriceCat());
        values.put(DBContract.CategoriesCt.COL_IMAGE_URI, cat.getImageCat());
        return values;
    }
}
