package echang.pxd.a19qqlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PXDListView extends ListView {
    public PXDListView(Context context) {
        super(context);
        initData();
    }

    public PXDListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData(){
        //设置适配器
        setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return DataManager.sharedManager
                    .getDataSource()
                    .size();
        }

        @Override
        public Object getItem(int position) {
            return DataManager.sharedManager
                    .getDataSource()
                    .get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //获取这个item对应的数据模型
            FriendModel model = DataManager.sharedManager
                    .getDataSource()
                    .get(position);

            //创建管理Item的对象
            FriendItem item = new FriendItem(
                    getContext(),
                    model);

            return item.rootView;
        }
    }
}
