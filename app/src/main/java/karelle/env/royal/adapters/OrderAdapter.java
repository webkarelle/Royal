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
import android.widget.Toast;

import java.util.ArrayList;

import karelle.env.royal.R;

import karelle.env.royal.controlers.OrdersActivity;

import karelle.env.royal.db.OrdersDAO;

import karelle.env.royal.models.Order;



public class OrderAdapter  extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final LayoutInflater inflater;
    private static ArrayList<Order> orders;
    private Context context;
    private static OrdersDAO orderDAO;

    public OrderAdapter(Context context) {
        this.context = context;
        requery();
        inflater = LayoutInflater.from(context);
    }

    public void requery() {
        orderDAO = new OrdersDAO(context);
        orders = orderDAO.query();
        Toast.makeText(context, "tzaille orders"+orders.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        final Order o = orders.get(position);
        //holder.tvIdOrder.setText(o.getIdo());
        holder.tvIdCust.setText(o.getIdCust());
        holder.tvDateOrder.setText(o.getDateOrder());
        holder.tvPriceOrder.setText(String.valueOf(o.getPriceOrder()));
        holder.tvStatutOrder.setText(o.getStatutOrder());
        // holder.tvImageCategory.setText(cat.getImageCat());
        holder._ID = o.getIdo();

        holder.ivDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteByHolder(holder);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrdersActivity.class);
                intent.putExtra("_ID", o.getIdo());
                context.startActivity(intent);
            }
        });
    }

    public void deleteByHolder(OrderViewHolder holder){
        //Search the orders in the arraylist
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            //if found:
            if (o.getIdo().equals(holder._ID)){
                //remove the song from the arrayList
                orders.remove(o);
                //remove the song from the dao
                orderDAO.delete(o.getIdo());
                //notify the adapter
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        String _ID;
        TextView tvIdOrder;
        TextView tvIdCust;
        TextView tvPriceOrder;
        TextView tvDateOrder;
        TextView tvStatutOrder;
        ImageView ivDeleteOrder;
        //ImageView ivArt;
        RelativeLayout layout;


        public OrderViewHolder(View v) {
            super(v);

           tvIdOrder = (TextView) v.findViewById(R.id.tvIdOrder);
            tvIdCust = (TextView) v.findViewById(R.id.tvIdCust);
            tvPriceOrder = (TextView) v.findViewById(R.id.tvPriceOrder);
            tvDateOrder = (TextView) v.findViewById(R.id.tvDateOrder);
            tvStatutOrder = (TextView) v.findViewById(R.id.tvStatutOrder);

            // ivArt = (ImageView) v.findViewById(R.id.imageView);
            layout = (RelativeLayout) v.findViewById(R.id.layoutOrder);
            ivDeleteOrder = (ImageView) v.findViewById(R.id.ivDeleteOrder);
        }
    }
}