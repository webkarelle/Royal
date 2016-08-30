package karelle.env.royal.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import karelle.env.royal.models.SubOrderDetail;

public class SubOrderDetailsDAO {
    private SQLiteDatabase db;
    private Context context;

    public SubOrderDetailsDAO(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public long insert(SubOrderDetail sod) {

        ContentValues values = new ContentValues();
        values.put(DBContract.TempSubOrderDetailsCt.COL_SUBCATEGORY, sod.getNameSOD());
        values.put(DBContract.TempSubOrderDetailsCt.COL_CATEGORY, sod.getTypeSOD());
        values.put(DBContract.TempSubOrderDetailsCt.COL_PRICE, sod.getPriceSOD());
        values.put(DBContract.TempSubOrderDetailsCt.COL_IDOD, Integer.valueOf(sod.getIdOD()));

        long insertedID = db.insert(DBContract.TempSubOrderDetailsCt.TABLE_NAME, null, values);


        return insertedID;
    }


    public ArrayList<SubOrderDetail> query() {
        ArrayList<SubOrderDetail> subOrderDetails = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempSubOrderDetailsCt.TABLE_NAME, null, null, null, null, null, null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubOrderDetail sod = parseCursor(cursor);
                subOrderDetails.add(sod);
            } while (cursor.moveToNext());
        }
        return subOrderDetails;
    }
    public ArrayList<SubOrderDetail> queryByType(String type) {
        ArrayList<SubOrderDetail> subOrderDetails = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempSubOrderDetailsCt.TABLE_NAME,
                null,
                DBContract.TempSubOrderDetailsCt.COL_CATEGORY+ " LIKE ?",
                new String[]{"%"+type+"%"},
                null, null,null);

        //move to the first row:
        if (cursor.moveToFirst()) {

            do {
                SubOrderDetail sod = parseCursor(cursor);
                subOrderDetails.add(sod);
            } while (cursor.moveToNext());
        }
        return subOrderDetails;
    }
    public SubOrderDetail query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempSubOrderDetailsCt.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        return parseCursor(cursor);
    }

    public ArrayList<SubOrderDetail> queryByName(String idOD) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempOrderDetailsCt.TABLE_NAME,
                null,
                DBContract.TempSubOrderDetailsCt.COL_IDOD+ " = ? ",
                new String[]{idOD},
                null, null,
                null
        );

        ArrayList<SubOrderDetail> subOrderDetails = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                subOrderDetails.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }
        return subOrderDetails;
    }

    public ArrayList<SubOrderDetail> queryByIdOD(String idOD) {
        //The Data set: The Query result:
        Cursor cursor = db.query(DBContract.TempSubOrderDetailsCt.TABLE_NAME,
                null,
                DBContract.TempSubOrderDetailsCt.COL_IDOD + " = ? ",
                new String[]{idOD},
                null, null,
                null
        );

        ArrayList<SubOrderDetail> subOrderDetails = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                subOrderDetails.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }
        return subOrderDetails;
    }


    private SubOrderDetail parseCursor(Cursor cursor) {
        //Read the Line:
        String idSod = cursor.getString(cursor.getColumnIndex(DBContract.TempSubOrderDetailsCt.COL_ID));
        String idOD = cursor.getString(cursor.getColumnIndex(DBContract.TempSubOrderDetailsCt.COL_IDOD));
        String name= cursor.getString(cursor.getColumnIndex(DBContract.TempSubOrderDetailsCt.COL_SUBCATEGORY));
        String type= cursor.getString(cursor.getColumnIndex(DBContract.TempSubOrderDetailsCt.COL_CATEGORY));
        Double price = cursor.getDouble(cursor.getColumnIndex(DBContract.TempSubOrderDetailsCt.COL_PRICE));


        return new SubOrderDetail(idSod,name,type,price,idOD);

    }

    public int delete(String id) {
        int rowsAffected = db.delete(DBContract.TempSubOrderDetailsCt.TABLE_NAME, DBContract.TempSubOrderDetailsCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }
    public int delete() {
        int rowsAffected = db.delete(DBContract.TempSubOrderDetailsCt.TABLE_NAME, null, null);
        return rowsAffected;
    }
    public int deleteByCategory(String cat) {
        int rowsAffected = db.delete(DBContract.TempSubOrderDetailsCt.TABLE_NAME, DBContract.TempSubOrderDetailsCt.COL_CATEGORY+ " = ?", new String[]{cat});
        return rowsAffected;
    }
    public int deleteBySubCategory(String subCat) {
        int rowsAffected = db.delete(DBContract.TempSubOrderDetailsCt.TABLE_NAME, DBContract.TempSubOrderDetailsCt.COL_SUBCATEGORY+ " = ?", new String[]{subCat});
        return rowsAffected;
    }

    public int update(String id, SubOrderDetail sod) {
        ContentValues values = getContentValues(sod);
        int rowsAffected = db.update(DBContract.TempSubOrderDetailsCt.TABLE_NAME, values, DBContract.TempSubOrderDetailsCt.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

    private ContentValues getContentValues(SubOrderDetail sod) {
        ContentValues values = new ContentValues();
        values.put(DBContract.TempSubOrderDetailsCt.COL_IDOD, sod.getIdOD());
        values.put(DBContract.TempSubOrderDetailsCt.COL_CATEGORY, sod.getTypeSOD());
        values.put(DBContract.TempSubOrderDetailsCt.COL_PRICE, sod.getPriceSOD());
        return values;
    }
}
