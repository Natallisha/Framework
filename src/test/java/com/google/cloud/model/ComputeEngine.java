package com.google.cloud.model;

public class ComputeEngine {

    private String numberOfInstance;
    private String operationSystem;
    private String vmClass;
    private String series;
    private String type;
    private String numberOfGPUs;
    private String gPUsType;
    private String valueSSD;
    private String location;
    private String commitmentTerm;

    public ComputeEngine(String numberOfInstance, String operationSystem, String vmClass, String series,
                         String type, String numberOfGPUs, String gPUsType,
                         String valueSSD, String location, String commitmentTerm) {
        this.numberOfInstance = numberOfInstance;
        this.operationSystem = operationSystem;
        this.vmClass = vmClass;
        this.series = series;
        this.type = type;
        this.numberOfGPUs = numberOfGPUs;
        this.gPUsType = gPUsType;
        this.valueSSD = valueSSD;
        this.location = location;
        this.commitmentTerm = commitmentTerm;
    }

    public String getNumberOfInstance() {
        return numberOfInstance;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public String getVmClass() {
        return vmClass;
    }

    public String getSeries() {
        return series;
    }

    public String getType() {
        return type;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getGPUsType() {
        return gPUsType;
    }

    public String getValueSSD() {
        return valueSSD;
    }

    public String getLocation() {
        return location;
    }

    public String getCommitmentTerm() {
        return commitmentTerm;
    }


}
