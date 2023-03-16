package com.example.APIwiz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "last_30_days")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LastThirtyDayData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private double INR;
    private double GBP;
    private double EUR;
    private double JPY;
    private double CHF;
    private double USD;
    private double CAD;
    private double NZD;
    private double AUD;
    private double NOK;
    private double SEK;



}
