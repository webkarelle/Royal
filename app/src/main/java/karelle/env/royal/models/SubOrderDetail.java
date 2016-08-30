package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salomé on 13/08/2016.
 */
public class SubOrderDetail implements Parcelable {
    private String idSOD;
    private String nameSOD;
    private String typeSOD;
    private Double priceSOD;
    private String idOD;


    public SubOrderDetail() {

    }

    public SubOrderDetail(String idSOD, String nameSOD, String typeSOD, Double priceSOD, String idOD) {
        this.idSOD = idSOD;
        this.nameSOD = nameSOD;
        this.typeSOD = typeSOD;
        this.priceSOD = priceSOD;
        this.idOD = idOD;
    }

    public SubOrderDetail(String nameSOD, String typeSOD, Double priceSOD, String idOD) {
        this.nameSOD = nameSOD;
        this.typeSOD = typeSOD;
        this.priceSOD = priceSOD;
        this.idOD = idOD;
    }

    public String getIdSOD() {
        return idSOD;
    }

    public void setIdSOD(String idSOD) {
        this.idSOD = idSOD;
    }

    public String getNameSOD() {
        return nameSOD;
    }

    public void setNameSOD(String nameSOD) {
        this.nameSOD = nameSOD;
    }

    public String getTypeSOD() {
        return typeSOD;
    }

    public void setTypeSOD(String typeSOD) {
        this.typeSOD = typeSOD;
    }

    public Double getPriceSOD() {
        return priceSOD;
    }

    public void setPriceSOD(Double priceSOD) {
        this.priceSOD = priceSOD;
    }

    public String getIdOD() {
        return idOD;
    }

    public void setIdOD(String idOD) {
        this.idOD = idOD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idSOD);
        dest.writeString(this.nameSOD);
        dest.writeString(this.typeSOD);
        dest.writeValue(this.priceSOD);
        dest.writeString(this.idOD);
    }

    protected SubOrderDetail(Parcel in) {
        this.idSOD = in.readString();
        this.nameSOD = in.readString();
        this.typeSOD = in.readString();
        this.priceSOD = (Double) in.readValue(Double.class.getClassLoader());
        this.idOD = in.readString();
    }

    public static final Creator<SubOrderDetail> CREATOR = new Creator<SubOrderDetail>() {
        @Override
        public SubOrderDetail createFromParcel(Parcel source) {
            return new SubOrderDetail(source);
        }

        @Override
        public SubOrderDetail[] newArray(int size) {
            return new SubOrderDetail[size];
        }
    };

    @Override
    public String toString() {
        return "SubOrderDetail{" +
                "idSOD='" + idSOD + '\'' +
                ", nameSOD='" + nameSOD + '\'' +
                ", typeSOD='" + typeSOD + '\'' +
                ", priceSOD=" + priceSOD +
                ", idOD='" + idOD + '\'' +
                '}';
    }
}