package karelle.env.royal.controlers;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import karelle.env.royal.R;
import karelle.env.royal.adapters.CategoryAdapter;
import karelle.env.royal.db.CategoriesDAO;

public class CategoriesRecyclerActivity extends AppCompatActivity {
    private Button btnAddCategory;
    CategoriesDAO daoCat;
    RecyclerView recyclerView;
    @Override
    protected void onResume() {
        super.onResume();
        CategoryAdapter adapter = (CategoryAdapter) recyclerView.getAdapter();
        adapter.requery();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_recycler);
        daoCat = new CategoriesDAO(this);
        btnAddCategory = (Button)findViewById(R.id.btnAddCategory) ;
        recyclerView = (RecyclerView) findViewById(R.id.categoriesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);

        recyclerView.setAdapter(categoryAdapter);
    }


    public void gotodetailsCategory(View view) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

}