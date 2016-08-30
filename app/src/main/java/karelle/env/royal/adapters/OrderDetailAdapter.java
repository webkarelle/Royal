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

import karelle.env.royal.controlers.OrderDetailsActivity;

import karelle.env.royal.db.OrderDetailsDAO;

import karelle.env.royal.models.OrderDetail;


public class OrderDetailAdapter extends  RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {


    private final LayoutInflater inflater;
    private static ArrayList<OrderDetail> orderDetails;
    private Context context;
    private static OrderDetailsDAO odDao;

    public OrderDetailAdapter(Context context) {
        this.context = context;
        requery();
        inflater = LayoutInflater.from(context);
    }

    public void requery() {
        odDao = new OrderDetailsDAO(context);
        orderDetails = odDao.query();
    }

    @Override
    public OrderDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.orderdetail, parent, false);
        return new OrderDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OrderDetailViewHolder holder, int position) {
        final OrderDetail od = orderDetails.get(position);

        holder.tvIdOrder.setText(od.getIdOd());
        holder.tvTypeOD.setText(od.getTypeOd());
        holder.tvPriceOD.setText(String.valueOf(od.getPrice()));
        holder.tvSubOD.setText("oignon,olive,tomate");
        // holder.tvImageCategory.setText(cat.getImageCat());
        holder._ID = od.getIdOd();

        holder.ivDeleteOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteByHolder(holder);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("_ID", od.getIdOd());
                context.startActivity(intent);
            }
        });
    }

    public void deleteByHolder(OrderDetailViewHolder holder){
        //Search the categories in the arraylist
        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail od = orderDetails.get(i);
            //if found:
            if (od.getIdOd().equals(holder._ID)){
                //remove the song from the arrayList
                orderDetails.remove(od);
                //remove the song from the dao
                odDao.delete(od.getIdOd());
                //notify the adapter
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {

        String _ID;
        TextView tvIdOrder;
        TextView tvTypeOD;
        TextView tvPriceOD;
        TextView tvSubOD;
        ImageView ivDeleteOD;
        //ImageView ivArt;
        RelativeLayout layout;


        public OrderDetailViewHolder(View v) {
            super(v);

            tvIdOrder = (TextView) v.findViewById(R.id.tvIdOrder);
            tvTypeOD = (TextView) v.findViewById(R.id.tvTypeOD);
            tvPriceOD = (TextView) v.findViewById(R.id.tvPriceOD);
            tvSubOD = (TextView) v.findViewById(R.id.tvSuborderDetail);

            // ivArt = (ImageView) v.findViewById(R.id.imageView);
            layout = (RelativeLayout) v.findViewById(R.id.layoutOrderDetail);
            ivDeleteOD = (ImageView) v.findViewById(R.id.ivDeleteOD);
        }
    }
}