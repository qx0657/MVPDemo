package fun.qianxiao.mvpdemo.main.view;

/**
 * Create by QianXiao
 * On 2020/4/15
 */
public interface IMainView {

    void OpenLoadingDialog();

    void CloseLoadingDialog();

    void Toast(String s);

    void CloseRefresh();

    void RefreshDate(String data);

}
