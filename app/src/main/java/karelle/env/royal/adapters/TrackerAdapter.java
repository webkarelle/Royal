package karelle.env.royal.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import karelle.env.royal.R;
import karelle.env.royal.models.Order;
import karelle.env.royal.utils.FirebaseRecyclerAdapter;


public class TrackerAdapter extends FirebaseRecyclerAdapter<Order, TrackerAdapter.TrackerViewHolder> {

    private final DatabaseReference ref;
    boolean isEnabled = true;

    public TrackerAdapter(DatabaseReference ref) {
        super(Order.class, R.layout.tracker_item, TrackerViewHolder.class, ref);
        this.ref = ref;

    }

    @Override
    protected void populateViewHolder(final TrackerViewHolder viewHolder, final Order model, final int position) {
        //if (model.getStatutOrder()!=null )
       // {
            viewHolder.tvStatutOrder.setText(model.getStatutOrder());
        //}
       // else {
            //viewHolder.tvStatutOrder.setText("PurchaseOrder");
            //if (model.getIdo() != null) {
                viewHolder.tvNumOrder.setText(model.getIdo());
            //} else {
             //   viewHolder.tvNumOrder.setText("mumCmde 3");
            //}
        //}
    }


    public static class TrackerViewHolder extends RecyclerView.ViewHolder {
        String key;//Firebase.ViewHolder... String key
        TextView tvNumOrder;
        TextView tvStatutOrder;


        public TrackerViewHolder(View v) {
            super(v);

            tvNumOrder = (TextView) v.findViewById(R.id.tvNumOrder);
            tvStatutOrder = (TextView) v.findViewById(R.id.tvStatutOrder);


        }
    }
}