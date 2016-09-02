package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;


public class SubC implements Parcelable {

    private String nameSubC;
    private String typeSubC;
    private String priceSubC;


    public SubC() {
    }


    public SubC( String nameSubC, String typeSubC,String priceSubC) {
        this.nameSubC = nameSubC;
        this.typeSubC = typeSubC;
        this.priceSubC = priceSubC;

    }


    public String getNameSubC() {
        return nameSubC;
    }

    public void setNameSubC(String nameSubC) {
        this.nameSubC = nameSubC;
    }

    public String getTypeSubC() {
        return typeSubC;
    }

    public void setTypeSubC(String typeSubC) {
        this.typeSubC = typeSubC;
    }

    public String getPriceSubC() {
        return priceSubC;
    }

    public void setPriceSubC(String priceSubC) {
        this.priceSubC = priceSubC;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nameSubC);
        dest.writeString(this.typeSubC);
        dest.writeString(this.priceSubC);

    }

    protected SubC(Parcel in) {

        this.nameSubC = in.readString();
        this.typeSubC = in.readString();
        this.priceSubC = in.readString();

    }

    public static final Parcelable.Creator<SubC> CREATOR = new Parcelable.Creator<SubC>() {
        @Override
        public SubC createFromParcel(Parcel source) {
            return new SubC(source);
        }

        @Override
        public SubC[] newArray(int size) {
            return new SubC[size];
        }
    };

    @Override
    public String toString() {
        return "SubC{" +

                "nameSubC='" + nameSubC + '\'' +
                ", typeSubC='" + typeSubC + '\'' +
                ", priceSubC=" + priceSubC +
                '}';
    }
}
