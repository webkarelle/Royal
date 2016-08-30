package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.SubCategory;
import karelle.env.royal.models.SubOrderDetail;

public class SaucePastaActivity extends AppCompatActivity {


        //DAO
        SubCategoriesDAO daoSubCat;
        SubOrderDetailsDAO daoSOD;

        //ArrayList
        ArrayList<SubOrderDetail> SODArrayList;
        ArrayList<SubCategory> saucesPastaArrayList;

        //Array
        String[] saucesPastaArray;
        int checkedSaucePasta=0;//la position de l 'item selectionné

        //Bundle
        Bundle extrasIn=new Bundle();
        Bundle extrasOut=new Bundle();

        //View
        ListView listViewSauces;
        Button btnSelectionSaucePasta ;

        //Simple Variables
        int numChecked;
        int numSubCat= 0;
        long numInsert;
        String numOrderDetailTemp ="1";
        String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauce_pasta);

        //FindViewById
        listViewSauces = (ListView) findViewById(R.id.listViewSauces);
        btnSelectionSaucePasta = (Button) findViewById(R.id.btnSelectionSaucePasta);

        //Extras : 1 SOD arraylist typé Topping ou Drink + 1 boolean[] checked

            extrasIn= getIntent().getExtras();
            type =extrasIn.getString("type");
            checkedSaucePasta = extrasIn.getInt("checkedSaucePasta",checkedSaucePasta);


        //DAO SubCategory
        daoSubCat = new SubCategoriesDAO(this);

        //Array of Toppings
        saucesPastaArray =daoSubCat.queryNamesToppingsByType("SaucePasta");
        numSubCat = saucesPastaArray.length;
        //ArrayList of SubCategory
        saucesPastaArrayList = daoSubCat.queryByType("SaucePasta");


        //ListView : setAdapter & setItemChecked
        listViewSauces.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, saucesPastaArray));
        listViewSauces.setItemChecked(checkedSaucePasta,true);
        btnSelectionSaucePasta.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ArrayList<SubOrderDetail> saucesPastaSOD= new ArrayList<SubOrderDetail>();

                    //daoSOD = new SubOrderDetailsDAO(SaucePastaActivity.this);
                    //int numDeleteSOD = daoSOD.deleteBySubCat("SaucePasta");
                    //Toast.makeText(SaucePastaActivity.this, "SOD supprimés : "+numDeleteSOD, Toast.LENGTH_SHORT).show();
                    //on recupere la position selectionnée
                    checkedSaucePasta=listViewSauces.getCheckedItemPosition();
                    saucesPastaSOD.add(
                    new SubOrderDetail( saucesPastaArrayList.get(checkedSaucePasta).getNameSubC(),
                                        saucesPastaArrayList.get(checkedSaucePasta).getTypeSubC(),
                                        saucesPastaArrayList.get(checkedSaucePasta).getPriceSubC(),
                                        numOrderDetailTemp)
                    );

                    Intent i = new Intent(SaucePastaActivity.this,PastaActivity.class);
                    extrasOut.putInt("checkedSaucePasta",checkedSaucePasta);
                    extrasOut.putParcelableArrayList("saucesPastaSOD",saucesPastaSOD);
                    i.putExtras(extrasOut);
                    setResult(RESULT_OK,i);
                    finish();
                }
            });
    }}
