package karelle.env.royal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import karelle.env.royal.db.FinalOrdersDAO;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.fragments.ChooseStoreFragment;
import karelle.env.royal.fragments.DeliveryFragment;
import karelle.env.royal.fragments.TimeFragment;
import karelle.env.royal.fragments.WhereFragment;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubOrderDetail;

public class CheckOutActivity extends AppCompatActivity {
Spinner spWhere;
TimePicker mTimePicker;
    String numOrderString="",priceOrderString="";
    Button btnNowWhen,btntodayWhen,btnGo;
    ImageView ivValideMode,ivValideWhere,ivValideWhen,ivCarryOut,ivDelivery;
    //ImageButton;
    TextView tvChoiceMode,tvMode,tvChooseWhen,tvWhen;
    boolean isDelivery;
    OrderDetailsDAO daoOD;
    OrdersDAO daoOrder;
    SubOrderDetailsDAO daoSOD;
    FinalOrdersDAO daoFinal;

    //Order's Data

        String idCust="";//idUserFirebase
        double priceOrder=0.0;
        String  dateOrder="";
        String statutOrder="purchase order";//purchase order, received, in preparation, sent, delivered
        String  modeOrder="";//CarryOut or Delivery
        String placeDelivery="";//home or Store1 or Store2
        String addDelivery="";//address Delivery if delivery
        String dateDelivery="";//hope dateTime delivery



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ivValideMode = (ImageView)findViewById(R.id.ivValideMode);
        ivValideMode.setVisibility(View.INVISIBLE);
        ivValideWhere = (ImageView)findViewById(R.id.ivValideWhere);
        ivValideWhere.setVisibility(View.INVISIBLE);
        ivValideWhen = (ImageView)findViewById(R.id.ivValideWhen);
        ivValideWhen.setVisibility(View.INVISIBLE);
        tvChoiceMode = (TextView )findViewById(R.id.tvChoiceMode);
        tvMode = (TextView )findViewById(R.id.tvMode);
        tvWhen =(TextView)findViewById(R.id.tvWhen);
        tvChooseWhen =(TextView)findViewById(R.id.tvChooseWhen);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,new WhereFragment()).commit();

        ivDelivery = (ImageView)findViewById(R.id.ivDelivery);
        ivDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDelivery = true;
                modeOrder="Delivery";
                placeDelivery="Home";
                addDelivery="addressHome";
                ivValideMode.setVisibility(View.VISIBLE);
                tvChoiceMode.setText("Delivery");
                tvMode.setText("Mode Choosed :  ");
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,new DeliveryFragment()).commit();
            }
        });
        ivCarryOut = (ImageView)findViewById(R.id.ivCarryOut);
        ivCarryOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDelivery= false;
                modeOrder="CarryOut";
                ivValideMode.setVisibility(View.VISIBLE);
                tvChoiceMode.setText("CarryOut");
                placeDelivery="Store1";//or other Store
                tvMode.setText("Mode Choosed :  ");
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,new ChooseStoreFragment()).commit();
            }
        });


        btntodayWhen = (Button) findViewById(R.id.btntodayWhen);
        btntodayWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args=new Bundle();
                TimeFragment timeF =new TimeFragment();
                String timeString="";
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutTime,timeF).commit();
                //args=timeF.getArguments();
                //timeString= args.getString("time",timeString);
                dateDelivery="For Today at ";
                ivValideWhen.setVisibility(View.VISIBLE);
                tvWhen.setText(timeString);
                tvChooseWhen.setText("Time Choosed : ");


            }
        });
        //getSupportFragmentManager().beginTransaction().remove(timeF).commit();




        btnNowWhen = (Button) findViewById(R.id.btnNowWhen);
        btnNowWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivValideWhen.setVisibility(View.VISIBLE);
                tvWhen.setText("now");
                tvChooseWhen.setText("Time Choosed : ");
                dateDelivery="For Now";

            }
        });


        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDB();
                Intent i = new Intent(CheckOutActivity.this,FinalActivity.class);
                i.putExtra("numOrderString",numOrderString);
                i.putExtra("priceOrderString",priceOrderString);
                startActivity(i);
            }
        });



    }

    public void updateDB()
    {
        daoOD=new OrderDetailsDAO(this);
        daoOrder=new OrdersDAO(this);
        daoSOD =new SubOrderDetailsDAO(this);
        daoFinal =new FinalOrdersDAO(this);
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String dateNow = df.format(Calendar.getInstance().getTime());
        dateOrder=dateNow;
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        idCust=currentUser.getUid();
        Order o =new Order(
                idCust,
                priceOrder,
                dateOrder,
                statutOrder,
                modeOrder,
                placeDelivery,
                addDelivery,
                dateDelivery);


        Long idOrder;
        Long idOD;
        String idODTemp;
        Long idSOD;



        idOrder= daoFinal.insertOrderFull(o);
        o.setIdo(String.valueOf(idOrder));

        //Retreive all the confirmed OrderDetails and Create all the OrderDetails
        OrderDetail od =new OrderDetail();
        ArrayList<OrderDetail> arrayListOD = daoOD.query();
        //Toast.makeText(CheckOutActivity.this, "taille de daoOD.query() "+arrayListOD.size(), Toast.LENGTH_SHORT).show();

        SubOrderDetail sod = new SubOrderDetail();
        ArrayList<SubOrderDetail> arrayListSOD =new ArrayList<SubOrderDetail>();
        for(int i = 0;i<arrayListOD.size();i++ )
        {
            od=arrayListOD.get(i);
            od.setIdOrder(String.valueOf(idOrder));
            idOD=daoFinal.insertOrderDetailFull(od);
            idODTemp=od.getIdOd();
            priceOrder = priceOrder+od.getPrice();
            arrayListSOD = daoSOD.queryByIdOD(idODTemp);
            for (int j = 0;j<arrayListSOD.size();j++)
            {
                sod=arrayListSOD.get(j);
                sod.setIdOD(String.valueOf(idOD));
                idSOD= daoFinal.insertSubOrderDetailFull(sod);
            }
            od.setSubOrderDetails(arrayListSOD);
        }
       // o.setOd(arrayListOD);
        o.setPriceOrder(priceOrder);
        o.setOd(arrayListOD);
        priceOrderString=priceOrder+" sh";
        numOrderString=""+idOrder;

        //init a model of user:
        User user = new User(currentUser.getUid(), currentUser.getEmail());
        //get a reference to the users table
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Orders/toDo").child(user.getUserId());
        // save the new User under the node <userID>
        ref.push().setValue(o);
    }
}
