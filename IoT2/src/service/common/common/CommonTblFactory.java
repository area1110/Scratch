package service.common.common;

import blueprint.factory.ITableFactory;
import blueprint.table.ITableService;

public class CommonTblFactory implements ITableFactory {

    @Override
    public ITableService createTable(String tableName) {

        if ("ci_asset".equals(tableName)) {
            return new CommonTblCiAsset();
        } else if ("ci_asset_network".equals(tableName)) {
            return new CommonTblCiAssetNetwork();
        }
        System.out.println("Unsuported Ci Table");
        return null;
    }

}
