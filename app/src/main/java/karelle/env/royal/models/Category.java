package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Category implements Parcelable {
    private String idCat;
    private String nameCat;
    private String typeCat;
    private Double priceCat;
    private String imageCat;

    public Category() {
    }

    public Category(String nameCat, String typeCat) {
        this.nameCat = nameCat;
        this.typeCat = typeCat;
        this.priceCat = 30.0 ;
        this.imageCat = "http://google.com";
    }
    public Category(String nameCat, String typeCat, Double priceCat, String imageCat) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.typeCat = typeCat;
        this.priceCat = priceCat;
        this.imageCat = imageCat;
    }

    public Category(String idCat, String nameCat, String typeCat, Double priceCat, String imageCat) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.typeCat = typeCat;
        this.priceCat = priceCat;
        this.imageCat = imageCat;
    }

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public String getTypeCat() {
        return typeCat;
    }

    public void setTypeCat(String typeCat) {
        this.typeCat = typeCat;
    }

    public Double getPriceCat() {
        return priceCat;
    }

    public void setPriceCat(Double priceCat) {
        this.priceCat = priceCat;
    }

    public String getImageCat() {
        return imageCat;
    }

    public void setImageCat(String imageCat) {
        this.imageCat = imageCat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idCat);
        dest.writeString(this.nameCat);
        dest.writeString(this.typeCat);
        dest.writeValue(this.priceCat);
        dest.writeString(this.imageCat);
    }

    protected Category(Parcel in) {
        this.idCat = in.readString();
        this.nameCat = in.readString();
        this.typeCat = in.readString();
        this.priceCat = (Double) in.readValue(Double.class.getClassLoader());
        this.imageCat = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public String toString() {
        return "Category{" +
                "idCat='" + idCat + '\'' +
                ", nameCat='" + nameCat + '\'' +
                ", typeCat='" + typeCat + '\'' +
                ", priceCat=" + priceCat +
                ", imageCat='" + imageCat + '\'' +
                '}';
    }
}