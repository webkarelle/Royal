package karelle.env.royal.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import karelle.env.royal.CartActivity;
import karelle.env.royal.R;
import karelle.env.royal.SubODActivity;
import karelle.env.royal.controlers.OrderDetailsActivity;
import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.OrderDetail;
import karelle.env.royal.models.SubOrderDetail;

/**
 * Created by Salomé on 15/08/2016.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

        private  ArrayList<OrderDetail> orderDetails;
        private Context context;
        private OrderDetailsDAO odDAO; //=new OrderDetailsDAO(context);
        int size = 0 ;


    public CartAdapter(Context context)
    {   this.context = context;
        odDAO = new OrderDetailsDAO(context);
        orderDetails = odDAO.query();
    }





        @Override
        public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
            return new CartViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CartViewHolder holder, int position) {
            final OrderDetail od = orderDetails.get(position);


                holder._ID = od.getIdOd();
                holder.tvNumCartDetail.setText(od.getIdOd());
                holder.tvTypeCartDetail.setText(od.getNameOd());
                holder.tvPriceCartDetail.setText(od.getPrice() + "sh");
                holder.tvSOD1.setText("++++");
                holder.ivDeleteCartOD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteByHolder(holder);
                }
            });

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SubODActivity.class);
                    intent.putExtra("_ID", od.getIdOd());
                    context.startActivity(intent);
                }
            });
        }
    public void deleteByHolder(CartViewHolder holder){
            //Search  in the arraylist
            for (int i = 0; i < orderDetails.size(); i++) {
                OrderDetail od = orderDetails.get(i);
                //if found:
                if (od.getIdOd().equals(holder._ID)){
                    //remove the song from the arrayList
                    orderDetails.remove(od);
                    //remove the song from the dao
                    odDAO.delete(od.getIdOd());
                    //notify the adapter
                    //notifyItemRemoved(i);
                    Intent j = new Intent(context, CartActivity.class);
                    context.startActivity(j);
                }
            }
        }

        @Override
        public int getItemCount() {
            return orderDetails.size();
        }

        public class CartViewHolder extends RecyclerView.ViewHolder {
            TextView tvNumCartDetail,tvTypeCartDetail,tvPriceCartDetail,tvNumCartOrder,tvSOD1;
            ImageView ivDeleteCartOD;
            Spinner spSubCartDetail;
            String _ID;
            LinearLayout layout;



            public CartViewHolder(View v) {
                super(v);
                //numIdOD
                tvNumCartDetail = (TextView) v.findViewById(R.id.tvNumCartDetail);
                //typeOD
                tvTypeCartDetail = (TextView) v.findViewById(R.id.tvTypeCartDetail);
                //priceOD
                tvPriceCartDetail = (TextView) v.findViewById(R.id.tvPriceCartDetail);
                //futur lien pour le sod en spinner
                tvSOD1 = (TextView) v.findViewById(R.id.tvSOD1);
                //le layout entier
                layout = (LinearLayout) v.findViewById(R.id.layoutCart);
                //l'imageview poubelle
                ivDeleteCartOD = (ImageView) v.findViewById(R.id.ivDeleteCartOD);

            }
        }

}
