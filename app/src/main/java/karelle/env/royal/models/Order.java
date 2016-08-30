package karelle.env.royal.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Parcelable {
    private String ido;
    private String idCust;
    private Double priceOrder;
    private String dateOrder;
    private String statutOrder;
    private String modeOrder;
    private String placeDelivery;
    private String addDelivery;
    private String dateDelivery;
    private ArrayList<OrderDetail> od;


    public Order() {
    }



    public Order(String ido, String idCust, Double priceOrder, String dateOrder, String statutOrder) {
        this.ido = ido;
        this.idCust = idCust;
        this.priceOrder = priceOrder;
        this.dateOrder = dateOrder;
        this.statutOrder = statutOrder;
    }
    public Order( String idCust, Double priceOrder, String dateOrder, String statutOrder, String modeOrder, String placeDelivery, String addDelivery, String dateDelivery) {

        this.idCust = idCust;
        this.priceOrder = priceOrder;
        this.dateOrder = dateOrder;
        this.statutOrder = statutOrder;
        this.modeOrder = modeOrder;
        this.placeDelivery = placeDelivery;
        this.addDelivery = addDelivery;
        this.dateDelivery = dateDelivery;
    }


    public Order(String ido, String idCust, Double priceOrder, String dateOrder, String statutOrder, String modeOrder, String placeDelivery, String addDelivery, String dateDelivery) {
        this.ido = ido;
        this.idCust = idCust;
        this.priceOrder = priceOrder;
        this.dateOrder = dateOrder;
        this.statutOrder = statutOrder;
        this.modeOrder = modeOrder;
        this.placeDelivery = placeDelivery;
        this.addDelivery = addDelivery;
        this.dateDelivery = dateDelivery;
    }

    public Order(String ido, Double priceOrder) {
        this.ido = ido;
        this.priceOrder = priceOrder;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public String getIdCust() {
        return idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public Double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(Double priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStatutOrder() {
        return statutOrder;
    }

    public void setStatutOrder(String statutOrder) {
        this.statutOrder = statutOrder;
    }

    public String getModeOrder() {
        return modeOrder;
    }

    public void setModeOrder(String modeOrder) {
        this.modeOrder = modeOrder;
    }

    public String getPlaceDelivery() {
        return placeDelivery;
    }

    public void setPlaceDelivery(String placeDelivery) {
        this.placeDelivery = placeDelivery;
    }

    public String getAddDelivery() {
        return addDelivery;
    }

    public void setAddDelivery(String addDelivery) {
        this.addDelivery = addDelivery;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public ArrayList<OrderDetail> getOd() {
        return od;
    }

    public void setOd(ArrayList<OrderDetail> od) {
        this.od = od;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ido);
        dest.writeString(this.idCust);
        dest.writeValue(this.priceOrder);
        dest.writeString(this.dateOrder);
        dest.writeString(this.statutOrder);
        dest.writeString(this.modeOrder);
        dest.writeString(this.placeDelivery);
        dest.writeString(this.addDelivery);
        dest.writeString(this.dateDelivery);
        dest.writeTypedList(this.od);
    }

    protected Order(Parcel in) {
        this.ido = in.readString();
        this.idCust = in.readString();
        this.priceOrder = (Double) in.readValue(Double.class.getClassLoader());
        this.dateOrder = in.readString();
        this.statutOrder = in.readString();
        this.modeOrder = in.readString();
        this.placeDelivery = in.readString();
        this.addDelivery = in.readString();
        this.dateDelivery = in.readString();
        this.od = in.createTypedArrayList(OrderDetail.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public String toString() {
        return "Order{" +
                "ido='" + ido + '\'' +
                ", idCust='" + idCust + '\'' +
                ", priceOrder=" + priceOrder +
                ", dateOrder='" + dateOrder + '\'' +
                ", statutOrder='" + statutOrder + '\'' +
                ", modeOrder='" + modeOrder + '\'' +
                ", placeDelivery='" + placeDelivery + '\'' +
                ", addDelivery='" + addDelivery + '\'' +
                ", dateDelivery='" + dateDelivery + '\'' +
                ", od=" + od +
                '}';
    }
}
