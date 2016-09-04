package karelle.env.royal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubCategory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    LinearLayout layoutPizza;
    LinearLayout layoutZiva;
    LinearLayout layoutSalad;
    LinearLayout layoutPasta;
    LinearLayout layoutDrink;
    LinearLayout layoutDesert;
    ArrayList<OrderDetail> arrayListOD = new ArrayList<>();
    Button btnGoToCart;
    OrderDetailsDAO daoOD;
    SubCategoriesDAO daoSubCat;
    CategoriesDAO daoCat;
    SubOrderDetailsDAO daoSOD;
    OrdersDAO ordersDAO;
    String numOrder;
    Double priceOrder;
    //Extras
    Bundle extrasIN = new Bundle();
    Bundle extrasOUT = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("RoyalPizza");








        btnGoToCart =(Button)findViewById(R.id.btnGoToCart);
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iCart = new Intent(MainActivity.this,CartActivity.class);
                startActivity(iCart);


            }
        });


        layoutPizza = (LinearLayout)findViewById(R.id.layoutPizza);
        layoutPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPizza = new Intent(MainActivity.this,PizzaActivity.class);
                startActivity(iPizza);

            }
        });
        layoutZiva = (LinearLayout)findViewById(R.id.layoutZiva);
        layoutZiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iZiva = new Intent(MainActivity.this, ZivaActivity.class);
                startActivity(iZiva);
            }
        });
        layoutSalad = (LinearLayout)findViewById(R.id.layoutSalad);
        layoutSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSalad = new Intent(MainActivity.this,SaladActivity.class);
                startActivity(iSalad);;
            }
        });
        layoutPasta = (LinearLayout)findViewById(R.id.layoutPasta);
        layoutPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPasta = new Intent(MainActivity.this,PastaActivity.class);
                startActivity(iPasta);;

            }
        });
        layoutDrink = (LinearLayout)findViewById(R.id.layoutDrink);
        layoutDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDrink = new Intent(MainActivity.this,DrinksActivity.class);
                startActivity(iDrink);
            }
        });
        layoutDesert = (LinearLayout)findViewById(R.id.layoutDesert);
        layoutDesert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDesert = new Intent(MainActivity.this,DesertActivity.class);
                startActivity(iDesert);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case(R.id.action_logout):FirebaseAuth.getInstance().signOut();
                return true;
            case(R.id.action_home):
                Intent h = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(h);
                return true;

            case(R.id.action_cart):
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
                return true;

            case(R.id.action_locate):
                Intent j = new Intent(MainActivity.this, LocateActivity.class);
                startActivity(j);

                return true;
            case(R.id.action_tracker):
                Intent k = new Intent(MainActivity.this, TrackerActivity.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
