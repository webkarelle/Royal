package karelle.env.royal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    //Constructor
    public DBHelper(Context context) {
        super(context, "RoyalPizzaDB", null, 1);
    }

    //Methods that we need to implement:
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " +DBContract.UserCt.TABLE_NAME + "(" +
                DBContract.UserCt.COL_ID + " TEXT, " +
                DBContract.UserCt.COL_NAME + " TEXT, " +
                DBContract.UserCt.COL_EMAIL + " TEXT, " +
                DBContract.UserCt.COL_TEL + " TEXT, " +
                DBContract.UserCt.COL_ADD + " TEXT " +
                "); ";

        db.execSQL(createUserTable);

        String createStoreTable = "CREATE TABLE " +DBContract.StoreCt.TABLE_NAME + "(" +
                DBContract.StoreCt.COL_ID + " TEXT, " +
                DBContract.StoreCt.COL_NAME + " TEXT, " +
                DBContract.StoreCt.COL_ADD + " TEXT " +
                "); ";

        db.execSQL(createStoreTable);

       String createSubCategoriesTable = "CREATE TABLE " +DBContract.SubCategoriesCt.TABLE_NAME + "(" +
                DBContract.SubCategoriesCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.SubCategoriesCt.COL_NAME + " TEXT NOT NULL, " +
                DBContract.SubCategoriesCt.COL_TYPE + " TEXT, " +
                DBContract.SubCategoriesCt.COL_PRICE + " REAL, " +
                DBContract.SubCategoriesCt.COL_IMAGE_URI + " TEXT " +
                "); ";

        db.execSQL(createSubCategoriesTable);

        String createCategoriesTable = "CREATE TABLE " +DBContract.CategoriesCt.TABLE_NAME + "(" +
                DBContract.CategoriesCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.CategoriesCt.COL_NAME + " TEXT NOT NULL, " +
                DBContract.CategoriesCt.COL_TYPE + " TEXT, " +
                DBContract.CategoriesCt.COL_PRICE + " REAL, " +
                DBContract.CategoriesCt.COL_IMAGE_URI + " TEXT " +
                "); ";

        db.execSQL(createCategoriesTable);


        String createSubOrderDetailsTable = "CREATE TABLE " +DBContract.SubOrderDetailsCt.TABLE_NAME + "(" +
                DBContract.SubOrderDetailsCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.SubOrderDetailsCt.COL_SUBCATEGORY + " TEXT , " +
                DBContract.SubOrderDetailsCt.COL_CATEGORY + " TEXT , " +
                DBContract.SubOrderDetailsCt.COL_PRICE + " REAL, " +
                DBContract.SubOrderDetailsCt.COL_IDOD + " INTEGER" +
                "); ";

        db.execSQL(createSubOrderDetailsTable);

        String createTempSubOrderDetailsTable = "CREATE TABLE " +DBContract.TempSubOrderDetailsCt.TABLE_NAME + "(" +
                DBContract.TempSubOrderDetailsCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.TempSubOrderDetailsCt.COL_SUBCATEGORY + " TEXT , " +
                DBContract.TempSubOrderDetailsCt.COL_CATEGORY + " TEXT , " +
                DBContract.TempSubOrderDetailsCt.COL_PRICE + " REAL, " +
                DBContract.TempSubOrderDetailsCt.COL_IDOD + " INTEGER" +
                "); ";

        db.execSQL(createTempSubOrderDetailsTable);

       String createTempOrderDetailsTable = "CREATE TABLE " +DBContract.TempOrderDetailsCt.TABLE_NAME + "(" +
                DBContract.TempOrderDetailsCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.TempOrderDetailsCt.COL_CATEGORY + " TEXT , " +
                DBContract.TempOrderDetailsCt.COL_PRICE + " REAL, " +
                DBContract.TempOrderDetailsCt.COL_TYPE + " TEXT, " +
                DBContract.TempOrderDetailsCt.COL_IDO + " INTEGER " +

                "); ";

        db.execSQL(createTempOrderDetailsTable);

        String createOrderDetailsTable = "CREATE TABLE " +DBContract.OrderDetailsCt.TABLE_NAME + "(" +
                DBContract.OrderDetailsCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.OrderDetailsCt.COL_CATEGORY + " TEXT , " +
                DBContract.OrderDetailsCt.COL_PRICE + " REAL, " +
                DBContract.OrderDetailsCt.COL_TYPE + " TEXT, " +
                DBContract.OrderDetailsCt.COL_IDO + " INTEGER " +
                "); ";

        db.execSQL(createOrderDetailsTable);

        String createOrdersTable = "CREATE TABLE " +DBContract.OrdersCt.TABLE_NAME + "(" +
                DBContract.OrdersCt.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBContract.OrdersCt.COL_IDCUST + " TEXT, " +
                DBContract.OrdersCt.COL_PRICE + " REAL, " +
                DBContract.OrdersCt.COL_DATE + " TEXT," +
                DBContract.OrdersCt.COL_MODE + " TEXT," +
                DBContract.OrdersCt.COL_PLACE + " TEXT," +
                DBContract.OrdersCt.COL_ADD + " TEXT," +
                DBContract.OrdersCt.COL_DATE_DELIVERY+ " TEXT," +
                DBContract.OrdersCt.COL_STATUT + " TEXT" +
                "); ";


        db.execSQL(createOrdersTable);


        String createTempOrdersTable = "CREATE TABLE " +DBContract.TempOrdersCt.TABLE_NAME + "(" +
                DBContract.TempOrdersCt.COL_ID + " INTEGER, " +
                DBContract.TempOrdersCt.COL_IDCUST + " TEXT , " +
                DBContract.TempOrdersCt.COL_PRICE + " REAL, " +
                DBContract.TempOrdersCt.COL_DATE + " TEXT," +
                DBContract.TempOrdersCt.COL_STATUT + " TEXT" +
                "); ";

        db.execSQL(createTempOrdersTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropSubCategories = "DROP TABLE IF EXISTS" + DBContract.SubCategoriesCt.TABLE_NAME + ";";
        db.execSQL(dropSubCategories);
        String dropCategories = "DROP TABLE IF EXISTS" + DBContract.CategoriesCt.TABLE_NAME + ";";
        db.execSQL(dropCategories);

        String dropSubOrderDetails = "DROP TABLE IF EXISTS" + DBContract.SubOrderDetailsCt.TABLE_NAME + ";";
        db.execSQL(dropSubOrderDetails);

        String dropTempSubOrderDetails = "DROP TABLE IF EXISTS" + DBContract.TempSubOrderDetailsCt.TABLE_NAME + ";";
        db.execSQL(dropTempSubOrderDetails);

        String dropOrderDetails = "DROP TABLE IF EXISTS" + DBContract.OrderDetailsCt.TABLE_NAME + ";";
        db.execSQL(dropOrderDetails);

        String dropTempOrderDetails = "DROP TABLE IF EXISTS" + DBContract.TempOrderDetailsCt.TABLE_NAME + ";";
        db.execSQL(dropTempOrderDetails);

        String dropTempOrders = "DROP TABLE IF EXISTS" + DBContract.TempOrdersCt.TABLE_NAME + ";";
        db.execSQL(dropTempOrders);

        String dropOrders = "DROP TABLE IF EXISTS" + DBContract.OrdersCt.TABLE_NAME + ";";
        db.execSQL(dropOrders);

        String dropUser = "DROP TABLE IF EXISTS" + DBContract.UserCt.TABLE_NAME + ";";
        db.execSQL(dropUser);

        String dropStores = "DROP TABLE IF EXISTS" + DBContract.StoreCt.TABLE_NAME + ";";
        db.execSQL(dropStores);

        onCreate(db);
    }
}