package karelle.env.royal.db;


import android.content.Context;

import karelle.env.royal.models.SubCategory;

public class DBInit {
   SubCategoriesDAO daoSubCat;
    Context context;

    public DBInit() {
        this.daoSubCat = daoSubCat;
        /*daoSubCat = new SubCategoriesDAO(this);
        daoSubCat.insert(new SubCategory("Base(Sauce+Cheese)","ToppingPizza",5.0,"urlImageBase"));
        daoSubCat.insert(new SubCategory("Tuna","ToppingPizza",5.0,"urlImageTuna"));
        daoSubCat.insert(new SubCategory("Green Olive","ToppingPizza",5.0,"urlImageGreenOlive"));
        daoSubCat.insert(new SubCategory("Black Olive","ToppingPizza",5.0,"urlImageBlackOlive"));
        daoSubCat.insert(new SubCategory("Mushroom","ToppingPizza",5.0,"urlImageMushroom"));
        daoSubCat.insert(new SubCategory("Red Pepper","ToppingPizza",5.0,"urlImageRedPepper"));
        daoSubCat.insert(new SubCategory("Green Pepper","ToppingPizza",5.0,"urlImageGreenPepper"));
        daoSubCat.insert(new SubCategory("Onion","ToppingPizza",5.0,"urlImageOnion"));
        daoSubCat.insert(new SubCategory("Egg","ToppingPizza",5.0,"urlImageEgg"));*/
    }

}
