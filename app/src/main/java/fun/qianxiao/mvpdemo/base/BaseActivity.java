package fun.qianxiao.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Create by QianXiao
 * On 2020/4/15
 */
public abstract class BaseActivity<T extends BasePresenter<?,?>>
        extends AppCompatActivity{
    public Context context;
    /**
     * 对应的Presenter层的类对象
     */
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用抽象方法在子类中实现来绑定对应layout页面
        setContentView(getLayoutID());
        context = this;
        //使用抽象方法在子类中实现来初始化Presenter层的类对象
        mPresenter = initPresenter();
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutID();

    protected abstract T initPresenter();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    /**
     * 绑定控件
     * @param id
     * @param <E>
     * @return
     */
    public <E> E f(int id){
        return (E)findViewById(id);
    }

    //抽象父类里也可以写一些公共方法
    public void Toast(String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
