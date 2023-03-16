package com.example.APIwiz.Repository;

import com.example.APIwiz.Model.LastThirtyDayData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<LastThirtyDayData, Integer> {

    @Query("SELECT l.date, CASE ?1 " +
            "WHEN 'INR' THEN l.INR " +
            "WHEN 'GBP' THEN l.GBP " +
            "WHEN 'EUR' THEN l.EUR " +
            "WHEN 'JPY' THEN l.JPY " +
            "WHEN 'CHF' THEN l.CHF " +
            "WHEN 'USD' THEN l.USD " +
            "WHEN 'CAD' THEN l.CAD " +
            "WHEN 'NZD' THEN l.NZD " +
            "WHEN 'AUD' THEN l.AUD " +
            "WHEN 'NOK' THEN l.NOK " +
            "WHEN 'SEK' THEN l.SEK " +
            "ELSE null END " +
            "FROM LastThirtyDayData l")
    List<Object[]> getDateAndCurrency(String currency);

}
