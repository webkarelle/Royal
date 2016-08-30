package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.db.DBContract;
import karelle.env.royal.db.DBHelper;
import karelle.env.royal.models.SubCategory;


public class SubCategoriesDAO {

    private SQLiteDatabase db;
    private Context context;

    public SubCategoriesDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(SubCategory subC) {

        ContentValues values = new ContentValues();
        values.put(DBContract.SubCategoriesCt.COL_NAME, subC.getNameSubC());
        values.put(DBContract.SubCategoriesCt.COL_TYPE, subC.getTypeSubC());
        values.put(DBContract.SubCategoriesCt.COL_PRICE, subC.getPriceSubC());
        values.put(DBContract.SubCategoriesCt.COL_IMAGE_URI, subC.getImageSubC());

        long insertedID = db.insert(DBContract.SubCategoriesCt.TABLE_NAME, null, values);

        return insertedID;
    }


    public ArrayList<SubCategory> query() {
        ArrayList<SubCategory> subCategories = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubCategory subC = parseCursor(cursor);
                subCategories.add(subC);
            } while (cursor.moveToNext());
        }
        return subCategories;
    }
    public ArrayList<SubCategory> queryByType(String type) {
        ArrayList<SubCategory> subCategories = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME, null,
                DBContract.SubCategoriesCt.COL_TYPE + " LIKE ?",
                new String[]{"%"+type+"%"},
                null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubCategory subC = parseCursor(cursor);
                subCategories.add(subC);
            } while (cursor.moveToNext());
        }
        return subCategories;
    }
    public String[] queryNamesToppings() {

        ArrayList<SubCategory> namesToppings = new ArrayList<>();
        //The Data set: The Query result:

        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubCategory subC = parseCursor(cursor);
                namesToppings.add(subC);
            } while (cursor.moveToNext());
        }
        String[] namesToppingsArray = new String[namesToppings.size()];
        for (int i=0; i<namesToppings.size();i++)
        {
            namesToppingsArray[i]=namesToppings.get(i).getNameSubC();
        }
        return namesToppingsArray;
    }
    public String[] queryNamesToppingsByType(String type) {

        ArrayList<SubCategory> subCategoryArrayLists = new ArrayList<>();
        //The Data set: The Query result:

        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME, null,
                DBContract.SubCategoriesCt.COL_TYPE + " LIKE ?",
                new String[]{type+"%"},
                null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {
            do {
                SubCategory subC = parseCursor(cursor);
                subCategoryArrayLists.add(subC);
            } while (cursor.moveToNext());
        }

        String[] namesToppingsArrayByType = new String[subCategoryArrayLists.size()];
        for (int i=0; i<subCategoryArrayLists.size();i++)
        {
            namesToppingsArrayByType[i]=subCategoryArrayLists.get(i).getNameSubC();
        }
        return namesToppingsArrayByType;
    }

    public SubCategory query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        return parseCursor(cursor);
    }

    public ArrayList<SubCategory> queryByName(String name) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.SubCategoriesCt.TABLE_NAME,
                null,
                DBContract.SubCategoriesCt.COL_NAME + " LIKE ?",
                new String[]{name + "%"},
                null, null,
                DBContract.SubCategoriesCt.COL_NAME + " DESC"
        );

        ArrayList<SubCategory> subCategories = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                subCategories.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }

        return subCategories;
    }

    private SubCategory parseCursor(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.SubCategoriesCt.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DBContract.SubCategoriesCt.COL_NAME));
        String type = cursor.getString(cursor.getColumnIndex(DBContract.SubCategoriesCt.COL_TYPE));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.SubCategoriesCt.COL_PRICE));
        String imageURI = cursor.getString(cursor.getColumnIndex(DBContract.SubCategoriesCt.COL_IMAGE_URI));

        return new SubCategory( id,name,type, price, imageURI);
    }

    public int delete(String id) {
        int rowsAffected = db.delete(DBContract.SubCategoriesCt.TABLE_NAME, DBContract.SubCategoriesCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }
    public int delete() {
        int rowsAffected = db.delete(DBContract.SubCategoriesCt.TABLE_NAME, null, null);
        return rowsAffected;
    }

    public int update(String id, SubCategory subC) {
        ContentValues values = getContentValues(subC);
        int rowsAffected = db.update(DBContract.SubCategoriesCt.TABLE_NAME, values, DBContract.SubCategoriesCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

    private ContentValues getContentValues(SubCategory subC) {
        ContentValues values = new ContentValues();
        values.put(DBContract.SubCategoriesCt.COL_NAME, subC.getNameSubC());
        values.put(DBContract.SubCategoriesCt.COL_TYPE, subC.getTypeSubC());
        values.put(DBContract.SubCategoriesCt.COL_PRICE, subC.getPriceSubC());
        values.put(DBContract.SubCategoriesCt.COL_IMAGE_URI, subC.getImageSubC());
        return values;
    }
}
