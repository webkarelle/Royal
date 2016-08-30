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

public class PastaActivity extends AppCompatActivity {
    //DAO
    SubOrderDetailsDAO daoSOD;
    OrderDetailsDAO daoOD;
    SubCategoriesDAO daoSubCat;
    CategoriesDAO daoCat;

    //ArrayList
    ArrayList<SubOrderDetail> saucePastaSOD= new ArrayList<SubOrderDetail>();
    ArrayList<SubOrderDetail> typePastaSOD= new ArrayList<SubOrderDetail>();
    ArrayList<SubOrderDetail> SOD=new ArrayList<>();
    ArrayList<OrderDetail> OD=new ArrayList<>();

    //View
    RelativeLayout layoutTypePastaSelection;
    RelativeLayout layoutSaucePastaSelection;

    Button btnTypePasta,btnSaucePasta,btnSaveOD;
    ImageButton ibCart;

    //Intent
    Intent iTypePasta;
    Intent iSaucePasta;


    //Extras
    Bundle extras = new Bundle();
    Bundle extrasIn = new Bundle();
    Bundle extrasOut = new Bundle();

    //Array
    //boolean[] checkedToppingsPizza;


    //Variables
    final int codeIntentTypePasta = 1;
    final int codeIntentSaucePasta = 2;
    int numSaucesPasta=0,checkedSaucePasta=0,checkedTypePasta=0,numTypesPasta=0,numCheckedSaucePasta,numCheckedTypePasta=0;
    Boolean isSaucePasta,isTypePasta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta);

        //View
        layoutTypePastaSelection = (RelativeLayout) findViewById(R.id.layoutTypePastaSelection);
        layoutSaucePastaSelection = (RelativeLayout) findViewById(R.id.layoutSaucePastaSelection);

        btnTypePasta = (Button) findViewById(R.id.btnTypePasta);
        btnSaucePasta = (Button) findViewById(R.id.btnSaucePasta);

        btnSaveOD  = (Button) findViewById(R.id.btnSaveOD);



        if ( btnTypePasta.getText().equals("-"))
        {btnTypePasta.setVisibility(View.INVISIBLE);}
        if ( btnSaucePasta.getText().equals("-"))
        {btnSaucePasta.setVisibility(View.INVISIBLE);}


        //DAO
        daoOD = new OrderDetailsDAO(this);
        daoSOD = new SubOrderDetailsDAO(this);
        daoSubCat = new SubCategoriesDAO(this);
        daoCat = new CategoriesDAO(this);
        numSaucesPasta =daoSubCat.queryNamesToppingsByType("SaucePasta").length;
        numTypesPasta =daoSubCat.queryNamesToppingsByType("TypePasta").length;



        //Listeners
        layoutTypePastaSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iTypePasta = new Intent(PastaActivity.this, TypePastaActivity.class);
                extras.putString("type","Pasta");
                extras.putInt("checkedTypePasta",checkedTypePasta);
                iTypePasta.putExtras(extras);
                startActivityForResult(iTypePasta, codeIntentTypePasta);
            }
        });

        layoutSaucePastaSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                iSaucePasta = new Intent(PastaActivity.this, SaucePastaActivity.class);
                extras.putString("type","Pasta");
                extras.putInt("checkedSaucePasta",checkedSaucePasta);
                iSaucePasta.putExtras(extras);
                startActivityForResult(iSaucePasta, codeIntentSaucePasta);
            }
        });



        btnSaveOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double priceOD=30.0;//prix de pasta de base
                SOD.add(typePastaSOD.get(0));
                SOD.add(saucePastaSOD.get(0));

                OrderDetail od = new OrderDetail("1","Pasta","Pasta",priceOD);

                Long numIdOD = daoOD.insertFull(od);

                Long numIdSOD;
                SubOrderDetail sod= new SubOrderDetail();

                int size= SOD.size();
                for (int i =0; i<size;i++) {
                    sod=SOD.get(i);
                    //insertion dans ArrayList ToppingsSOD du idOD associé
                    sod.setIdOD(String.valueOf(numIdOD));

                    //insertion dans BdD d'un SOD avec idOD associé
                    numIdSOD = daoSOD.insert(sod);
                    //récupération de l'idSOD de la base et insertion dans ArrayList ToppingsSOD
                    sod.setIdSOD(String.valueOf(numIdSOD));

                    //Màj du montant d'une OD = cumul des prix unitaires SOD + pizza neutre
                    priceOD=priceOD+sod.getPriceSOD();
                }
                //Màj dans l'objet od l'IdOD issu de la BdD, le prix cumulé calculé et son ArrayListSOD associé
                od.setSubOrderDetails(SOD);
                od.setPrice(priceOD);


                od.setIdOd(String.valueOf(numIdOD));
                //Màj en BdD de OrderDétail nouvellement créé
                daoOD.update(String.valueOf(numIdOD),od);


                Intent i = new Intent(PastaActivity.this,MainActivity.class);

                startActivity(i);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == codeIntentTypePasta) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // Extras : boolean[] checked & ArrayList<SOD>

                checkedTypePasta = data.getIntExtra("checkedTypePasta",checkedTypePasta);
               typePastaSOD = data.getParcelableArrayListExtra("typePastaSOD");
                numCheckedTypePasta = typePastaSOD.size();
               Toast.makeText(this," il y a " +numCheckedTypePasta+" numCheckedTypePasta", Toast.LENGTH_SHORT).show();
                if (numCheckedTypePasta==1)
                {
                    btnTypePasta.setVisibility(View.VISIBLE);
                    btnTypePasta.setText(" ");}

            }
        } else if (requestCode == codeIntentSaucePasta) {
            if (resultCode == RESULT_OK) {

                // Extras : int checked & ArrayList<SOD>

                checkedSaucePasta = data.getIntExtra("checkedSaucePasta",checkedSaucePasta);
                saucePastaSOD = data.getParcelableArrayListExtra("saucesPastaSOD");
                numCheckedSaucePasta = saucePastaSOD.size();

                Toast.makeText(this," il y a " +numCheckedSaucePasta+" sauce", Toast.LENGTH_SHORT).show();


               if (numCheckedSaucePasta==1)
               {
                   btnSaucePasta.setVisibility(View.VISIBLE);
                   btnSaucePasta.setText(" ");}
            }
        }

    }
}