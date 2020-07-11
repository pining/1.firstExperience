package echang.pxd.tearcloth;


/**
 * Android里面打不部分的控件都会提供很多方法
 * 这些方法只需要自己去重写 不需要自己主动调用
 *
 * 什么是Activity = 就是一个界面
 * 管理一个界面从创建到运行到结束的整个过程 / 生命周期
 * 配置界面 onCreate 这个方法是系统调用的
 * 启动界面 start
 * 重新启动 restart
 * 唤醒界面 resume
 * 暂停界面 pause
 * 销毁界面 destory
 *
 * 界面启动
 *   onCreate
 *   onStart
 *   onResume
 *
 * 点击home键 回到主界面
 *   onPause
 *
 * 通过后台 重新运行这个程序
 *   onRestart
 *   onStart
 *   onResume
 *
 * 使用返回键 返回到主界面
 *   onPause
 *   onDestory
 */

/**
 * 使用两种方式界面布局
 * 1. xml配置
 * 2. 使用Java代码创建
 *
 * 默认一个Activity对应一个xml配置文件
 * 命名特点：activity_界面功能.xml
 * xml文件就是一个容器：可以放很多UI控件
 * 布局：这么多控件该如何布局
 * 1.约束布局 ConstraintLayout
 * 2.线性布局 LinearLayout 垂直 水平
 * 3.相对布局 RelativeLayout
 * 4.帧布局   FrameLayout
 * 5.表格布局 TableLayout Gridlayout
 * 6.绝对布局 AbsoluteLayout
 */

/**
 * 图案解锁
 * 图片资源 textView ImageView
 * 控件 = 一个视图 = 看到的都是视图
 * 1. 基础阶段：学习系统自带的控件 熟悉
 *    TextView EditText Button ImageView ListView
 *    RecycleView ScrollView ViewPager ProgressBar
 *    Switch
 * 2. 高级阶段：自定义
 *    1. 在已有的控件基础上加上自己的功能 继承
 *    2. 自己画
 */

/**
 * 控件的尺寸
 * 父视图：
 * 子视图：
 * 将一个控件添加到一个容器中，控件就是这个容器的子视图
 * 容器就是这个控件的父视图
 *
 * 1. match_parent 和父视图一样大
 * 2. wrap_content 包裹内容 和控件的内容一样大
 * 3. 20dp         具体尺寸
 */

/**
 * 一个控件就是一个类的具体对象
 * ImageView：
 *  属性
 *  方法
 */

/**
 * 程序或者某个UI模块都可以有自己的样式style
 * 可以在values.styles.xml里面定义
 * 不需要ActionBar
 * parent="Theme.AppCompat.Light.NoActionBar"
 */

/**
 * 使用java代码来布局界面
 * 通过添加id号可以唯一标识某一个控件 或者 组件(容器)
 * android:id="@+id/fl_main"
 */

/**
 * xml：解耦 安卓推荐使用
 * 什么时候需要用代码创建 什么时候使用xml配置
 * 如果添加的控件是静态的（变化的东西不多）选择xml配置
 * 如果需要灵活地操作这个控件  选择代码创建
 */

/**
 * 撕衣服的思路
 * 使用透明色去替换原有图片的对应点的像素
 * 立刻获取替换之后的图片 将图片显示在ImageView上
 */
public class MainActivity extends AppCompatActivity {
    ImageView forground;
    Bitmap copyBitmap;
    Canvas canvas;
    Paint paint;
    Bitmap orgBitmap;
    @Override //创建一个界面 界面如何布局
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //配置界面
        setContentView(R.layout.activity_main);

        //找到容器里面的图片视图控件
        //findViewById
        forground = findViewById(R.id.iv_forground);

        //将需要操作的图片读取出来 Bitmap
        //BitmapFactory 用于管理位图
        //decodeResource 从工程的资源路径中去生成一张位图
        //getResources() 获取工程的资源
        //R.drawable.fr  访问资源路径下 drawable里面的一个文件名为fr的资源
        orgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fr);

        //操作这张图片 用透明色去替换某个位置的颜色
        //不能操作原图 只能copy一份
        //创建一个和原始图片相同环境的空位图
        copyBitmap = Bitmap.createBitmap(orgBitmap.getWidth(),
                orgBitmap.getHeight(), orgBitmap.getConfig());

        //创建一个Canvas 画布-现实中的画板
        canvas = new Canvas(copyBitmap);

        //创建一个画笔
        paint = new Paint();

        //创建一个矩阵
        Matrix matrix = new Matrix();
        //旋转图片
        //matrix.setRotate(90,240,400);
        //平移
        //matrix.setTranslate(100,0);
        //翻转 set之作用一次 post作用多次
        //matrix.setScale(-1f,1f);
        //matrix.postTranslate(orgBitmap.getWidth(),0);
        //画一幅图
        canvas.drawBitmap(orgBitmap,matrix,paint);
        //显示图片
        forground.setImageBitmap(copyBitmap);

        //给前景图片添加touch事件
        //当有触摸事件发生 系统就会将这个事件接收并回调这个事件
        forground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取当前事件
                int action = event.getAction();
                //判断状态
                if (action == MotionEvent.ACTION_MOVE){
                    //获取触摸点的坐标
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    System.out.println(canvas);
                    //替换x，y对应的像素
                    for (int i = -8; i < 8; i++) {
                        for (int j = -8; j < 8; j++) {
                            copyBitmap.setPixel(x+i,y+j,Color.TRANSPARENT);
                        }
                    }
                    forground.setImageBitmap(copyBitmap);
                }
                return true;
            }
        });
    }

    public void code(){
        //2.设置当前这个界面的内容视图为这个容器
        //通过代码来布局界面
        //1.找一个容器 xxlayout
        FrameLayout container = new FrameLayout(this);

        //3.创建一个子视图
        //创建ImageView显示图片
        ImageView bgImageView = new ImageView(this);
        //设置属性
        bgImageView.setBackgroundColor(Color.GREEN);
        //添加到容器里面
        container.addView(bgImageView,200,200);

        //2.设置当前这个界面的内容视图为这个容器
        setContentView(container);
    }
    /*
    @Override //界面启动 展现出来了
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");

    }

    @Override //重新启动一个界面
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }

    @Override //恢复界面 后台->前台
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override //暂停界面 界面切换
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override //界面销毁
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }
    */
}

















