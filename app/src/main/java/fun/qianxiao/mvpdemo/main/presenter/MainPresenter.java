package fun.qianxiao.mvpdemo.main.presenter;

import android.content.Context;

import fun.qianxiao.mvpdemo.base.BasePresenter;
import fun.qianxiao.mvpdemo.main.model.IMainModel;
import fun.qianxiao.mvpdemo.main.model.MainModel;
import fun.qianxiao.mvpdemo.main.view.IMainView;

/**
 * Create by QianXiao
 * On 2020/4/15
 */
public class MainPresenter
        extends BasePresenter<IMainView, IMainModel>
        implements IMainPresenter, IMainModel.RefreshCallback {
    public MainPresenter(Context context) {
        super(context);
        mModel = new MainModel(context);
    }

    @Override
    public void OnRefresh() {
        mView.OpenLoadingDialog();
        //将实现的回调接口（IMainModel.RefreshCallback）实现并传入
        mModel.OnRefreshData(this);
    }

    @Override
    public void RefreshSuccess(String data) {
        mView.CloseRefresh();
        mView.CloseLoadingDialog();
        mView.RefreshDate(data);

    }

    @Override
    public void RefreshError(String e) {
        mView.CloseRefresh();
        mView.CloseLoadingDialog();
        mView.Toast(e);
    }
}
