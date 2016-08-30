package karelle.env.royal.controlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import karelle.env.royal.R;
import karelle.env.royal.adapters.CategoryAdapter;
import karelle.env.royal.adapters.SubCategoryAdapter;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.db.SubCategoriesDAO;

public class SubCategoriesRecyclerActivity extends AppCompatActivity {
    private Button btnAddSubCategory;
    SubCategoriesDAO daoSubCat;
    RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        SubCategoryAdapter adapter = (SubCategoryAdapter) recyclerView.getAdapter();
        adapter.requery();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories_recycler);

        daoSubCat = new SubCategoriesDAO(this);
        btnAddSubCategory = (Button)findViewById(R.id.btnAddSubCategory) ;
        recyclerView = (RecyclerView) findViewById(R.id.subCategoriesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(this);
        recyclerView.setAdapter(subCategoryAdapter);
    }


    public void gotoDetails(View view) {
        Intent intent = new Intent(this, SubCategoriesActivity.class);
        startActivity(intent);
    }

}