package echang.pxd.a3edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener {

    //键盘被按下的回调事件
    @Override
    public boolean onEditorAction(TextView v, int actionId,
                                  KeyEvent event) {
        System.out.println("点击了");
        return false;
    }
    //1.xml配置文件
    //2.使用代码创建
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过id获取xml里对应的控件
        EditText et = findViewById(R.id.et_password);

        //监听键盘的key按下的事件
        //1.创建匿名类对象

        //1.1 创建一个类管理事件

//        PXDListener pl = new PXDListener();
//        et.setOnEditorActionListener(pl);
//
//        et.setOnEditorActionListener(new PXDListener());

        //3 匿名类对象
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,
                                          int actionId,
                                          KeyEvent event) {
                et.getText();
                return false;
            }
        });

        //4 Lambda表达式
        et.setOnEditorActionListener((textView,actionId,event) -> {
            System.out.println("点击了");
            return true;
        });


        //1.当前这个Activity来监听事件
        //et.setOnEditorActionListener(this);


        //监听EditText文本内容改变的事件
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //System.out.println("改变前:"+et.getText().toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //System.out.println("正在改变:"+et.getText().toString());
                //获取目前输入的个数
                int len = s.toString().length();

                if (len > 6){
                    //将最后一个删除掉
                    //只要前面6个
                    et.setText(s.subSequence(0,6));
                    //让光标定位到最后
                    et.setSelection(6);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //System.out.println("改变后:"+ et.getText().toString());
            }
        });
    }



}

//2.创建一个类 管理事件的回调
class PXDListener implements TextView.OnEditorActionListener{

    @Override
    public boolean onEditorAction(TextView v, int actionId,
                                  KeyEvent event) {
        System.out.println("点击了");
        return false;
    }
}