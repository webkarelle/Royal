package karelle.env.royal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubOrderDetail;

public class FinalOrdersDAO {
    private SQLiteDatabase db;
    private Context context;

    public FinalOrdersDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insertOrderFull(Order o) {
/*       String createOrdersTable = "CREATE TABLE " +DBContract.OrdersCt.TABLE_NAME + "(" +
                DBContract.OrdersCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.OrdersCt.COL_IDCUST + " TEXT, " +
                DBContract.OrdersCt.COL_PRICE + " REAL, " +
                DBContract.OrdersCt.COL_DATE + " TEXT," +
                DBContract.OrdersCt.COL_MODE + " TEXT," +
                DBContract.OrdersCt.COL_PLACE + " TEXT," +
                DBContract.OrdersCt.COL_ADD + " TEXT," +
                DBContract.OrdersCt.COL_DATE_DELIVERY+ " TEXT," +
                DBContract.OrdersCt.COL_STATUT + " TEXT" +
                "); ";*/

        ContentValues values = new ContentValues();

        values.put(DBContract.OrdersCt.COL_IDCUST, o.getIdCust());
        values.put(DBContract.OrdersCt.COL_PRICE, o.getPriceOrder());
        values.put(DBContract.OrdersCt.COL_DATE, o.getDateOrder());
        values.put(DBContract.OrdersCt.COL_MODE, o.getModeOrder());
        values.put(DBContract.OrdersCt.COL_PLACE, o.getPlaceDelivery());
        values.put(DBContract.OrdersCt.COL_ADD, o.getAddDelivery());
        values.put(DBContract.OrdersCt.COL_DATE_DELIVERY, o.getDateDelivery());
        values.put(DBContract.OrdersCt.COL_STATUT, o.getStatutOrder());


        long insertedID = db.insert(DBContract.OrdersCt.TABLE_NAME, null, values);

        return insertedID;
    }


    public long insertOrderDetailFull(OrderDetail od) {

        ContentValues values = new ContentValues();

        values.put(DBContract.OrderDetailsCt.COL_CATEGORY, od.getNameOd());
        values.put(DBContract.OrderDetailsCt.COL_PRICE, od.getPrice());
        values.put(DBContract.OrderDetailsCt.COL_TYPE, od.getTypeOd());
        values.put(DBContract.OrderDetailsCt.COL_IDO, od.getIdOrder());

        long insertedID = db.insert(DBContract.OrderDetailsCt.TABLE_NAME, null, values);

        return insertedID;
    }
    public long insertSubOrderDetailFull(SubOrderDetail sod) {

        ContentValues values = new ContentValues();

        values.put(DBContract.SubOrderDetailsCt.COL_SUBCATEGORY, sod.getNameSOD());
        values.put(DBContract.SubOrderDetailsCt.COL_CATEGORY, sod.getTypeSOD());
        values.put(DBContract.SubOrderDetailsCt.COL_PRICE, sod.getPriceSOD());
        values.put(DBContract.SubOrderDetailsCt.COL_IDOD, Integer.valueOf(sod.getIdSOD()));

        long insertedID = db.insert(DBContract.SubOrderDetailsCt.TABLE_NAME, null, values);

        return insertedID;
    }




    public ArrayList<Order> queryOrdersFull() {
        ArrayList<Order> orders = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.OrdersCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                Order o = parseCursorOrder(cursor);
                orders.add(o);
            } while (cursor.moveToNext());
        }
        return orders;
    }
    public ArrayList<OrderDetail> queryOrderDetailsFull() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.OrderDetailsCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                OrderDetail od = parseCursorOrderDetail(cursor);
                orderDetails.add(od);
            } while (cursor.moveToNext());
        }
        return orderDetails;
    }
    public ArrayList<SubOrderDetail> queryOrderSubDetailsFull() {
        ArrayList<SubOrderDetail> subOrderDetails = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.SubOrderDetailsCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubOrderDetail sod = parseCursorSubOrderDetail(cursor);
                subOrderDetails.add(sod);
            } while (cursor.moveToNext());
        }
        return subOrderDetails;
    }
    private Order parseCursorOrder(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_ID));
        String idCust = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_IDCUST));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.OrdersCt.COL_PRICE));
        String date= cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_DATE));
        String mode = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_MODE));
        String place = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_PLACE));
        String add = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_ADD));
        String dateDelivery = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_DATE_DELIVERY));
        String statut = cursor.getString(cursor.getColumnIndex(DBContract.OrdersCt.COL_STATUT));


        return new Order(id,idCust, price,date,mode,place,add,dateDelivery,statut);
    }
    private OrderDetail parseCursorOrderDetail(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(DBContract.OrderDetailsCt.COL_ID));
        String idOrder = cursor.getString(cursor.getColumnIndex(DBContract.OrderDetailsCt.COL_IDO));
        String name= cursor.getString(cursor.getColumnIndex(DBContract.OrderDetailsCt.COL_CATEGORY));
        String type= cursor.getString(cursor.getColumnIndex(DBContract.OrderDetailsCt.COL_TYPE));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.OrderDetailsCt.COL_PRICE));

        return new OrderDetail(id,idOrder,name,type,price);
    }
    private SubOrderDetail parseCursorSubOrderDetail(Cursor cursor) {
        //Read the Line:
        String idSod = cursor.getString(cursor.getColumnIndex(DBContract.SubOrderDetailsCt.COL_ID));
        String idOD = cursor.getString(cursor.getColumnIndex(DBContract.SubOrderDetailsCt.COL_IDOD));
        String name= cursor.getString(cursor.getColumnIndex(DBContract.SubOrderDetailsCt.COL_SUBCATEGORY));
        String type= cursor.getString(cursor.getColumnIndex(DBContract.SubOrderDetailsCt.COL_CATEGORY));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.SubOrderDetailsCt.COL_PRICE));
        return new SubOrderDetail(idSod,name,type,price,idOD);

    }

}
