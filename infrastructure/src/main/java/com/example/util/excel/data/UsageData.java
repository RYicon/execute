package com.example.util.excel.data;

import lombok.Data;

@Data
public class UsageData {
    private String Id;
    private String product_id;
    private String resource_id;
    private String option_id;
    private String IsReservation;
    private String ExchangeProof;
    private String IsActive;
    private String OtherExplanationOne;
    private String OtherExplanationTwo;
    private String UsageTimeEqualticketExchangeTime;
    private String ReservationTime;
    private String ReservationPhone;
    private String ReservationIntro;


}
