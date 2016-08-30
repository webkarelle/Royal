package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import karelle.env.royal.R;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.Order;

public class OrdersActivity extends AppCompatActivity {
    EditText etIdCust, etDateOrder, etPriceOrder, etStatutOrder;
    Button btnSaveOrUpdate;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        etIdCust=(EditText)findViewById(R.id.etIdCust);
        etDateOrder=(EditText)findViewById(R.id.etDateOrder);
        etPriceOrder=(EditText)findViewById(R.id.etPriceOrder);
        etStatutOrder=(EditText)findViewById(R.id.etStatutOrder);

        btnSaveOrUpdate=(Button)findViewById(R.id.btnSaveOrUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");
        if (id != null) {
            //init the dao:
            OrdersDAO ordersDao = new OrdersDAO(this);
            //get the category from the dao by id
            Order o = ordersDao.query(id);

            //fill the edittext using the order:
            etIdCust.setText(o.getIdCust());
            etDateOrder.setText(o.getDateOrder());
            etPriceOrder.setText(String.valueOf(o.getPriceOrder()));
            etStatutOrder.setText(o.getStatutOrder());
        }
        btnSaveOrUpdate.setText(id != null ? "Update" : "Insert");
    }
    public void saveOrder(View view) {
        OrdersDAO ordersdao = new OrdersDAO(this);

        /*Order o = new Order(
                etIdCust.getText().toString(),
                Double.valueOf(etPriceOrder.getText().toString()),
                etDateOrder.getText().toString(),
                etStatutOrder.getText().toString()
        );

        //if (_id!=null) call update instead.
        if (id != null) {
            ordersdao.update(id, o);
        } else {
            ordersdao.insert(o);
        }*/

        Intent mainIntent = new Intent(this, OrdersRecyclerActivity.class);
        startActivity(mainIntent);

        //finish();
    }
}
