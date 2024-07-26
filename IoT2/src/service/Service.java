package service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import blueprint.factory.ISystemFactory;

public class Service {

    private DeviceFactory factory = new DeviceFactory();

    public void execute() {

        ISystemFactory systemFactory = factory.createSystemFactory("IoT2");

        
        
        
        
    }
}
