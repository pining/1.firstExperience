package echang.pxd.a18listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ListView(UITableView):只能纵向滚动显示
 * 具体的显示给Adapter来配置
 * 1.创建ListView：.xml .代码创建
 * 2.配置数据 创建一个类继承于BaseAdapter
 * 3.设置ListView的适配器(数据)
 * 4.创建Layout 布局每行的具体样式，
 *   使用View.Inflate 解析layout布局文件 -> View
 * 5.响应用户的点击事件setOnItemClickListener - onItemClick
 * 6.
 * 注意：每行显示的内容通常是用layout.xml配置文件
 * 设计模式的作用：复用性 健壮性(低耦合)
 * JavaBean
 * FriendBean
 * FriendModel
 * MVC：Model View Controller
 * MVP: Model View  Present
 */
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<FriendModel> friends;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);

        friends = new ArrayList<>();

        //设置行与行之间的间距
        listView.setDividerHeight(100);
        //设置高度
        //listView.setMinimumHeight(100);
        //设置适配器
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);
        //设置监听器监听用户的行为
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //先将Item对应的视图转化为ViewGroup
                ViewGroup viewGroup = (ViewGroup) view;

                //查找容器里面的子视图
                View v = viewGroup.findViewWithTag("vTag");

                //操作这个子视图
                v.setBackgroundColor(Color.BLACK);

                //切换到下一个界面
                //调用系统的或者某个应用的方式：隐式意图
                Intent intent1 = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);

                //显示意图：直接指明跳转到哪个页面
                Intent intent = new Intent(
                        getApplicationContext(),
                        SecondActivity.class);

                //开始切换
                startActivity(intent);
                //finish(); 关闭当前界面
            }
        });

        //开启定时器 每隔1s中添加一个好友信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                initData();
                System.out.println("总共有"+friends.size()+"个");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //主线程刷新ListView
                        mAdapter.notifyDataSetChanged();

                        //如果没有全局化
                        //MyAdapter adapter = (MyAdapter) listView.getAdapter();
                        //adapter.notifyDataSetChanged();
                    }
                });
            }
        },0,1000);
    }

    //定义数据适配器
    public class MyAdapter extends BaseAdapter{
        //配置listView显示多少行
        @Override
        public int getCount() {
            return friends.size();
        }

        //数据源：数组->所有的数据 所有的好友信息
        //获取数据源里面position行对应的数据
        @Override
        public Object getItem(int position) {
            return null;
        }

        //获取position位置对应的id
        //id就是这一行对应的索引值
        @Override
        public long getItemId(int position) {
            return position;
        }

        //配置每行显示什么内容
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //创建一个文本视图
            /*
            TextView tv = new TextView(getApplicationContext());
            tv.setText("row"+position);
            tv.layout(0,0,300,100);
            */
            //将一个layout.xml文件转化为对应的视图
            //LinearLayout -> ViewGroup -> View
            View v = View.inflate(getApplicationContext(),
                    R.layout.layout_qq_friend,
                    null);

            return v;
        }
    }

    //准备数据源
    public void initData(){
        //创建一个好友
        FriendModel model = new FriendModel(
                R.drawable.ic_launcher_foreground,
                "jack");

        //将这个好友添加到数组
        friends.add(model);
    }
}









