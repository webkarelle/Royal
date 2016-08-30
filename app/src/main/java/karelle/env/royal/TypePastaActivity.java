package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.SubCategory;
import karelle.env.royal.models.SubOrderDetail;

public class TypePastaActivity extends AppCompatActivity {

    //DAO
    SubCategoriesDAO daoSubCat;
    SubOrderDetailsDAO daoSOD;

    //ArrayList
    ArrayList<SubOrderDetail> SODArrayList;
    ArrayList<SubCategory> typePastaArrayList;

    //Array
    String[] TypePastaArray;
    int checkedTypePasta=0;//la position de l 'item selectionné

    //Bundle
    Bundle extrasIn=new Bundle();
    Bundle extrasOut=new Bundle();

    //View
    ListView listViewType;
    Button btnTypePasta ;

    //Simple Variables
    int numChecked;
    int numSubCat= 0;
    long numInsert;
    String numOrderDetailTemp ="1";
    String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_pasta);

        //FindViewById
        listViewType = (ListView) findViewById(R.id.listViewType);
        btnTypePasta = (Button) findViewById(R.id.btnTypePasta);

        //Extras : 1 SOD arraylist typé Topping ou Drink + 1 boolean[] checked

        extrasIn= getIntent().getExtras();
        type =extrasIn.getString("type");
        checkedTypePasta = extrasIn.getInt("checkedTypePasta",checkedTypePasta);


        //DAO SubCategory
        daoSubCat = new SubCategoriesDAO(this);

        //Array of Toppings
        TypePastaArray =daoSubCat.queryNamesToppingsByType("TypePasta");
        numSubCat = TypePastaArray.length;
        //ArrayList of SubCategory
        typePastaArrayList = daoSubCat.queryByType("TypePasta");


        //ListView : setAdapter & setItemChecked
        listViewType.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, TypePastaArray));
        listViewType.setItemChecked(checkedTypePasta,true);
        btnTypePasta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<SubOrderDetail> typePastaSOD= new ArrayList<SubOrderDetail>();

                //daoSOD = new SubOrderDetailsDAO(SaucePastaActivity.this);
                //int numDeleteSOD = daoSOD.deleteBySubCat("SaucePasta");
                //Toast.makeText(SaucePastaActivity.this, "SOD supprimés : "+numDeleteSOD, Toast.LENGTH_SHORT).show();
                //on recupere la position selectionnée
                checkedTypePasta=listViewType.getCheckedItemPosition();
                typePastaSOD.add(
                        new SubOrderDetail( typePastaArrayList.get(checkedTypePasta).getNameSubC(),
                                typePastaArrayList.get(checkedTypePasta).getTypeSubC(),
                                typePastaArrayList.get(checkedTypePasta).getPriceSubC(),
                                numOrderDetailTemp)
                );

                Intent i = new Intent(TypePastaActivity.this,PastaActivity.class);
                extrasOut.putInt("checkedTypePasta",checkedTypePasta);
                extrasOut.putParcelableArrayList("typePastaSOD",typePastaSOD);
                i.putExtras(extrasOut);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }}