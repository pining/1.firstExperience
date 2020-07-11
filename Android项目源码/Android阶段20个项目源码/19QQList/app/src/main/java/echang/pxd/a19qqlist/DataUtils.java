package echang.pxd.a19qqlist;

import java.util.ArrayList;

/**
 * 类方法：如果只关心这个方法本身
 *        工具类里面的方法基本上都是类方法
 * 对象方法：如果有数据需要对象存储
 */
public class DataUtils {
    /**
     * 自己构造数据
     */
    public static ArrayList<FriendModel> loadCustomData(){
        ArrayList<FriendModel> friends = new ArrayList<>();

        for (int i = 0; i < 30; i++){
            //创建模型对象
            FriendModel model = new FriendModel(
                    R.drawable.icon,
                    "Android实战开发");
            //保存
            friends.add(model);
        }
        return friends;
    }

    /**
     * 本地加载（文件）
     */
    public static Object loadFromFile(){
        return null;
    }

    /**
     * 数据库加载
     */
    public static Object loadFromSQL(){
        return null;
    }

    /**
     * 网络加载
     */
    public static Object loadFromServer(){
        return null;
    }

}
