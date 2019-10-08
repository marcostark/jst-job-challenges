package br.com.marcosouza.justamobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CollectionPoints {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("materials")
    @Expose
    private String materials;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;

    public CollectionPoints(String name, String thumb, String description, String materials, String lat, String lon) {
        this.name = name;
        this.thumb = thumb;
        this.description = description;
        this.materials = materials;
        this.lat = lat;
        this.lon = lon;
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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

}
