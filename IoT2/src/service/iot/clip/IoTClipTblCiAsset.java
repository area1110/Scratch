package service.iot.clip;

import blueprint.table.ITableService;

public class IoTClipTblCiAsset implements ITableService {

    @Override
    public void doBefore() {
        System.out.println(this.getClass().getName() + ": doBefore");
    }

    @Override
    public void doCopy() {
        System.out.println(this.getClass().getName() + ": doCopy");
    }

    @Override
    public void doAfter() {
        System.out.println(this.getClass().getName() + ": doCopy");
    }

}
