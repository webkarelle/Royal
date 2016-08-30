package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import karelle.env.royal.models.OrderDetail;

public class OrderDetailsDAO {


    private SQLiteDatabase db;
    private Context context;

    public OrderDetailsDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insertFull(OrderDetail od) {

        ContentValues values = new ContentValues();
        values.put(DBContract.TempOrderDetailsCt.COL_IDO, Integer.valueOf(od.getIdOrder()));
        values.put(DBContract.TempOrderDetailsCt.COL_CATEGORY, od.getNameOd());
        values.put(DBContract.TempOrderDetailsCt.COL_TYPE, od.getTypeOd());
        values.put(DBContract.TempOrderDetailsCt.COL_PRICE, od.getPrice());

        long insertedID = db.insert(DBContract.TempOrderDetailsCt.TABLE_NAME, null, values);

        return insertedID;
    }
    public long insertSimple(OrderDetail od) {

        ContentValues values = new ContentValues();
        values.put(DBContract.TempOrderDetailsCt.COL_CATEGORY, od.getTypeOd());

        long insertedID = db.insert(DBContract.TempOrderDetailsCt.TABLE_NAME, null, values);


        return insertedID;
    }

    public ArrayList<OrderDetail> query() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrderDetailsCt.TABLE_NAME, null, null, null, null, null, null);
        //move to the first row:
        if (cursor.moveToFirst()) {
            do {
                OrderDetail od = parseCursor(cursor);
                orderDetails.add(od);
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }

    public OrderDetail query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrderDetailsCt.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        return parseCursor(cursor);
    }

    public ArrayList<OrderDetail> queryByIDOrder(String idOrder) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrderDetailsCt.TABLE_NAME,
                null,
                DBContract.TempOrderDetailsCt.COL_IDO+ " LIKE ?",
                new String[]{idOrder + "%"},
                null, null,
                DBContract.TempOrderDetailsCt.COL_IDO + " DESC"
        );

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                orderDetails.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }
    public ArrayList<OrderDetail> queryByType(String type) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrderDetailsCt.TABLE_NAME,
                null,
                DBContract.TempOrderDetailsCt.COL_TYPE+ " LIKE ?",
                new String[]{type + "%"},
                null, null,
                DBContract.TempOrderDetailsCt.COL_TYPE + " DESC"
        );

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                orderDetails.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }
    private OrderDetail parseCursor(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.TempOrderDetailsCt.COL_ID));
        String idOrder = cursor.getString(cursor.getColumnIndex(DBContract.TempOrderDetailsCt.COL_IDO));
        String name= cursor.getString(cursor.getColumnIndex(DBContract.TempOrderDetailsCt.COL_CATEGORY));
        String type= cursor.getString(cursor.getColumnIndex(DBContract.TempOrderDetailsCt.COL_TYPE));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.TempOrderDetailsCt.COL_PRICE));

        return new OrderDetail(id,idOrder,name,type,price);
    }

    public int delete(String id) {
        int rowsAffected = db.delete(DBContract.TempOrderDetailsCt.TABLE_NAME, DBContract.TempOrderDetailsCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }
    public int delete() {
        int rowsAffected = db.delete(DBContract.TempOrderDetailsCt.TABLE_NAME, null, null);
        return rowsAffected;
    }

    public int update(String id, OrderDetail od) {
        ContentValues values = getContentValues(od);
        int rowsAffected = db.update(DBContract.TempOrderDetailsCt.TABLE_NAME, values, DBContract.TempOrderDetailsCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

   private ContentValues getContentValues(OrderDetail od) {
        ContentValues values = new ContentValues();
        values.put(DBContract.TempOrderDetailsCt.COL_IDO, od.getIdOrder());
        values.put(DBContract.TempOrderDetailsCt.COL_CATEGORY, od.getNameOd());
       values.put(DBContract.TempOrderDetailsCt.COL_TYPE, od.getTypeOd());
        values.put(DBContract.TempOrderDetailsCt.COL_PRICE, od.getPrice());

        return values;
    }
}