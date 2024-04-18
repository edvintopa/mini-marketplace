package com.example.minimarketplace.model.product.products.vehicle;

import com.example.minimarketplace.model.product.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-09
 */
public class Vehicle extends Product {

    //Vehicle attributes
    private VehicleType type;
    private VehicleDrivetrain drivetrain;
    private VehicleFuel fuel;
    private VehicleTransmission transmission;
    private double milage;
    private int numberOfOwners;

    //getters

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public VehicleDrivetrain getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(VehicleDrivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public VehicleFuel getFuel() {
        return fuel;
    }

    public void setFuel(VehicleFuel fuel) {
        this.fuel = fuel;
    }

    public VehicleTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(VehicleTransmission transmission) {
        this.transmission = transmission;
    }

    public double getMilage() {
        return milage;
    }

    public void setMilage(double milage) {
        this.milage = milage;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }
}
