package echang.pxd.a19qqlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendItem {
    //这个Item的数据模型
    private FriendModel model;

    //这个Item对应视图
    public View rootView;
    private Context context;

    //构造方法 创建的同时需要传递这个Item对应的数据模型
    public FriendItem(Context context,FriendModel model) {
        this.model = model;
        this.context = context;

        initView();
    }

    private void initView() {
        //获取对应的容器视图
        ViewGroup v = (ViewGroup) View.inflate(
                context,
                R.layout.layout_qq_friend,
                null);

        //将数据显示到这个容器对应的控件上
        //获取子视图
        ImageView icon = v.findViewWithTag(
                context.getResources().getString(R.string.iconTag));
        TextView name = v.findViewWithTag(
                context.getResources().getString(R.string.nameTag));

        //显示数据
        icon.setImageResource(model.iconID);
        name.setText(model.name);

        rootView = v;
    }
}
