package com.example.DiningApi.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="Restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="peanutAllergy")
    private Integer peanutAllergy;

    @Column(name="eggAllergy")
    private Integer eggAllergy;

    @Column(name="dairyAllergy")
    private Integer dairyAllergy;

    @Column(name="userAllergiesRating")
    private Integer userAllergiesRating;

    @Column(name="overallRating")
    private Integer overallRating;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipCode")
    private String zipCode;

    @Column(name="phoneNumber")
    private String phoneNumber;

    private List<Integer> usersRating;

    public Restaurant(){
        this.name = null;
        this.peanutAllergy = 0;
        this.eggAllergy = 0;
        this.dairyAllergy = 0;
        this.userAllergiesRating = 0;
        this.overallRating = 0;
        this.city = null;
        this.state = null;
        this.zipCode = null;
        this.phoneNumber = null;
        this.usersRating = new ArrayList<Integer>();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPeanutAllergy() {
        return this.peanutAllergy;
    }

    public void setPeanutAllergy(Integer peanutAllergy) {
        this.peanutAllergy = peanutAllergy;
    }

    public Integer getEggAllergy() {
        return this.eggAllergy;
    }

    public void setEggAllergy(Integer eggAllergy) {
        this.eggAllergy = eggAllergy;
    }

    public Integer getDairyAllergy() {
        return dairyAllergy;
    }

    public void setDairyAllergy(Integer dairyAllergy) {
        this.dairyAllergy = dairyAllergy;
    }

    public Integer getUserAllergiesRating() {
        return this.userAllergiesRating;
    }

    public void setUserAllergiesRating() {

        this.userAllergiesRating = (this.peanutAllergy + this.eggAllergy + this.dairyAllergy) / 3;
    }

    public Integer getOverallRating() {
        return this.overallRating;
    }

    public void setOverallRating(Integer overallRating) {
        this.overallRating = overallRating;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUsersRating(List<Integer> usersRating) {
        this.usersRating = usersRating;
    }

    public List<Integer> getUsersRating() {
        return usersRating;
    }

}
