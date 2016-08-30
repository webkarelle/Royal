package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import karelle.env.royal.R;
import karelle.env.royal.adapters.CategoryAdapter;
import karelle.env.royal.adapters.OrderAdapter;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrdersDAO;

public class OrdersRecyclerActivity extends AppCompatActivity {
    private Button btnAddOrder;
    OrdersDAO daoOrder;
    RecyclerView recyclerView;
    @Override
    protected void onResume() {
        super.onResume();
        OrderAdapter adapter = (OrderAdapter) recyclerView.getAdapter();
        adapter.requery();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_recycler);

        daoOrder = new OrdersDAO(this);
        btnAddOrder = (Button)findViewById(R.id.btnAddOrder) ;
        recyclerView = (RecyclerView) findViewById(R.id.ordersRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter orderAdapter = new OrderAdapter(this);

        recyclerView.setAdapter(orderAdapter);
    }


    public void gotodetailsOrder(View view) {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

}