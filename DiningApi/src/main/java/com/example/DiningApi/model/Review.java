package com.example.DiningApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="displayName")
    private String displayName;

    @Column(name="restaurantId")
    private Long restaurantId;

    @Column(name="peanutScore")
    private Integer peanutScore;

    @Column(name="eggScore")
    private Integer eggScore;

    @Column(name="dairyScore")
    private Integer dairyScore;

    @Column(name="commentary")
    private String commentary;

    public enum Status {
        ACCEPTED,
        PENDING,
        REJECTED
    }

    @Column(name="status")
    private Status status;

    public Review() {
        this.displayName = null;
        this.restaurantId = null;
        this.peanutScore = 0;
        this.eggScore = 0;
        this.dairyScore = 0;
        this.commentary = null;
        this.status = null;
    }

    public Integer getPeanutScore() {
        return peanutScore;
    }

    public void setPeanutScore(Integer peanutScore) {
        this.peanutScore = peanutScore;
    }

    public Integer getEggScore() {
        return eggScore;
    }

    public void setEggScore(Integer eggScore) {
        this.eggScore = eggScore;
    }

    public Integer getDairyScore() {
        return dairyScore;
    }

    public void setDairyScore(Integer dairyScore) {
        this.dairyScore = dairyScore;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return (peanutScore + eggScore + dairyScore)/3;
    }

}