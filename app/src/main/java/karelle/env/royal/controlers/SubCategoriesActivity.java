package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import karelle.env.royal.R;
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.models.SubCategory;

public class SubCategoriesActivity extends AppCompatActivity {
    EditText etNameSubCategory, etTypeSubCategory, etPriceSubCategory, etImageSubCategory;
    Button btnSaveOrUpdate;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);
        etNameSubCategory=(EditText)findViewById(R.id.etNameSubCategory);
        etTypeSubCategory=(EditText)findViewById(R.id.etTypeSubCategory);
        etPriceSubCategory=(EditText)findViewById(R.id.etPriceSubCategory);
        etImageSubCategory=(EditText)findViewById(R.id.etImageSubCategory);

        btnSaveOrUpdate=(Button)findViewById(R.id.btnSaveOrUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");
        if (id != null) {
            //init the dao:
            SubCategoriesDAO SubCatDao = new SubCategoriesDAO(this);
            //get the category from the dao by id
            SubCategory subCat = SubCatDao.query(id);

            //fill the edittext using the song:
            etNameSubCategory.setText(subCat.getNameSubC());
            etTypeSubCategory.setText(subCat.getTypeSubC());
            etPriceSubCategory.setText(String.valueOf(subCat.getPriceSubC()));
            etImageSubCategory.setText(subCat.getImageSubC());
        }
        btnSaveOrUpdate.setText(id != null ? "Update" : "Insert");
    }
    public void save(View view) {
        SubCategoriesDAO dao = new SubCategoriesDAO(this);
        SubCategory subCat = new SubCategory(
                etNameSubCategory.getText().toString(),
                etTypeSubCategory.getText().toString(),
                Double.valueOf(etPriceSubCategory.getText().toString()),
                etImageSubCategory.getText().toString()
        );

        //if (_id!=null) call update instead.
        if (id != null) {
            dao.update(id, subCat);
        } else {
            dao.insert(subCat);
        }

        Intent mainIntent = new Intent(this, SubCategoriesRecyclerActivity.class);
        startActivity(mainIntent);

        //finish();
    }
}