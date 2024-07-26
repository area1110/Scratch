package service.iot;

import blueprint.factory.ISystemFactory;
import blueprint.factory.ITableFactory;
import service.common.common.CommonTblFactory;
import service.iot.clip.IoTClipTblFactory;

public class IotSystemFactory implements ISystemFactory {

    @Override
    public ITableFactory createTableFactory(String systemId) {

        switch (systemId) {
            case "clip":
                return new IoTClipTblFactory();
            default:
                return new CommonTblFactory();
        }
    }

}
