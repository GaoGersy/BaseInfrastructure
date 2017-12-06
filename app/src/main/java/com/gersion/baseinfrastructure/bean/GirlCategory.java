package com.gersion.baseinfrastructure.bean;

public class GirlCategory {
    private Integer categoryId;

    private String title;

    private String icon;

    private String description;

    private Integer girlType;

    private Integer viewNumber;

    private Integer praiseNumber;

    public GirlCategory(Integer categoryId, String title, String icon, String description, Integer girlType, Integer viewNumber, Integer praiseNumber) {
        this.categoryId = categoryId;
        this.title = title;
        this.icon = icon;
        this.description = description;
        this.girlType = girlType;
        this.viewNumber = viewNumber;
        this.praiseNumber = praiseNumber;
    }

    public GirlCategory() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getGirlType() {
        return girlType;
    }

    public void setGirlType(Integer girlType) {
        this.girlType = girlType;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Integer praiseNumber) {
        this.praiseNumber = praiseNumber;
    }
}