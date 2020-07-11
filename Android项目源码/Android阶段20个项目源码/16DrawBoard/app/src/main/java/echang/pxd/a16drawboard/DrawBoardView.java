package echang.pxd.a16drawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class DrawBoardView extends View {
    private ArrayList<Graph> graphs;//操作的数组
    private ArrayList<Graph> orginalGraphs;//原始数组

    private int lineColor = Color.BLACK;
    private int lineSize = 15;

    Path mPath;

    public DrawBoardView(Context context) {
        super(context);
        init();
    }

    public DrawBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //初始化数组
        graphs = new ArrayList<>();
        orginalGraphs = new ArrayList<>();
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //遍历数组
        Iterator<Graph> iterator = graphs.iterator();
        while (iterator.hasNext()){
            //从集合中获取一个图形对象
            Graph line = iterator.next();
            //绘制图形
            canvas.drawPath(line.path, line.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //创建当前这条线对应的Paint和Path
                Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(lineColor);
                mPaint.setStrokeWidth(lineSize);
                mPaint.setStyle(Paint.Style.STROKE);

                mPath = new Path();

                //设置图形的起点
                mPath.moveTo(event.getX(),event.getY());

                //保存当前这个图形的详细信息
                Graph temp = new Graph(mPaint,mPath);
                graphs.add(temp);
                orginalGraphs.add(temp);

                break;
            case MotionEvent.ACTION_MOVE:
                //连接从path终点到当前触摸点的线
                mPath.lineTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    //创建私有类来管理图形的画笔和路径
    private class Graph{
        Paint paint;
        Path path;

        public Graph(Paint paint, Path path) {
            this.paint = paint;
            this.path = path;
        }
    }

    //删除最后一个图形  撤销
    public void removeLast(){
        if (graphs.size() > 0){
            graphs.remove(graphs.size()-1);
            invalidate();
        }
    }

    //删除所有 清空
    public void removeAll(){
        graphs.clear();
        invalidate();
    }

    //还原上一步
    public void returnTolastStep(){
        //判断缓存中是否有
        if (graphs.size() < orginalGraphs.size()){
            //获取上一步的索引值
            int index = graphs.size()-1+1;
            //从缓存中获取index 添加到操作数组中
            graphs.add(orginalGraphs.get(index));
            //刷新
            invalidate();
        }
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }
}
