package service.common.common;

import blueprint.table.ITblCiAssetService;

public class CommonTblCiAsset implements ITblCiAssetService {

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
