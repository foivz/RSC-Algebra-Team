package com.rsc.idonor.model;

import com.facebook.model.GraphUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class User {

    @SerializedName("userId")
    private int userId;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("sex")
    private String sex;

    @SerializedName("age")
    private int age;

    @SerializedName("weightKg")
    private float weight;

    @SerializedName("bloodTypeId")
    private int bloodType;

    @SerializedName("city")
    private String town;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("postal")
    private String postal;

    @SerializedName("timeSinceLastDonationSec")
    private int timeSinceLastDonationSec;

    @SerializedName("bloodDonations")
    List<BloodDonations> bloodDonations;

    public User() {

    }

    public User(GraphUser user) {
        this.setUsername(String.valueOf(user.getId()));
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getBloodType() {
        return bloodType;
    }

    public void setBloodType(int bloodType) {
        this.bloodType = bloodType;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public int getTimeSinceLastDonationSec() {
        return timeSinceLastDonationSec;
    }

    public void setTimeSinceLastDonationSec(int timeSinceLastDonationSec) {
        this.timeSinceLastDonationSec = timeSinceLastDonationSec;
    }

    public List<BloodDonations> getBloodDonations() {
        return bloodDonations;
    }

    public void setBloodDonations(List<BloodDonations> bloodDonations) {
        this.bloodDonations = bloodDonations;
    }
}
