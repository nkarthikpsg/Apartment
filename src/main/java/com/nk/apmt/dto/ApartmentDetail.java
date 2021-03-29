package com.nk.apmt.dto;

public class ApartmentDetail
{
    String name;

    String priceRange;

    String detailUrl;

    String imageUrl;

    Integer reviewRating;

    String eleSchoolRating = "";

    String eleSchoolName = "";

    String transitScore = "";

    String walkableScore = "";

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPriceRange()
    {
        return priceRange;
    }

    public String getPrice()
    {
        return priceRange.split("-")[0].trim();
    }

    public void setPriceRange(String priceRange)
    {
        this.priceRange = priceRange;
    }

    public String getDetailUrl()
    {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl)
    {
        this.detailUrl = detailUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public Integer getReviewRating()
    {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating)
    {
        this.reviewRating = reviewRating;
    }

    public String getEleSchoolRating()
    {
        return eleSchoolRating;
    }

    public void setEleSchoolRating(String eleSchoolRating)
    {
        this.eleSchoolRating = eleSchoolRating;
    }

    public String getEleSchoolName()
    {
        return eleSchoolName;
    }

    public void setEleSchoolName(String eleSchoolName)
    {
        this.eleSchoolName = eleSchoolName;
    }

    public String getTransitScore()
    {
        return transitScore;
    }

    public void setTransitScore(String transitScore)
    {
        this.transitScore = transitScore;
    }

    public String getWalkableScore()
    {
        return walkableScore;
    }

    public void setWalkableScore(String walkableScore)
    {
        this.walkableScore = walkableScore;
    }
}
