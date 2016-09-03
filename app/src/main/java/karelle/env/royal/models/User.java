package karelle.env.royal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salomé on 20/08/2016.
 */
public class User implements Parcelable {
    private String userId;
    private String name;
    private String tel;
    private String email;
    private String addHome;

    public User() {
    }

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public User(String userId, String email, String addHome) {
        this.userId = userId;
        this.email = email;
        this.addHome = addHome;
    }

    public User(String userId, String name, String tel, String email, String addHome) {
        this.userId = userId;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.addHome = addHome;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddHome() {
        return addHome;
    }

    public void setAddHome(String addHome) {
        this.addHome = addHome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeString(this.email);
        dest.writeString(this.addHome);
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.name = in.readString();
        this.tel = in.readString();
        this.email = in.readString();
        this.addHome = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", addHome='" + addHome + '\'' +
                '}';
    }
}
