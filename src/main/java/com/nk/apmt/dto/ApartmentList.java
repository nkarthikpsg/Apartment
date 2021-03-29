package com.nk.apmt.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentList
{
    List<ApartmentDetail> apartmentList;

    public ApartmentList()
    {
        this.apartmentList = new ArrayList<>();
    }

    public List<ApartmentDetail> getApartmentList()
    {
        return apartmentList;
    }

    public void addApartment(ApartmentDetail apartment)
    {
        this.apartmentList.add(apartment);
    }

    public String toScreen()
    {
        List<ApartmentDetail> filteredList = apartmentList.stream()
                .filter(a -> a.getEleSchoolRating() != "" && a.getEleSchoolRating().compareTo("6") >= 0)
                .collect(Collectors.toList());
        Comparator<ApartmentDetail> comp = (a, b) -> {
            int school = a.getEleSchoolRating().compareTo(b.getEleSchoolRating());
            if (school == 0)
            {
                return a.getPriceRange().compareTo(b.getPriceRange());
            }

            return school;
        };

        Collections.sort(filteredList, comp);

        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        sb.append("***************************************************");
        sb.append("\n");

        for (ApartmentDetail detail : filteredList)
        {
            sb.append(detail.getEleSchoolRating());
            sb.append(",\t");
            sb.append(detail.getPrice());
            sb.append(",\t");
            sb.append(detail.getDetailUrl());
            sb.append("\n");
        }

        sb.append("***************************************************");

        return sb.toString();
    }
}
