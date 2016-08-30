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

import karelle.env.royal.controlers.SubCategoriesActivity;

import karelle.env.royal.db.SubCategoriesDAO;

import karelle.env.royal.models.SubCategory;


public class SubCategoryAdapter  extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    private final LayoutInflater inflater;
    private static ArrayList<SubCategory> subCategories;
    private Context context;
    private static SubCategoriesDAO subCatDao;

    public SubCategoryAdapter(Context context) {
        this.context = context;
        requery();
        inflater = LayoutInflater.from(context);
    }

    public void requery() {
        subCatDao = new SubCategoriesDAO(context);
        subCategories = subCatDao.query();
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.subcategory, parent, false);
        return new SubCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubCategoryViewHolder holder, int position) {
        final SubCategory subCat = subCategories.get(position);
        holder.tvNameSubCategory.setText(subCat.getNameSubC());
        holder.tvTypeSubCategory.setText(subCat.getTypeSubC());
        holder.tvPriceSubCategory.setText(String.valueOf(subCat.getPriceSubC()));
        // holder.tvImageCategory.setText(subCat.getImageSubC());
        holder._ID = subCat.getIdSubC();

        holder.ivDeleteSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteByHolder(holder);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubCategoriesActivity.class);
                intent.putExtra("_ID", subCat.getIdSubC());
                context.startActivity(intent);
            }
        });
    }

    public void deleteByHolder(SubCategoryViewHolder holder){
        //Search the categories in the arraylist
        for (int i = 0; i < subCategories.size(); i++) {
            SubCategory subCat = subCategories.get(i);
            //if found:
            if (subCat.getIdSubC().equals(holder._ID)){
                //remove the song from the arrayList
                subCategories.remove(subCat);
                //remove the song from the dao
                subCatDao.delete(subCat.getIdSubC());
                //notify the adapter
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        String _ID;
        TextView tvNameSubCategory;
        TextView tvTypeSubCategory;
        TextView tvPriceSubCategory;
        TextView tvImageSubCategory;
        ImageView ivDeleteSubCategory;
        //ImageView ivArt;
        RelativeLayout layout;


        public SubCategoryViewHolder(View v) {
            super(v);

            tvNameSubCategory = (TextView) v.findViewById(R.id.tvNameSubCategory);
            tvTypeSubCategory = (TextView) v.findViewById(R.id.tvTypeSubCategory);
            tvPriceSubCategory = (TextView) v.findViewById(R.id.tvPriceSubCategory);
            //tvImageSubCategory = (TextView) v.findViewById(R.id.tvImageSubCategory);

            // ivArt = (ImageView) v.findViewById(R.id.imageView);
            layout = (RelativeLayout) v.findViewById(R.id.layoutSubCategory);
            ivDeleteSubCategory = (ImageView) v.findViewById(R.id.ivDeleteSubCategory);
        }
    }
}