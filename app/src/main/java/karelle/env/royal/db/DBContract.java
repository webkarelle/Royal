package karelle.env.royal.db;

/**
 * Created by Salomé on 14/08/2016.
 */
public class DBContract {


    public class CategoriesCt{
        public static final String TABLE_NAME = "Categories";
        public static final String COL_ID = "_id";
        public static final String COL_NAME = "name";
        public static final String COL_TYPE = "type";
        public static final String COL_PRICE = "price";
        public static final String COL_IMAGE_URI = "imageUri";

    }
    public class SubCategoriesCt{
        public static final String TABLE_NAME = "SubCategories";
        public static final String COL_ID = "_id";
        public static final String COL_NAME = "name";
        public static final String COL_TYPE = "type";
        public static final String COL_PRICE = "price";
        public static final String COL_IMAGE_URI = "imageUri";
    }

    public class SubOrderDetailsCt{
        public static final String TABLE_NAME = "SubOrderDetails";
        public static final String COL_ID = "_id";
        public static final String COL_SUBCATEGORY = "subcategory";
        public static final String COL_CATEGORY = "category";
        public static final String COL_PRICE = "price";
        public static final String COL_IDOD = "idod";

    }

    public class OrderDetailsCt{
        public static final String TABLE_NAME = "OrderDetails";
        public static final String COL_ID = "_id";
        public static final String COL_CATEGORY = "category";
        public static final String COL_PRICE = "price";
        public static final String COL_TYPE ="type";
        public static final String COL_IDO = "ido";

    }

    public class OrdersCt{
        public static final String TABLE_NAME = "Orders";
        public static final String COL_ID = "_id";
        public static final String COL_IDCUST = "idcust";
        public static final String COL_PRICE = "price";
        public static final String COL_DATE = "date";
        public static final String COL_MODE = "mode";//mode = CarryOut ou Delivery
        public static final String COL_PLACE= "place";//place = home ou store
        public static final String COL_ADD = "address";//address si Delivery
        public static final String COL_DATE_DELIVERY = "datedelivery";//date de mise à disposition souhaitée
        public static final String COL_STATUT = "statut";//recu en cours pret ou livre
    }
    public class TempOrdersCt{
        public static final String TABLE_NAME = "TempOrders";
        public static final String COL_ID = "_id";
        public static final String COL_IDCUST = "idcust";
        public static final String COL_PRICE = "price";
        public static final String COL_DATE = "date";
        public static final String COL_STATUT = "statut";
    }
    public class TempOrderDetailsCt{
        public static final String TABLE_NAME = "TempOrderDetails";
        public static final String COL_ID = "_id";
        public static final String COL_CATEGORY = "category";
        public static final String COL_PRICE = "price";
        public static final String COL_TYPE ="type";
        public static final String COL_IDO = "ido";

    }
    public class TempSubOrderDetailsCt{
        public static final String TABLE_NAME = "TempSubOrderDetails";
        public static final String COL_ID = "_id";
        public static final String COL_SUBCATEGORY = "subcategory";
        public static final String COL_CATEGORY = "category";
        public static final String COL_PRICE = "price";
        public static final String COL_IDOD = "idod";

    }

}