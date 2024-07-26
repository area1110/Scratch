package service.common.common;

import blueprint.table.ITblCiAssetNetworkService;

public class CommonTblCiAssetNetwork implements ITblCiAssetNetworkService {

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
