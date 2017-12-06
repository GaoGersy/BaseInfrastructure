package com.gersion.baseinfrastructure.bean;

public class GirlImage {
    private Integer id;

    private String title;

    private String imageUrl;

    private Integer girlType;

    private Integer categoryId;

    public GirlImage(Integer id, String title, String imageUrl, Integer girlType, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.girlType = girlType;
        this.categoryId = categoryId;
    }

    public GirlImage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getGirlType() {
        return girlType;
    }

    public void setGirlType(Integer girlType) {
        this.girlType = girlType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}