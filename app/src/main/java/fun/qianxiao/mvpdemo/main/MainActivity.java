package fun.qianxiao.mvpdemo.main;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import fun.qianxiao.mvpdemo.R;
import fun.qianxiao.mvpdemo.base.BaseActivity;
import fun.qianxiao.mvpdemo.main.presenter.MainPresenter;
import fun.qianxiao.mvpdemo.main.view.IMainView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {
    SwipeRefreshLayout srl_main;
    TextView tv_info;
    ProgressDialog progressDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        MainPresenter presenter = new MainPresenter(context);
        presenter.attach(this);
        return presenter;
    }

    @Override
    protected void initView() {
        srl_main = f(R.id.srl_main);
        tv_info = f(R.id.tv_info);
    }


    @Override
    protected void initListener() {
        srl_main.setOnRefreshListener(() -> mPresenter.OnRefresh());
    }

    @Override
    protected void initData() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中");
        mPresenter.OnRefresh();
    }

    @Override
    public void OpenLoadingDialog() {
        if(!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void CloseLoadingDialog() {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void CloseRefresh() {
        if(srl_main.isRefreshing()){
            srl_main.setRefreshing(false);
        }
    }

    @Override
    public void RefreshDate(String data) {
        tv_info.setText(data);
    }

    @Override
    public void Toast(String s) {
        super.Toast(s);
    }


}
