package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import karelle.env.royal.models.User;

public class CheckOutActivity extends AppCompatActivity {
Spinner spWhere;
TimePicker mTimePicker;
    String numOrderString="",priceOrderString="",timeString="11H";
    int timeInt = 11;
    Button btnNowWhen,btntodayWhen,btnGo;
    ImageView ivValideMode,ivValideWhere,ivValideWhen,ivCarryOut,ivDelivery,ivMinus,ivPlus,ivStore1,ivStore2,ivHomeDelivery;
    //ImageButton;
    TextView tvChoiceMode,tvMode,tvChooseWhen,tvWhen,tvChooseWhere,tvWhere,tvTime,tvAddStore1,tvAddStore2;
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


        ivStore1= (ImageView)findViewById(R.id.ivStore1);
        ivStore2= (ImageView)findViewById(R.id.ivStore2);
        ivHomeDelivery = (ImageView)findViewById(R.id.ivHomeDelivery);


        ivValideMode = (ImageView)findViewById(R.id.ivValideMode);
        ivValideMode.setVisibility(View.INVISIBLE);
        ivValideWhere = (ImageView)findViewById(R.id.ivValideWhere);
        ivValideWhere.setVisibility(View.INVISIBLE);
        ivValideWhen = (ImageView)findViewById(R.id.ivValideWhen);
        ivValideWhen.setVisibility(View.INVISIBLE);
        tvChoiceMode = (TextView )findViewById(R.id.tvChoiceMode);
        tvMode = (TextView )findViewById(R.id.tvMode);
        tvTime = (TextView)findViewById(R.id.tvTime);
        tvTime.setVisibility(View.INVISIBLE);
        ivMinus = (ImageView)findViewById(R.id.ivMinus);
        ivMinus.setVisibility(View.INVISIBLE);
        ivPlus = (ImageView)findViewById(R.id.ivPlus);
        ivPlus.setVisibility(View.INVISIBLE);
        tvWhen =(TextView)findViewById(R.id.tvWhen);
        tvChooseWhen =(TextView)findViewById(R.id.tvChooseWhen);
        tvChooseWhere=(TextView)findViewById(R.id.tvChooseWhere);
        tvWhere =(TextView)findViewById(R.id.tvWhere);

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
                tvMode.setText("Mode :  ");
                ivValideWhere.setVisibility(View.VISIBLE);
                tvWhere.setText("HomeDelivery");
                tvChooseWhere.setText("Where : ");

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
                tvMode.setText("Mode :  ");
                ivValideWhere.setVisibility(View.VISIBLE);
                tvChooseWhere.setText("Where : ");
                tvWhere.setText("Store");

            }
        });


        btntodayWhen = (Button) findViewById(R.id.btntodayWhen);
        btntodayWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(timeString);
                ivPlus.setVisibility(View.VISIBLE);
                ivPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (timeInt<22)
                        {timeInt++;}
                        else
                        {timeInt=22;
                            Toast.makeText(CheckOutActivity.this, "Delivery max 22H", Toast.LENGTH_SHORT).show();}
                        timeString=timeInt+"H";
                        tvTime.setText(timeString);
                        tvWhen.setText(timeString);
                        dateDelivery=timeString;
                    }
                });
                ivMinus.setVisibility(View.VISIBLE);
                ivMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (timeInt>12)
                        {timeInt--;}
                        else
                        {timeInt=11;
                            Toast.makeText(CheckOutActivity.this, "Delivery min 11H", Toast.LENGTH_SHORT).show();}
                        timeString=timeInt+"H";
                        tvTime.setText(timeString);
                        tvWhen.setText(timeString);
                        dateDelivery=timeString;
                    }
                });
                ivValideWhen.setVisibility(View.VISIBLE);
                tvWhen.setText(tvTime.getText().toString());
                tvChooseWhen.setText("When : ");


            }
        });





        btnNowWhen = (Button) findViewById(R.id.btnNowWhen);
        btnNowWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivValideWhen.setVisibility(View.VISIBLE);
                tvWhen.setText("Now");
                tvChooseWhen.setText("When : ");
                dateDelivery="For Now";
                tvTime.setVisibility(View.INVISIBLE);
                ivMinus.setVisibility(View.INVISIBLE);
                ivPlus.setVisibility(View.INVISIBLE);


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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Orders").child(user.getUserId());
        // save the new User under the node <userID>
        ref.push().setValue(o);
    }
}
