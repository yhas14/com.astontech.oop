package com.astontech.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class VehicleMake extends BaseBO{
    private int VehicleMakeId;
    private String VehicleMakeName;
    private Date CreateDate;
    private VehicleModel vehicleModel;

    //region CONSTRUCTOR
        public VehicleMake(){
            this.vehicleModel = vehicleModel;
        }

        public VehicleMake(int vehicleMakeId, String vehicleMakeName){
            this.setVehicleMakeId(vehicleMakeId);
            this.setVehicleMakeName(vehicleMakeName);
        }
    //endregion
    //region GETTERS/SETTERS
    public int getVehicleMakeId() {
        return VehicleMakeId;
    }

    public void setVehicleMakeId(int vehicleMakeId) {
        VehicleMakeId = vehicleMakeId;
    }

    public String getVehicleMakeName() {
        return VehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        VehicleMakeName = vehicleMakeName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
    //endregion

    //region CUSTOM METHOD 3 for LAB
    List<String> carList = new ArrayList<>(Arrays.asList("Acura",
            " Alfa Romeo",
            "Aston Martin",
            "Audi",
            " Bentley",
            " BMW",
            " Bugatti",
            " Buick",
            "Cadillac",
            "Caterham",
            "Chevrolet",
            "Chrysler",
            "Dodge",
            "Ferrari",
            "Fiat",
            "Ford",
            "GMC",
            "Honda",
            "Hyundai",
            "Infiniti",
            "Jaguar",
            "Jeep",
            "Kia",
            "Lamborghini",
            " Land Rover",
            "Lexus",
            "Lincoln",
            "Lotus",
            " Maserati",
            "  Mazda",
            "Mercedes Benz",
            " Mini",
            " Mitsubishi",
            "Nissan",
            "Porsche",
            "Ram Trucks",
            "Rolls Royce",
            "Smart",
            "Subaru",
            "Toyota",
            "Tesla",
            "Volkswagen",
            "Volvo"));

        public String makeExist(){
            if(carList.contains(VehicleMakeName)){
                return  VehicleMakeName + " "+ "Does exist";
            }else {
                return VehicleMakeName + "Does not exist";
            }
        }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    //endregion
}
