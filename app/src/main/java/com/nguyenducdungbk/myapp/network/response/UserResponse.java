package com.nguyenducdungbk.myapp.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserResponse extends RealmObject implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    private int idRealm = 1;
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;

    private String token;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UserResponse() {
    }

    protected UserResponse(Parcel in) {
        idRealm = in.readInt();
        code = in.readString();
        name = in.readString();
        avatar = in.readString();
        gender = in.readInt();
        birthday = in.readString();
        phone = in.readString();
        email = in.readString();
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idRealm);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeInt(gender);
        dest.writeString(birthday);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdRealm() {
        return idRealm;
    }

    public void setIdRealm(int idRealm) {
        this.idRealm = idRealm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
