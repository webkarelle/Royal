package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import karelle.env.royal.models.Store;
import karelle.env.royal.models.SubCategory;


public class StoreDAO {
    private SQLiteDatabase db;
    private Context context;

    public StoreDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(Store store) {

        ContentValues values = new ContentValues();
        values.put(DBContract.StoreCt.COL_ID, store.getIdStore());
        values.put(DBContract.StoreCt.COL_NAME, store.getNameStore());
        values.put(DBContract.StoreCt.COL_ADD, store.getAddStore());

        long insertedID = db.insert(DBContract.StoreCt.TABLE_NAME, null, values);

        return insertedID;
    }


    public ArrayList<Store> query() {
        ArrayList<Store> stores = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.StoreCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                Store store = parseCursor(cursor);
                stores.add(store);
            } while (cursor.moveToNext());
        }
        return stores;
    }



    public int update(Store store) {
        ContentValues values = getContentValues(store);
        int rowsAffected = db.update(DBContract.StoreCt.TABLE_NAME, values, null, null);
        return rowsAffected;
    }
    private Store parseCursor(Cursor cursor) {
        //Read the Line:
        String idStore = cursor.getString(cursor.getColumnIndex(DBContract.StoreCt.COL_ID));

        String name= cursor.getString(cursor.getColumnIndex(DBContract.StoreCt.COL_NAME));

        String addr = cursor.getString(cursor.getColumnIndex(DBContract.StoreCt.COL_ADD));

        return new Store(idStore,name,addr);

    }


    public int delete() {
        int rowsAffected = db.delete(DBContract.StoreCt.TABLE_NAME, null, null);
        return rowsAffected;
    }



    private ContentValues getContentValues(Store store) {
        ContentValues values = new ContentValues();
        values.put(DBContract.StoreCt.COL_ID, store.getIdStore());
        values.put(DBContract.StoreCt.COL_NAME, store.getNameStore());
        values.put(DBContract.StoreCt.COL_ADD, store.getAddStore());
        return values;
    }

}
