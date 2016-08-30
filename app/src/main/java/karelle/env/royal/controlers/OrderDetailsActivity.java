package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import karelle.env.royal.R;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;

public class OrderDetailsActivity extends AppCompatActivity {
    EditText etIdOrder, etTypeOD, etPriceOD;
    Button btnSaveOrUpdate;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        etIdOrder=(EditText)findViewById(R.id.etIdOrder);
        etTypeOD=(EditText)findViewById(R.id.etTypeOD);
        etPriceOD=(EditText)findViewById(R.id.etPriceOD);


        btnSaveOrUpdate=(Button)findViewById(R.id.btnSaveOrUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");
        if (id != null) {
            //init the dao:
            OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO(this);
            //get the orderDetail from the dao by id
            OrderDetail od = orderDetailsDao.query(id);

            //fill the edittext using the orderDetail:
            etIdOrder.setText(od.getIdOd());
            etTypeOD.setText(od.getTypeOd());
            etPriceOD.setText(String.valueOf(od.getPrice()));

        }
        btnSaveOrUpdate.setText(id != null ? "Update" : "Insert");
    }
    public void saveOrderDetail(View view) {
        OrderDetailsDAO orderDetailsdao = new OrderDetailsDAO(this);

        OrderDetail od = new OrderDetail(
                etIdOrder.getText().toString(),
                etTypeOD.getText().toString(),
                Double.valueOf(etPriceOD.getText().toString())
        );

        //if (_id!=null) call update instead.
        if (id != null) {
            orderDetailsdao.update(id, od);
        } else {
            orderDetailsdao.insertFull(od);
        }

        Intent mainIntent = new Intent(this, OrderDetailsRecyclerActivity.class);
        startActivity(mainIntent);

        //finish();
    }
}