package service.iot.clip;

import blueprint.factory.ITableFactory;
import blueprint.table.ITableService;
import service.common.common.CommonTblCiAssetNetwork;

public class IoTClipTblFactory implements ITableFactory {

    @Override
    public ITableService createTable(String tableName) {
        switch (tableName) {
            case "ci_asset":
                return new IoTClipTblCiAsset();
            case "ci_asset_network":
                return new CommonTblCiAssetNetwork();         
            default:
                return null;
        }
    }

}
