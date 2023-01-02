package com.nk.tax;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculator {


    public static void main(String[] args) {
        System.out.println(getTax(539000));
    }

    protected static double getTax(double income) {
        List<double[]> taxes = new ArrayList<>();
        double tax = 0d;
        double orgIncome = income;
        income -= getStdDeduction();
        int i = 0;
        List<double[]> taxRates = getTaxRates();
        double diff = 0;
        while(income > 0 && i++ < taxRates.size()) {
            double rate = taxRates.get(i-1)[1];
            double slab = taxRates.get(i)[0]-taxRates.get(i-1)[0];
            diff = Math.min(income, slab);
            double temp = (diff * (rate/100));
            tax += temp;
            taxes.add(new double[]{temp, rate, diff});
            income -= diff;
        }
        i--;
        if(income > 0) {
            double temp = income * (taxRates.get(i)[1]/100);
            tax += temp;
            taxes.add(new double[]{temp, taxRates.get(i)[1], income});
        }

        System.out.println("Total Tax: " + tax);
        System.out.println("Marginal Tax: " + taxRates.get(i)[1]);
        System.out.println("Average Tax: " + (tax/orgIncome)*100);
        System.out.println("Income in " + taxRates.get(i)[1] + " Bracket: " + diff);
        if(i > 0)
            System.out.println("Can save " + diff * (taxRates.get(i)[1]/100) + " if " + diff + " is invested to make Marginal Tax as: " + taxRates.get(i-1)[1]);

        for(double[] t : taxes) {
            System.out.println("Tax at " + t[1] + "% for " + t[2] + " is = " + t[0]);
        }



        return tax;
    }

    protected static List<double[]> getTaxRates() {
        List<double[]> rates = new ArrayList<>();
        rates.add(new double[]{0, 10});
        rates.add(new double[]{20550, 12});
        rates.add(new double[]{83550, 22});
        rates.add(new double[]{178150, 24});
        rates.add(new double[]{340100, 32});
        rates.add(new double[]{431900, 35});
        rates.add(new double[]{647850, 37});

//        List<double[]> rates = new ArrayList<>();
//        rates.add(new double[]{0, 10});
//        rates.add(new double[]{19900, 12});
//        rates.add(new double[]{81050, 22});
//        rates.add(new double[]{172750, 24});
//        rates.add(new double[]{329850, 32});
//        rates.add(new double[]{418850, 35});
//        rates.add(new double[]{628300, 37});

//        List<double[]> rates = new ArrayList<>();
//        rates.add(new double[]{0, 10});
//        rates.add(new double[]{19750, 12});
//        rates.add(new double[]{80250, 22});
//        rates.add(new double[]{171050, 24});
//        rates.add(new double[]{326600, 32});
//        rates.add(new double[]{414700, 35});
//        rates.add(new double[]{622050, 37});

        return rates;
    }

    protected static int getStdDeduction() {
        return 25900;
//        return 24800;
    }
}
