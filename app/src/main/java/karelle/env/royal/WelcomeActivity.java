package karelle.env.royal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.SubCategory;

public class WelcomeActivity extends AppCompatActivity {
    private BottomSheetBehavior bsb;
    LinearLayout layoutWelcome,layoutBottomSheet;
    RelativeLayout layoutCart;

    Button btnStartOrder;
    OrderDetailsDAO daoOD;
    SubCategoriesDAO daoSubCat;
    CategoriesDAO daoCat;
    SubOrderDetailsDAO daoSOD;
    OrdersDAO ordersDAO;
    double priceOrder=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initDataDB();
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                if (currentUser == null) {
                    /**
                     * Start an intent without adding the activity to the stack
                     */
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(WelcomeActivity.this, "Hello, " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        View bottomSheetView =findViewById(R.id.nsBottomSheet);
        bsb=BottomSheetBehavior.from(bottomSheetView);
        layoutCart = (RelativeLayout)findViewById(R.id.layoutCart);
        layoutWelcome= (LinearLayout)findViewById(R.id.layoutWelcome);
        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "j ai clicke sur le layoutCart", Toast.LENGTH_SHORT).show();
               switch (bsb.getState())
                {   case BottomSheetBehavior.STATE_COLLAPSED :
                        bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED :
                        bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                }
            }
        });
        btnStartOrder = (Button)findViewById(R.id.btnStartOrder);
        btnStartOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    private void initDataDB() {


        daoSOD = new SubOrderDetailsDAO(this);
        daoSOD.delete();
        daoOD = new OrderDetailsDAO(this);
        daoOD.delete();
        ordersDAO = new OrdersDAO(this);
        ordersDAO.delete();
        daoSubCat = new SubCategoriesDAO(this);
        daoSubCat.delete();
        daoCat = new CategoriesDAO(this);
        daoCat.delete();


        //initOrder
        priceOrder=0.0;



        //init  pizza

        daoSubCat.insert(new SubCategory("Base(Sauce+Cheese)","Pizza",5.0,"urlImageBase"));
        daoSubCat.insert(new SubCategory("Tuna","Pizza",5.0,"urlImageTuna"));
        daoSubCat.insert(new SubCategory("Green Olive","Pizza",5.0,"urlImageGreenOlive"));
        daoSubCat.insert(new SubCategory("Black Olive","Pizza",5.0,"urlImageBlackOlive"));
        daoSubCat.insert(new SubCategory("Mushroom","Pizza",5.0,"urlImageMushroom"));
        daoSubCat.insert(new SubCategory("Red Pepper","Pizza",5.0,"urlImageRedPepper"));
        daoSubCat.insert(new SubCategory("Green Pepper","Pizza",5.0,"urlImageGreenPepper"));
        daoSubCat.insert(new SubCategory("Onion","Pizza",5.0,"urlImageOnion"));
        daoSubCat.insert(new SubCategory("Egg","Pizza",5.0,"urlImageEgg"));

        //type Pasta
        daoSubCat.insert(new SubCategory("Spaghetti","TypePasta",0.0,"urlImage"));
        daoSubCat.insert(new SubCategory("Penne","TypePasta",0.0,"urlImage"));
        daoSubCat.insert(new SubCategory("Tornicotti","TypePasta",0.0,"urlImage"));

        //sauce Pasta
        daoSubCat.insert(new SubCategory("Cream&Mushroom","SaucePasta",5.0,"urlImage"));
        daoSubCat.insert(new SubCategory("OliveOil&Garlic","SaucePasta",0.0,"urlImage"));
        daoSubCat.insert(new SubCategory("Tomatoe","SaucePasta",0.0,"urlImage"));






        //init Toppings Ziva
        daoSubCat.insert(new SubCategory("Tuna","Ziva",5.0,"urlImageTuna"));
        daoSubCat.insert(new SubCategory("Green Olive","Ziva",5.0,"urlImageGreenOlive"));
        daoSubCat.insert(new SubCategory("Black Olive","Ziva",5.0,"urlImageBlackOlive"));
        daoSubCat.insert(new SubCategory("Mushroom","Ziva",5.0,"urlImageMushroom"));
        daoSubCat.insert(new SubCategory("Red Pepper","Ziva",5.0,"urlImageRedPepper"));

        //init Drinks
        daoCat.insert(new Category("CocaCola","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("CocaCola Zero","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("CocaCola Light","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("Fanta","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("Sprite","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("Sprite Light","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("Water","Drink",5.0,"urlImage"));
        daoCat.insert(new Category("OrangeJus","Drink",5.0,"urlImage"));
        //init Deserts
        daoCat.insert(new Category("IceCream Vanille","Desert",10.0,"urlImage"));
        daoCat.insert(new Category("IceCream Chocolate","Desert",10.0,"urlImage"));
        daoCat.insert(new Category("IceCream Strawberry","Desert",10.0,"urlImage"));
        daoCat.insert(new Category("Apple Pie","Desert",15.0,"urlImage"));
        daoCat.insert(new Category("Donuts","Desert",5.0,"urlImage"));
        daoCat.insert(new Category("Cookies Light","Desert",5.0,"urlImage"));
        daoCat.insert(new Category("Italian Cake","Desert",15.0,"urlImage"));
        //init  Salads
        daoCat.insert(new Category("Tuna Salad","Salad",22.0,"urlImage"));
        daoCat.insert(new Category("Ceasar Salad","Salad",25.0,"urlImage"));
        daoCat.insert(new Category("French Salad","Salad",30.0,"urlImage"));
        daoCat.insert(new Category("American Salad","Salad",35.0,"urlImage"));

    }

    public void test(View view) {
    }
}