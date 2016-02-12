package com.devteam.sistrans.models;

public class ReportParamsRequest {
    private String datePrev;
    private String dateNext;
    private String adqFilters; //Adquiriente
    private String canalFilters; //Canal
    private String autFilters; //Autorizador

    public String getDatePrev() {
        return datePrev;
    }

    public void setDatePrev(String datePrev) {
        this.datePrev = datePrev;
    }

    public String getDateNext() {
        return dateNext;
    }

    public void setDateNext(String dateNext) {
        this.dateNext = dateNext;
    }

    public String getAdqFilters() {
        return adqFilters;
    }

    public void setAdqFilters(String adqFilters) {
        this.adqFilters = adqFilters;
    }

    public String getCanalFilters() {
        return canalFilters;
    }

    public void setCanalFilters(String canalFilters) {
        this.canalFilters = canalFilters;
    }

    public String getAutFilters() {
        return autFilters;
    }

    public void setAutFilters(String autFilters) {
        this.autFilters = autFilters;
    }
}
