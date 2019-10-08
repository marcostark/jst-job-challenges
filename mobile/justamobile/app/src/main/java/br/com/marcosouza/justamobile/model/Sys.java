package br.com.marcosouza.justamobile.model;

import com.google.gson.annotations.SerializedName;

class Sys {

    @SerializedName("country")
    public String country;

    @SerializedName("sunrise")
    public long sunrise;

    @SerializedName("sunset")
    public long sunset;
}
