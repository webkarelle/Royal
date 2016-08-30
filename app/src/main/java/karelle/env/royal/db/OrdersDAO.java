package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import karelle.env.royal.models.Order;


public class OrdersDAO {

    private SQLiteDatabase db;
    private Context context;

    public OrdersDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(Order o) {

        ContentValues values = new ContentValues();
        values.put(DBContract.TempOrdersCt.COL_ID, Integer.valueOf(o.getIdo()));
        values.put(DBContract.TempOrdersCt.COL_IDCUST, o.getIdCust());
        values.put(DBContract.TempOrdersCt.COL_DATE, o.getDateOrder());
        values.put(DBContract.TempOrdersCt.COL_PRICE, o.getPriceOrder());
        values.put(DBContract.TempOrdersCt.COL_STATUT, o.getStatutOrder());

        long insertedID = db.insert(DBContract.TempOrdersCt.TABLE_NAME, null, values);


        return insertedID;
    }


    public ArrayList<Order> query() {
        ArrayList<Order> orders = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrdersCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                Order o = parseCursor(cursor);
                orders.add(o);
            } while (cursor.moveToNext());
        }
        return orders;
    }

    public Order query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrdersCt.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        return parseCursor(cursor);
    }

    public ArrayList<Order> queryByName(String idCust) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrdersCt.TABLE_NAME,
                null,
                DBContract.TempOrdersCt.COL_IDCUST+ " LIKE ?",
                new String[]{idCust + "%"},
                null, null,
                DBContract.TempOrdersCt.COL_IDCUST + " DESC"
        );

        ArrayList<Order> orders = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                orders.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }

        return orders;
    }

    private Order parseCursor(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.TempOrdersCt.COL_ID));
        String idCust = cursor.getString(cursor.getColumnIndex(DBContract.TempOrdersCt.COL_IDCUST));
        String date= cursor.getString(cursor.getColumnIndex(DBContract.TempOrdersCt.COL_DATE));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.TempOrdersCt.COL_PRICE));
        String statut = cursor.getString(cursor.getColumnIndex(DBContract.TempOrdersCt.COL_STATUT));

        return new Order(id,idCust, price,date, statut);
    }

    public int delete(String id) {
        int rowsAffected = db.delete(DBContract.TempOrdersCt.TABLE_NAME, DBContract.TempOrdersCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }
    public int delete() {
        int rowsAffected = db.delete(DBContract.TempOrdersCt.TABLE_NAME, null, null);
        return rowsAffected;
    }

    public int update(String id, Order o) {
        ContentValues values = getContentValues(o);
        int rowsAffected = db.update(DBContract.TempOrdersCt.TABLE_NAME, values, DBContract.TempOrdersCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

    private ContentValues getContentValues(Order o) {
        ContentValues values = new ContentValues();
        values.put(DBContract.TempOrdersCt.COL_ID, o.getIdo());
        values.put(DBContract.TempOrdersCt.COL_IDCUST, o.getIdCust());
        values.put(DBContract.TempOrdersCt.COL_DATE, o.getDateOrder());
        values.put(DBContract.TempOrdersCt.COL_PRICE, o.getPriceOrder());
        values.put(DBContract.TempOrdersCt.COL_STATUT, o.getStatutOrder());
        return values;
    }
}