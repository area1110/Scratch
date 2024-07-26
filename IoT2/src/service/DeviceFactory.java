package service;

import blueprint.factory.IDeviceFactory;
import blueprint.factory.ISystemFactory;
import service.common.CommonSystemFactory;
import service.iot.IotSystemFactory;

public class DeviceFactory implements IDeviceFactory {

    @Override
    public ISystemFactory createSystemFactory(String deviceId) {
        switch (deviceId) {
            case "iot":
                return new IotSystemFactory();
            default:
                return new CommonSystemFactory();
        }
    }
}
