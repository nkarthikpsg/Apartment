package com.nk.apmt;

import com.nk.apmt.dto.ApartmentList;

public class ApartmentFilterHelper
{

    public static void start(String searchUrl)
    {


        ApartmentFilter apartmentFilter = new ApartmentFilter();

        ApartmentList apartmentList = apartmentFilter.start(searchUrl);

        System.out.println(apartmentList.toScreen());
    }
}
