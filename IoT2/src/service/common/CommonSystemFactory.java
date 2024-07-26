package service.common;

import blueprint.factory.ISystemFactory;
import blueprint.factory.ITableFactory;
import service.common.common.CommonTblFactory;

public class CommonSystemFactory implements ISystemFactory{

    @Override
    public ITableFactory createTableFactory(String systemId) {
        switch (systemId) {


            
            default:
                return new CommonTblFactory();
        }
    }

}
