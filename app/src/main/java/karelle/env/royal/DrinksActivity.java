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
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.Order;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubCategory;
import karelle.env.royal.models.SubOrderDetail;

public class DrinksActivity extends AppCompatActivity {

    //DAO
    CategoriesDAO daoCat;
    OrderDetailsDAO daoOD;


    //ArrayList
    ArrayList<OrderDetail> ODArrayList;
    ArrayList<Category> catArrayList;

    //Array
    String[] drinksArray;
    SparseBooleanArray CheckedItemPositions;//equivalent Hash map <Integer, Boolean>

    //Bundle
    Bundle extrasIn = new Bundle();
    Bundle extrasOut = new Bundle();

    //View
    ListView listViewDrinks;
    Button btnValChoicesDrink;

    //Simple Variables
    int numCat = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        //FindViewById
        listViewDrinks = (ListView) findViewById(R.id.listViewDrinks);
        btnValChoicesDrink = (Button) findViewById(R.id.btnValChoicesDrink);




        //DAO Category
        daoCat = new CategoriesDAO(this);
        daoOD = new OrderDetailsDAO(this);


        //ArrayList of Drinkss
        catArrayList = daoCat.queryByType("Drink");
        numCat = catArrayList.size();
        //ArrayList of SubCategory
        drinksArray = new String[numCat];
        for (int i = 0; i < numCat; i++) {
            drinksArray[i] = catArrayList.get(i).getNameCat();
        }

        //ListView : setAdapter & setItemChecked
        listViewDrinks.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, drinksArray));



            btnValChoicesDrink.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ArrayList<OrderDetail> drinksOD = new ArrayList<OrderDetail>();


                    //on recupere la hashmap des cases sélectionnées
                    CheckedItemPositions = listViewDrinks.getCheckedItemPositions();
                    for (int j = 0; j < numCat; j++) {
                        //checkedDrinks[j] = listViewDrinks.getCheckedItemPositions().get(j);

                        if (listViewDrinks.getCheckedItemPositions().get(j)) {
                            drinksOD.add(new OrderDetail("1",
                                    catArrayList.get(j).getNameCat(),
                                    catArrayList.get(j).getTypeCat(),
                                    catArrayList.get(j).getPriceCat()));


                        }
                    }
                    for (int i=0;i< drinksOD.size();i++) {
                        daoOD.insertFull(drinksOD.get(i));
                    }


                    Intent i = new Intent(DrinksActivity.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }






