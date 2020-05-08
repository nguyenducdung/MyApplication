package com.nguyenducdungbk.myapp.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserResponse extends RealmObject implements Parcelable {
    @PrimaryKey
    private int idRealm = 1;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("phone")
    private String phone;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UserResponse() {
    }

    public UserResponse(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.gender = "";
        this.dateOfBirth = "";
    }

    protected UserResponse(Parcel in) {
        name = in.readString();
        gender = in.readString();
        dateOfBirth = in.readString();
        phone = in.readString();
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(dateOfBirth);
        parcel.writeString(phone);
    }
}
