package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JeanMarc on 03/09/2016.
 */
public class Store implements Parcelable {
    private String idStore;
    private String nameStore;
    private String addStore;

    public Store() {
    }

    public Store(String nameStore, String addStore) {
        this.nameStore = nameStore;
        this.addStore = addStore;
    }

    public Store(String idStore, String nameStore, String addStore) {
        this.idStore = idStore;
        this.nameStore = nameStore;
        this.addStore = addStore;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getAddStore() {
        return addStore;
    }

    public void setAddStore(String addStore) {
        this.addStore = addStore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idStore);
        dest.writeString(this.nameStore);
        dest.writeString(this.addStore);
    }

    protected Store(Parcel in) {
        this.idStore = in.readString();
        this.nameStore = in.readString();
        this.addStore = in.readString();
    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel source) {
            return new Store(source);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    @Override
    public String toString() {
        return "Store{" +
                "idStore='" + idStore + '\'' +
                ", nameStore='" + nameStore + '\'' +
                ", addStore='" + addStore + '\'' +
                '}';
    }
}
