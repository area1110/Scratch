package blueprint.factory;

import blueprint.table.ITableService;

public interface ITableFactory {
    public abstract ITableService createTable(String tableName);
}
