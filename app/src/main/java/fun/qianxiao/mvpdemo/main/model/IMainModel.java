package fun.qianxiao.mvpdemo.main.model;

/**
 * Create by QianXiao
 * On 2020/4/15
 */
public interface IMainModel {
    void OnRefreshData(RefreshCallback callback);
    interface RefreshCallback{
        void RefreshSuccess(String data);
        void RefreshError(String e);
    }
}
