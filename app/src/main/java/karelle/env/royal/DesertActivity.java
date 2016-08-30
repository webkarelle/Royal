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

public class DesertActivity extends AppCompatActivity {

    //DAO
    CategoriesDAO daoCat;
    OrderDetailsDAO daoOD;


    //ArrayList
    ArrayList<Category> catArrayList;

    //Array
    String[] desertsArray;
    SparseBooleanArray CheckedItemPositions;//equivalent Hash map <Integer, Boolean>

    //Bundle
    Bundle extrasIn = new Bundle();
    Bundle extrasOut = new Bundle();

    //View
    ListView listViewDeserts;
    Button btnValChoicesDesert;

    //Simple Variables
    int numCat = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desert);

        //FindViewById
        listViewDeserts = (ListView) findViewById(R.id.listViewDeserts);
        btnValChoicesDesert = (Button) findViewById(R.id.btnValChoicesDesert);




        //DAO Category

        daoCat = new CategoriesDAO(this);
        daoOD = new OrderDetailsDAO(this);

        //ArrayList of Drinkss
        catArrayList = daoCat.queryByType("Desert");
        numCat = catArrayList.size();
        //ArrayList of SubCategory
        desertsArray = new String[numCat];
        for (int i = 0; i < numCat; i++) {
            desertsArray[i] = catArrayList.get(i).getNameCat();
        }

        //ListView : setAdapter & setItemChecked
        listViewDeserts.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, desertsArray));



        btnValChoicesDesert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<OrderDetail> desertsOD = new ArrayList<OrderDetail>();


                //on recupere la hashmap des cases sélectionnées
                CheckedItemPositions = listViewDeserts.getCheckedItemPositions();
                for (int j = 0; j < numCat; j++) {

                    if (listViewDeserts.getCheckedItemPositions().get(j)) {
                        desertsOD.add(new OrderDetail("1",
                                catArrayList.get(j).getNameCat(),
                                catArrayList.get(j).getTypeCat(),
                                catArrayList.get(j).getPriceCat()));

                    }
                }


                for (int i=0;i< desertsOD.size();i++) {
                    daoOD.insertFull(desertsOD.get(i));
                }

                Intent i = new Intent(DesertActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

