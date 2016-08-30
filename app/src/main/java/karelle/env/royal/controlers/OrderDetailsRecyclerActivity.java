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
import karelle.env.royal.adapters.OrderDetailAdapter;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrderDetailsDAO;

public class OrderDetailsRecyclerActivity extends AppCompatActivity {

    private Button btnAddOD;
    OrderDetailsDAO oddao;
    RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        OrderDetailAdapter adapter = (OrderDetailAdapter) recyclerView.getAdapter();
        adapter.requery();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_recycler);
        oddao = new OrderDetailsDAO(this);
        btnAddOD = (Button) findViewById(R.id.btnAddOD);
        recyclerView = (RecyclerView) findViewById(R.id.orderDetailsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this);
        recyclerView.setAdapter(orderDetailAdapter);
    }


    public void gotodetailsOD(View view) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        startActivity(intent);
    }
}