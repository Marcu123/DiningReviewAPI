package com.example.DiningApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="Username")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="displayName")
    private String displayName;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private Integer zipcode;

    @Column(name="peanutInt")
    private Boolean peanutInt;

    @Column(name="eggInt")
    private Boolean eggInt;

    @Column(name="dairyInt")
    private Boolean dairyInt;

    public User() {
        this.displayName = null;
        this.city = null;
        this.state = null;
        this.zipcode = null;
        this.peanutInt = null;
        this.eggInt = null;
        this.dairyInt = null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getDairyInt() {
        return dairyInt;
    }

    public void setDairyInt(Boolean dairyInt) {
        this.dairyInt = dairyInt;
    }

    public Boolean getEggInt() {
        return eggInt;
    }

    public void setEggInt(Boolean eggInt) {
        this.eggInt = eggInt;
    }

    public Boolean getPeanutInt() {
        return peanutInt;
    }

    public void setPeanutInt(Boolean peanutInt) {
        this.peanutInt = peanutInt;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return this.id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

}