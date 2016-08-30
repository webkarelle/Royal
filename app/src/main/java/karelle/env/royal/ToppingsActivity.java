package karelle.env.royal;

import android.app.Dialog;
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

public class ToppingsActivity extends AppCompatActivity {

    //DAO
    SubCategoriesDAO daoSubCat;
    SubOrderDetailsDAO daoSOD;

    //ArrayList
    ArrayList<SubOrderDetail> SODArrayList;
    ArrayList<SubCategory> toppingsArrayList;

    //Array
    String[] toppingsArray;
    boolean[] checkedToppingsPizza;
    SparseBooleanArray CheckedItemPositions;//equivalent Hash map <Integer, Boolean>

    //Bundle
    Bundle extrasIn=new Bundle();
    Bundle extrasOut=new Bundle();

    //View
    ListView listViewToppings;
    Button btnValToppingsPizza ;

    //Simple Variables
    int numChecked;
    int numSubCat= 0;
    long numInsert;
    String numOrderDetailTemp ="1";
    String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);

        //FindViewById
        listViewToppings = (ListView)findViewById(R.id.listViewToppings);
        btnValToppingsPizza = (Button)findViewById(R.id.btnValToppingsPizza);

        //Extras : 1 SOD arraylist typé Topping ou Drink + 1 boolean[] checked
        extrasIn= getIntent().getExtras();
        type =extrasIn.getString("type");
        checkedToppingsPizza = extrasIn.getBooleanArray("checkedToppingsPizza");

        //DAO SubCategory
        daoSubCat = new SubCategoriesDAO(this);

        //Array of Toppings
        toppingsArray =daoSubCat.queryNamesToppingsByType(type);
        numSubCat = toppingsArray.length;
        //ArrayList of SubCategory
        toppingsArrayList = daoSubCat.queryByType(type);

        //ListView : setAdapter & setItemChecked
        listViewToppings.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, toppingsArray));
        for (int i =0; i<numSubCat;i++) {
            listViewToppings.setItemChecked(i, checkedToppingsPizza[i]);}


        btnValToppingsPizza.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<SubOrderDetail> toppingsSOD= new ArrayList<SubOrderDetail>();

                /*daoSOD = new SubOrderDetailsDAO(ToppingsActivity.this);
                int numDeleteSOD = daoSOD.deleteByCat("Pizza");
                Toast.makeText(ToppingsActivity.this, "SOD supprimés : "+numDeleteSOD, Toast.LENGTH_SHORT).show();*/
                //on recupere la hashmap des cases sélectionnées
                CheckedItemPositions=listViewToppings.getCheckedItemPositions();
                for (int j=0; j<CheckedItemPositions.size();j++)
                {
                    checkedToppingsPizza[j]=listViewToppings.getCheckedItemPositions().get(j);

                    if (listViewToppings.getCheckedItemPositions().get(j)){
                        toppingsSOD.add(new SubOrderDetail(
                                toppingsArrayList.get(j).getNameSubC(),
                                "Pizza",toppingsArrayList.get(j).getPriceSubC(),
                                numOrderDetailTemp));
                    }
                }
                Intent i = new Intent(ToppingsActivity.this,PizzaActivity.class);
                extrasOut.putBooleanArray("checkedToppingsPizza",checkedToppingsPizza);
                extrasOut.putParcelableArrayList("toppingsSOD",toppingsSOD);
                i.putExtras(extrasOut);
                setResult(RESULT_OK,i);
                finish();
            }
    });}}


