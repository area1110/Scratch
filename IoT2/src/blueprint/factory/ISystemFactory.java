package blueprint.factory;

public interface ISystemFactory {
    
    public abstract ITableFactory createTableFactory(String systemId);
}
