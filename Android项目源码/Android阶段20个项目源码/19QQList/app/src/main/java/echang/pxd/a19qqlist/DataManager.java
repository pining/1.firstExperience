package echang.pxd.a19qqlist;

import java.util.ArrayList;

/**
 * 管理数据
 * 存储解析之后的数据
 * 加载数据
 * 保存数据
 * 更改数据
 *
 * 单例模式
 * 1.禁用构造方法
 * 2.定义单例对象 静态 final
 */
public class DataManager {
    //保存所有数据
    private ArrayList<FriendModel> dataSource;

    //单例对象
    public static final DataManager sharedManager =
            new DataManager();

    //禁用构造方法
    private DataManager(){
        loadData();
    }

    //加载数据
    private void loadData(){
        //用自己创建假数据的方式 记载所有数据
        dataSource = DataUtils.loadCustomData();
    }

    public ArrayList<FriendModel> getDataSource() {
        return dataSource;
    }
}












