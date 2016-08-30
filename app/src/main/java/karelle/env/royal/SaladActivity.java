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

import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;

public class SaladActivity extends AppCompatActivity {
    //DAO
    CategoriesDAO daoCat;
    OrderDetailsDAO daoOD;

    //ArrayList
    ArrayList<Category> catArrayList;

    //Array
    String[] saladsArray;
    SparseBooleanArray CheckedItemPositions;//equivalent Hash map <Integer, Boolean>

    //Bundle
    Bundle extrasIn = new Bundle();
    Bundle extrasOut = new Bundle();

    //View
    ListView listViewSalads;
    Button btnValChoicesSalad;

    //Simple Variables
    int numCat = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad);

        //FindViewById
        listViewSalads = (ListView) findViewById(R.id.listViewSalads);
        btnValChoicesSalad = (Button) findViewById(R.id.btnValChoicesSalad);


        //DAO Category
        daoCat = new CategoriesDAO(this);
        daoOD = new OrderDetailsDAO(this);

        //ArrayList of Drinkss
        catArrayList = daoCat.queryByType("Salad");
        numCat = catArrayList.size();
        //ArrayList of SubCategory
        saladsArray = new String[numCat];
        for (int i = 0; i < numCat; i++) {
            saladsArray[i] = catArrayList.get(i).getNameCat();
        }

        //ListView : setAdapter & setItemChecked
        listViewSalads.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, saladsArray));



        btnValChoicesSalad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<OrderDetail> saladsOD = new ArrayList<OrderDetail>();


                //on recupere la hashmap des cases sélectionnées
                CheckedItemPositions = listViewSalads.getCheckedItemPositions();
                for (int j = 0; j < numCat; j++) {
                    //checkedDrinks[j] = listViewDrinks.getCheckedItemPositions().get(j);

                    if (listViewSalads.getCheckedItemPositions().get(j)) {
                        saladsOD.add(new OrderDetail("1",
                                catArrayList.get(j).getNameCat(),
                                catArrayList.get(j).getTypeCat(),
                                catArrayList.get(j).getPriceCat()));


                    }
                }
                for (int i=0;i< saladsOD.size();i++) {
                    daoOD.insertFull(saladsOD.get(i));
                }

                Intent i = new Intent(SaladActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
