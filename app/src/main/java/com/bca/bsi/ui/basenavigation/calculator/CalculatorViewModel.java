package com.bca.bsi.ui.basenavigation.calculator;

public class CalculatorViewModel {


    private Double targetHasilInvestasiDouble;
    private Double modalAwalDouble;
    private Double investasiBulananDouble;
    private Double rorDOuble;
    private int durasiBulanInt;
    private int durasiTahunInt;

    public Double getTargetHasilInvestasiDouble() {
        return targetHasilInvestasiDouble;
    }

    public Double getModalAwalDouble() {
        return modalAwalDouble;
    }

    public Double getInvestasiBulananDouble() {
        return investasiBulananDouble;
    }

    public Double getRorDOuble() {
        return rorDOuble;
    }

    public int getDurasiBulanInt() {
        return durasiBulanInt;
    }

    public int getDurasiTahunInt() {
        return durasiTahunInt;
    }

    public void setTargetHasilInvestasiDouble(String targetHasilInvestasi) {
        this.targetHasilInvestasiDouble = Double.parseDouble(targetHasilInvestasi);
    }

    public void setModalAwalDouble(String modalAwal) {
        this.modalAwalDouble = Double.parseDouble(modalAwal);
    }

    public void setInvestasiBulananDouble(String investasiBulanan) {
        this.investasiBulananDouble = Double.parseDouble(investasiBulanan);
    }

    public void setRorDOuble(String rorDOuble) {
        this.rorDOuble = Double.parseDouble(rorDOuble);
    }

    public void setDurasiBulanInt(String durasiBulan) {
        this.durasiBulanInt = Integer.parseInt(durasiBulan);
    }

    public void setDurasiTahunInt(String durasiTahun) {
        this.durasiTahunInt = Integer.parseInt(durasiTahun);
    }


}
