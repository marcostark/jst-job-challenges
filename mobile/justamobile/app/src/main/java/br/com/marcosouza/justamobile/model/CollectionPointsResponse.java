package br.com.marcosouza.justamobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionPointsResponse {
    @SerializedName("atual_page")
    @Expose
    private Integer atualPage;
    @SerializedName("num_pages")
    @Expose
    private Integer numPages;
    @SerializedName("num_itens")
    @Expose
    private Integer numItens;
    @SerializedName("has_previous")
    @Expose
    private Boolean hasPrevious;
    @SerializedName("has_next")
    @Expose
    private Boolean hasNext;
    @SerializedName("results")
    @Expose
    private List<CollectionPoints> results = null;

    public Integer getAtualPage() {
        return atualPage;
    }

    public void setAtualPage(Integer atualPage) {
        this.atualPage = atualPage;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public Integer getNumItens() {
        return numItens;
    }

    public void setNumItens(Integer numItens) {
        this.numItens = numItens;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<CollectionPoints> getResults() {
        return results;
    }

    public void setResults(List<CollectionPoints> results) {
        this.results = results;
    }
}
