package com.astontech.bo;

import java.util.Date;

public class Vehicle extends BaseBO implements Comparable{
    private int         VehicleId;
    private int         Year;
    private int      Vin;
    private String      Color;
    private int         LicensePlate;
    private int     isPurchase;
    private int      PurchasePrice;
    private Date      PurchaseDate;
    private int         VehicleModelId;
    private VehicleMake  Make;
    private VehicleModel Model;


    //region CONSTRUCTOR
        public Vehicle(){}

        public Vehicle(int year){
            this.setYear(year);
        }

        public Vehicle(int vehicleId, int year, int vin, String color, int isPurchase, int purchasePrice, Date purchaseDate, int vehicleModelId){
            this.setVehicleId(vehicleId);
            this.setYear(year);
            this.setVin(vin);
            this.setColor(color);
            this.isPurchase = isPurchase;
            this.setPurchasePrice(purchasePrice);
            this.setPurchaseDate(purchaseDate);
            this.setVehicleModelId(vehicleModelId);
        }
        public Vehicle(VehicleMake make, VehicleModel model){
            this.Make = new VehicleMake();
            this.Model = new VehicleModel();
        }

        public Vehicle(int vehicleId, int year){
            this.setVehicleId(vehicleId);
            this.setYear(year);
            this.Make = new VehicleMake();
            this.Model = new VehicleModel();
        }
    //endregion

    //region GETTERS/SETTERS
    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getVin() {
        return Vin;
    }

    public void setVin(int vin) {
        Vin = vin;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getisPurchase() {
        return isPurchase;
    }

    public void setisPurchase(int ispurchase) {
        isPurchase = ispurchase;
    }

    public int getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public int getVehicleModelId() {
        return VehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        VehicleModelId = vehicleModelId;
    }

    public VehicleMake getMake() {
        return Make;
    }

    public void setMake(VehicleMake make) {
        Make = make;
    }

    public VehicleModel getModel() {
        return Model;
    }

    public void setModel(VehicleModel model) {
        Model = model;
    }

    public int getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        LicensePlate = licensePlate;
    }

    //endregion

    //region CUSTOM METHOD
    @Override
    public String toString() {
        return "Vehicles: " +
                "Vehicle Id =" + VehicleId +
                ", Year = " + Year +
                ", Purchase Price =" + PurchasePrice +
                ", Vin =" + Vin +
                ", Color = " + Color +
                ", Purchase Date =" + PurchaseDate +
                ", Vehicl Model Id ='" + VehicleModelId + " ";
    }
    //endregion

    //region Compareto method
    @Override
    public int compareTo(Object o) {
        Vehicle other = (Vehicle) o;
        if(getYear() > other.getYear()){
            return 1;
        }else if(getYear() < other.getYear()){
            return - 1;
        }else
            return 0;
    }

    //endregion
}
