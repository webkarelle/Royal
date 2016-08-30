package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.adapters.CartAdapter;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubOrderDetail;

public class CartActivity extends AppCompatActivity {

    TextView tvNumIdOrder,tvSumOrder;
    OrderDetailsDAO daoOD;
    ArrayList<OrderDetail> arrayListOD = new ArrayList<>();
    RecyclerView recyclerCart;
    Button btnOrder,btnUsePromotion,btnAddOrderDetail;
    String numOrder;
    Double priceOrder=0.0;
    Bundle extras = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        daoOD = new OrderDetailsDAO(this);
        arrayListOD = daoOD.query();
        for (int i =0;i<arrayListOD.size();i++)
        {priceOrder=priceOrder+arrayListOD.get(i).getPrice();}


        tvSumOrder = (TextView) findViewById(R.id.tvSumOrder);
        tvSumOrder.setText("Total : "+priceOrder+"sh");

        btnAddOrderDetail = (Button) findViewById(R.id.btnAddOrderDetail);
        btnAddOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, CheckOutActivity.class);
                startActivity(i);
            }
        });


        recyclerCart = (RecyclerView) findViewById(R.id.recyclerCart);
        CartAdapter adapter = new CartAdapter(this);
        recyclerCart.setAdapter(adapter);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));


    }
}
