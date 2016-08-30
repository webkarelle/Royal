package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SubCategory implements Parcelable {
    private String idSubC;
    private String nameSubC;
    private String typeSubC;
    private Double priceSubC;
    private String imageSubC;

    public SubCategory() {
    }

    public SubCategory(String nameSubC, String typeSubC) {
        this.nameSubC = nameSubC;
        this.typeSubC = typeSubC;
        this.priceSubC = 5.0;
        this.imageSubC = "http://google.com";
    }
    public SubCategory( String nameSubC, String typeSubC,Double priceSubC, String imageSubC) {
        this.nameSubC = nameSubC;
        this.typeSubC = typeSubC;
        this.priceSubC = priceSubC;
        this.imageSubC = imageSubC;
    }
    public SubCategory(String idSubC, String nameSubC, String typeSubC,Double priceSubC, String imageSubC) {
        this.idSubC=idSubC;
        this.nameSubC = nameSubC;
        this.typeSubC = typeSubC;
        this.priceSubC = priceSubC;
        this.imageSubC = imageSubC;
    }
    public String getIdSubC() {
        return idSubC;
    }

    public void setIdSubC(String idSubC) {
        this.idSubC = idSubC;
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

    public Double getPriceSubC() {
        return priceSubC;
    }

    public void setPriceSubC(Double priceSubC) {
        this.priceSubC = priceSubC;
    }

    public String getImageSubC() {
        return imageSubC;
    }

    public void setImageSubC(String imageSubC) {
        this.imageSubC = imageSubC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idSubC);
        dest.writeString(this.nameSubC);
        dest.writeString(this.typeSubC);
        dest.writeValue(this.priceSubC);
        dest.writeString(this.imageSubC);
    }

    protected SubCategory(Parcel in) {
        this.idSubC = in.readString();
        this.nameSubC = in.readString();
        this.typeSubC = in.readString();
        this.priceSubC = (Double) in.readValue(Double.class.getClassLoader());
        this.imageSubC = in.readString();
    }

    public static final Parcelable.Creator<SubCategory> CREATOR = new Parcelable.Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel source) {
            return new SubCategory(source);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
        }
    };

    @Override
    public String toString() {
        return "SubCategory{" +
                "idSubC='" + idSubC + '\'' +
                ", nameSubC='" + nameSubC + '\'' +
                ", typeSubC='" + typeSubC + '\'' +
                ", priceSubC=" + priceSubC +
                ", imageSubC='" + imageSubC + '\'' +
                '}';
    }
}
