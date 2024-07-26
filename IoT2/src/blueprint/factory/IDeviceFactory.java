package blueprint.factory;

public interface IDeviceFactory {
    public abstract ISystemFactory createSystemFactory(String deviceId);
}
