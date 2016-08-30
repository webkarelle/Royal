package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.SubCategory;
import karelle.env.royal.models.SubOrderDetail;

public class ToppingsZivaActivity extends AppCompatActivity {

    //DAO
    SubCategoriesDAO daoSubCat;
    SubOrderDetailsDAO daoSOD;

    //ArrayList
    ArrayList<SubOrderDetail> SODArrayList;
    ArrayList<SubCategory> toppingsArrayList;

    //Array
    String[] toppingsArray;
    boolean[] checkedToppingsZiva;
    SparseBooleanArray CheckedItemPositions;//equivalent Hash map <Integer, Boolean>

    //Bundle
    Bundle extrasIn=new Bundle();
    Bundle extrasOut=new Bundle();

    //View
    ListView listViewToppingsZiva;
    Button btnValToppingsZiva ;

    //Simple Variables
    int numChecked;
    int numSubCat= 0;
    long numInsert;
    String numOrderDetailTemp ="1";
    String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings_ziva);

        //FindViewById
        listViewToppingsZiva = (ListView)findViewById(R.id.listViewToppingsZiva);
        btnValToppingsZiva = (Button)findViewById(R.id.btnValToppingsZiva);

        //Extras : 1 SOD arraylist typé Topping ou Drink + 1 boolean[] checked
        extrasIn= getIntent().getExtras();
        type =extrasIn.getString("type");
        checkedToppingsZiva = extrasIn.getBooleanArray("checkedToppingsZiva");

        //DAO SubCategory
        daoSubCat = new SubCategoriesDAO(this);

        //Array of Toppings
        toppingsArray =daoSubCat.queryNamesToppingsByType(type);
        numSubCat = toppingsArray.length;
        //ArrayList of SubCategory
        toppingsArrayList = daoSubCat.queryByType(type);

        //ListView : setAdapter & setItemChecked
        listViewToppingsZiva.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, toppingsArray));
        for (int i =0; i<numSubCat;i++) {
            listViewToppingsZiva.setItemChecked(i, checkedToppingsZiva[i]);}


        btnValToppingsZiva.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<SubOrderDetail> toppingsSOD= new ArrayList<SubOrderDetail>();

                CheckedItemPositions=listViewToppingsZiva.getCheckedItemPositions();
                for (int j=0; j<CheckedItemPositions.size();j++)
                {
                    checkedToppingsZiva[j]=listViewToppingsZiva.getCheckedItemPositions().get(j);

                    if (listViewToppingsZiva.getCheckedItemPositions().get(j)){
                        toppingsSOD.add(new SubOrderDetail(
                                toppingsArrayList.get(j).getNameSubC(),
                                "Ziva",toppingsArrayList.get(j).getPriceSubC(),
                                numOrderDetailTemp));
                    }
                }
                Intent i = new Intent(ToppingsZivaActivity.this,PizzaActivity.class);
                extrasOut.putBooleanArray("checkedToppingsZiva",checkedToppingsZiva);
                extrasOut.putParcelableArrayList("toppingsSOD",toppingsSOD);
                i.putExtras(extrasOut);
                setResult(RESULT_OK,i);
                finish();
            }
        });}}

