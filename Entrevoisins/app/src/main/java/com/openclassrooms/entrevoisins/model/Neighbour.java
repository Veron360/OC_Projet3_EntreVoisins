package com.openclassrooms.entrevoisins.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Serializable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Adresse */
    private String adresse;

    /** User Phone */
    private String phone;

    /** Facebook */
    private String facebook;

    /** Message */
    private String message;

    /** Favoris */
    private boolean favorite;


    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param adresse
     * @param phone
     * @param facebook
     * @param message
     */
    public Neighbour(Integer id, String name, String avatarUrl, String adresse, String phone, String facebook, String message) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.adresse = adresse;
        this.phone = phone;
        this.facebook = facebook;
        this.message = message;
        this.favorite = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
