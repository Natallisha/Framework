package com.google.cloud.service;

import com.google.cloud.model.ComputeEngine;

public class EstimationCreator {

    public static final String TESTDATA_ENGINE_NUM_OF_INSTANCE = "testdata.engine.numberOfInstance";
    public static final String TESTDATA_ENGINE_OS = "testdata.engine.operationSystem";
    public static final String TESTDATA_ENGINE_VMCLASS = "testdata.engine.VMClass";
    public static final String TESTDATA_ENGINE_SERIES = "testdata.engine.series";
    public static final String TESTDATA_ENGINE_MACHINE_TYPE = "testdata.engine.machineType";
    public static final String TESTDATA_ENGINE_NUM_OF_GPU = "testdata.engine.numberOfGPU";
    public static final String TESTDATA_ENGINE_TYPE_OF_GPU = "testdata.engine.typeOFGPU";
    public static final String TESTDATA_ENGINE_LOCAL_SSD = "testdata.engine.localSSD";
    public static final String TESTDATA_ENGINE_LOCATION = "testdata.engine.dataCenterLocation";
    public static final String TESTDATA_ENGINE_COMMITMENT_PERIOD = "testdata.engine.commitmentPeriod";

    public static ComputeEngine withParametersFroMProperties() {
        return new ComputeEngine(TestDataReader.getTestData(TESTDATA_ENGINE_NUM_OF_INSTANCE),
                TestDataReader.getTestData(TESTDATA_ENGINE_OS),
                TestDataReader.getTestData(TESTDATA_ENGINE_VMCLASS),
                TestDataReader.getTestData(TESTDATA_ENGINE_SERIES),
                TestDataReader.getTestData(TESTDATA_ENGINE_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_ENGINE_NUM_OF_GPU),
                TestDataReader.getTestData(TESTDATA_ENGINE_TYPE_OF_GPU),
                TestDataReader.getTestData(TESTDATA_ENGINE_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_ENGINE_LOCATION),
                TestDataReader.getTestData(TESTDATA_ENGINE_COMMITMENT_PERIOD));
    }
}
