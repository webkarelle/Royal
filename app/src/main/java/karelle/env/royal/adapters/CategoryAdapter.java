package karelle.env.royal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import karelle.env.royal.R;
import karelle.env.royal.db.CategoriesDAO;
import karelle.env.royal.models.Category;


public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final LayoutInflater inflater;
    private static ArrayList<Category> categories;
    private Context context;
    private static CategoriesDAO catDao;

    public CategoryAdapter(Context context) {
        this.context = context;
        requery();
        inflater = LayoutInflater.from(context);
    }

    public void requery() {
        catDao = new CategoriesDAO(context);
        categories = catDao.query();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final Category cat = categories.get(position);

        holder.tvNameCategory.setText(cat.getNameCat());
        holder.tvTypeCategory.setText(cat.getTypeCat());
        holder.tvPriceCategory.setText(String.valueOf(cat.getPriceCat()));
       // holder.tvImageCategory.setText(cat.getImageCat());
        holder._ID = cat.getIdCat();

        holder.ivDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteByHolder(holder);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoriesActivity.class);
                intent.putExtra("_ID", cat.getIdCat());
                context.startActivity(intent);
            }
        });
    }

    public void deleteByHolder(CategoryViewHolder holder){
        //Search the categories in the arraylist
        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            //if found:
            if (cat.getIdCat().equals(holder._ID)){
                //remove the song from the arrayList
                categories.remove(cat);
                //remove the song from the dao
                catDao.delete(cat.getIdCat());
                //notify the adapter
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        String _ID;
        TextView tvNameCategory;
        TextView tvTypeCategory;
        TextView tvPriceCategory;
        TextView tvImageCategory;
        ImageView ivDeleteCategory;
        //ImageView ivArt;
        RelativeLayout layout;


        public CategoryViewHolder(View v) {
            super(v);

            tvNameCategory = (TextView) v.findViewById(R.id.tvNameCategory);
            tvTypeCategory = (TextView) v.findViewById(R.id.tvTypeCategory);
            tvPriceCategory = (TextView) v.findViewById(R.id.tvPriceCategory);

           // ivArt = (ImageView) v.findViewById(R.id.imageView);
            layout = (RelativeLayout) v.findViewById(R.id.layoutCategory);
            ivDeleteCategory = (ImageView) v.findViewById(R.id.ivDeleteCategory);
        }
    }
}
