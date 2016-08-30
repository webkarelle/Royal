package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubOrderDetail;

public class PizzaActivity extends AppCompatActivity {

    //DAO
    SubOrderDetailsDAO daoSOD;
    OrderDetailsDAO daoOD;
    SubCategoriesDAO daoSubCat;
    CategoriesDAO daoCat;

    //ArrayList
    ArrayList<SubOrderDetail> toppingsSOD= new ArrayList<SubOrderDetail>();
    ArrayList<SubOrderDetail> SOD=new ArrayList<>();
    ArrayList<OrderDetail> OD=new ArrayList<>();

    //View
    RelativeLayout layoutToppingsSelection;

    Button btnNumToppings,btnSaveOD;
    ImageButton ibCart;

    //Intent
    Intent iToppings;


    //Extras
    Bundle extras = new Bundle();
    Bundle extrasIn = new Bundle();
    Bundle extrasOut = new Bundle();

    //Array
    boolean[] checkedToppingsPizza;


    //Variables
    final int codeIntentTopping = 1;
    int numResultTopping = 0;
    int numToppings;
    int numSubCat=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        //View
        layoutToppingsSelection = (RelativeLayout) findViewById(R.id.layoutToppingsSelection);

        btnNumToppings = (Button) findViewById(R.id.btnNumToppings);

        btnSaveOD  = (Button) findViewById(R.id.btnSaveOD);



        if ( btnNumToppings.getText().equals("0"))
        {btnNumToppings.setVisibility(View.INVISIBLE);}


        //DAO
        daoOD = new OrderDetailsDAO(this);
        daoSOD = new SubOrderDetailsDAO(this);
        daoSubCat = new SubCategoriesDAO(this);
        numSubCat =daoSubCat.queryNamesToppingsByType("Pizza").length;


        //Listeners
        layoutToppingsSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (btnNumToppings.getText().equals("0") )
                {
                    checkedToppingsPizza=new boolean[numSubCat];
                    checkedToppingsPizza[0]=true;
                    for (int i=1; i<numSubCat;i++)
                    { checkedToppingsPizza[i]=false;}
                }
                iToppings = new Intent(PizzaActivity.this, ToppingsActivity.class);
                extras.putString("type","Pizza");
                extras.putBooleanArray("checkedToppingsPizza",checkedToppingsPizza);
                iToppings.putExtras(extras);
                startActivityForResult(iToppings, codeIntentTopping);
            }
        });





        btnSaveOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double priceOD=30.0;//prix initial d une pizza de base

                OrderDetail od = new OrderDetail("1","Pizza","Pizza",priceOD);

                Long numIdOD = daoOD.insertFull(od);

                Long numIdSOD;
                SubOrderDetail sod= new SubOrderDetail();

                int size= toppingsSOD.size();
                for (int i =0; i<size;i++) {
                    sod=toppingsSOD.get(i);
                    //insertion dans ArrayList ToppingsSOD du idOD associé
                    sod.setIdOD(String.valueOf(numIdOD));
                    Toast.makeText(PizzaActivity.this, "numIDOD"+numIdOD, Toast.LENGTH_SHORT).show();
                    //insertion dans BdD d'un SOD avec idOD associé
                    numIdSOD = daoSOD.insert(sod);
                    //récupération de l'idSOD de la base et insertion dans ArrayList ToppingsSOD
                    sod.setIdSOD(String.valueOf(numIdSOD));

                    //Màj du montant d'une OD = cumul des prix unitaires SOD + pizza neutre
                    priceOD=priceOD+sod.getPriceSOD();
                }
                //Màj dans l'objet od l'IdOD issu de la BdD, le prix cumulé calculé et son ArrayListSOD associé
                od.setSubOrderDetails(toppingsSOD);
                od.setPrice(priceOD);



                od.setIdOd(String.valueOf(numIdOD));
                //Màj en BdD de OrderDétail nouvellement créé
                daoOD.update(String.valueOf(numIdOD),od);



                Intent i = new Intent(PizzaActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == codeIntentTopping) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // Extras : boolean[] checked & ArrayList<SOD>

                checkedToppingsPizza = data.getBooleanArrayExtra("checkedToppingsPizza");
                toppingsSOD = data.getParcelableArrayListExtra("toppingsSOD");
                numToppings = toppingsSOD.size();
                Toast.makeText(this," il y a " +numToppings+" toppings", Toast.LENGTH_SHORT).show();
                if  (numToppings!=0)
                { btnNumToppings.setVisibility(View.VISIBLE);
                btnNumToppings.setText("+"+String.valueOf(numToppings));}

            }
        }

    }
}




