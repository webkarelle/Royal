package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class OrderDetail implements Parcelable {
    private String idOd;
    private String idOrder;
    private String nameOd; //glace vanille ou pizza ou penne
    private String typeOd;//pizza ou drink ou ziva ou drink
    private ArrayList<SubOrderDetail> subOrderDetails;
    private Double price;


    public OrderDetail() {
    }

    public OrderDetail(String idOd, String idOrder, String nameOd, String typeOd, ArrayList<SubOrderDetail> subOrderDetails, Double price) {
        this.idOd = idOd;
        this.idOrder = idOrder;
        this.nameOd = nameOd;
        this.typeOd = typeOd;
        this.subOrderDetails = subOrderDetails;
        this.price = price;
    }
    public OrderDetail( String idOrder, String typeOd, ArrayList<SubOrderDetail> subOrderDetails, Double price) {

        this.idOrder = idOrder;

        this.typeOd = typeOd;
        this.subOrderDetails = subOrderDetails;
        this.price = price;
    }
    public OrderDetail(String idOd, String idOrder, String nameOd, String typeOd, Double price) {
        this.idOd = idOd;
        this.idOrder = idOrder;
        this.nameOd = nameOd;
        this.typeOd = typeOd;
        this.price = price;
    }
    public OrderDetail( String idOrder,  String typeOd, Double price) {

        this.idOrder = idOrder;
        this.typeOd = typeOd;
        this.price = price;
    }
    public OrderDetail(String idOrder, String nameOd, String typeOd, Double price) {
        this.idOrder = idOrder;
        this.nameOd = nameOd;
        this.typeOd = typeOd;
        this.price = price;
    }

    public String getIdOd() {
        return idOd;
    }

    public void setIdOd(String idOd) {
        this.idOd = idOd;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameOd() {
        return nameOd;
    }

    public void setNameOd(String nameOd) {
        this.nameOd = nameOd;
    }

    public String getTypeOd() {
        return typeOd;
    }

    public void setTypeOd(String typeOd) {
        this.typeOd = typeOd;
    }

    public ArrayList<SubOrderDetail> getSubOrderDetails() {
        return subOrderDetails;
    }

    public void setSubOrderDetails(ArrayList<SubOrderDetail> subOrderDetails) {
        this.subOrderDetails = subOrderDetails;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idOd);
        dest.writeString(this.idOrder);
        dest.writeString(this.nameOd);
        dest.writeString(this.typeOd);
        dest.writeTypedList(this.subOrderDetails);
        dest.writeValue(this.price);
    }

    protected OrderDetail(Parcel in) {
        this.idOd = in.readString();
        this.idOrder = in.readString();
        this.nameOd = in.readString();
        this.typeOd = in.readString();
        this.subOrderDetails = in.createTypedArrayList(SubOrderDetail.CREATOR);
        this.price = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel source) {
            return new OrderDetail(source);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    @Override
    public String toString() {
        return "OrderDetail{" +
                "idOd='" + idOd + '\'' +
                ", idOrder='" + idOrder + '\'' +
                ", nameOd='" + nameOd + '\'' +
                ", typeOd='" + typeOd + '\'' +
                ", subOrderDetails=" + subOrderDetails +
                ", price=" + price +
                '}';
    }
}