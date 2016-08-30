package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import karelle.env.royal.R;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.models.Category;

public class CategoriesActivity extends AppCompatActivity {
    EditText etNameCategory, etTypeCategory, etPriceCategory, etImageCategory;
    Button btnSaveOrUpdate;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        etNameCategory=(EditText)findViewById(R.id.etNameCategory);
        etTypeCategory=(EditText)findViewById(R.id.etTypeCategory);
        etPriceCategory=(EditText)findViewById(R.id.etPriceCategory);
        etImageCategory=(EditText)findViewById(R.id.etImageCategory);

        btnSaveOrUpdate=(Button)findViewById(R.id.btnSaveOrUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");
        if (id != null) {
            //init the dao:
            CategoriesDAO catDao = new CategoriesDAO(this);
            //get the category from the dao by id
            Category cat = catDao.query(id);

            //fill the edittext using the song:
            etNameCategory.setText(cat.getNameCat());
            etTypeCategory.setText(cat.getTypeCat());
            etPriceCategory.setText(String.valueOf(cat.getPriceCat()));
            etImageCategory.setText(cat.getImageCat());
        }
        btnSaveOrUpdate.setText(id != null ? "Update" : "Insert");
    }
    public void save(View view) {
        CategoriesDAO dao = new CategoriesDAO(this);

        Category cat = new Category(
                etNameCategory.getText().toString(),
                etTypeCategory.getText().toString(),
                Double.valueOf(etPriceCategory.getText().toString()),
                etImageCategory.getText().toString()
        );


        //if (_id!=null) call update instead.
        if (id != null) {
            dao.update(id, cat);
        } else {
            dao.insert(cat);
        }

        Intent mainIntent = new Intent(this, CategoriesRecyclerActivity.class);
        startActivity(mainIntent);

        //finish();
    }
}
