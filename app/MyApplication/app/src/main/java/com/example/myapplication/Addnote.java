package com.example.myapplication;
import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.DIYclass.DatabaseOperation;
import com.example.myapplication.DIYclass.DateTimePickerDialog;
import com.example.myapplication.DIYclass.LineEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.example.myapplication.DIYclass.CloudSQLBean;

public class Addnote extends AppCompatActivity{
    private int[] bottomItems = {R.drawable.tabbar_camera, R.drawable.tabbar_appendix};//导入底部的相机图标，和设置提醒实践图标
    //按钮
    private TextView bt_back; //返回键
    private TextView bt_save; //完成键
    private TextView tv_title; //标题
    private SQLiteDatabase db;//数据库操作类
    private DatabaseOperation dop;//自定义数据库(DatabaseOperation)
    private LineEditText et_Notes;//自定义文本编辑栏(LineEditText)
    private GridView bottomMenu;
    private RelativeLayout datarl;
    private TextView datatv;
    private ScrollView sclv;
    private ImageButton ib_lk;

    //变量
    InputMethodManager imm;//控制手机键盘
    Intent intent;
    String editModel = null;
    int item_Id;
    String title;
    String time;
    String context;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        Bmob.initialize(this, "b69a3918b4468f7d26a7f678c3262d69");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init(){
        bt_back = findViewById(R.id.tv_back);
        bt_save = findViewById(R.id.bt_save);
        tv_title = findViewById(R.id.tv_title);
        et_Notes = findViewById(R.id.et_note);
        bottomMenu = findViewById(R.id.bottomMenu);
        datarl = findViewById(R.id.datarl);
        datatv = findViewById(R.id.datatv);
        sclv = findViewById(R.id.sclv);
        ib_lk = findViewById(R.id.ib_lk);

        //重写返回键
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addnote.this.finish();
            }
        });
        //重写保存键
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = et_Notes.getText().toString();
                if (context.isEmpty()) {
                    Toast.makeText(Addnote.this, "备忘录为空!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    //本地数据库保存
                    // 取得当前时间
                    SimpleDateFormat formatter = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm");
                    Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
                    time = formatter.format(curDate);
                    // 截取EditText中的前一部分作为标题，用于显示在主页列表中
                    title = getTitle(context);

                    // 打开数据库
                    dop.create_db();
                    // 判断是更新还是新增记事
                    if (editModel.equals("newAdd")) {
                        // 将记事插入到数据库中
                        dop.insert_db(title, context, time, datatype, datatime,
                                locktype, lock);
                    }
                    // 如果是编辑则更新记事即可
                    else if (editModel.equals("update")) {
                        dop.update_db(title, context, time, datatype, datatime,
                                locktype, lock, item_Id);
                    }

                    cdop.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId,BmobException e) {
                            if(e==null){

                            }else{
                            }
                        }
                    });


                    Addnote.this.finish();
                }
            }
        });
        // 配置菜单
        initBottomMenu();
        // 为菜单设置监听器
        bottomMenu.setOnItemClickListener(new MenuClickEvent());
        // 默认关闭软键盘,可以通过失去焦点设置
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_Notes.getWindowToken(), 0);
        dop = new DatabaseOperation(this, db);
        intent = getIntent();
        editModel = intent.getStringExtra("editModel");
        item_Id = intent.getIntExtra("noteId", 0);
        // 加载数据
        loadData();
    }

    //配置菜单方法
    private void initBottomMenu() {
        //菜单集合
        ArrayList<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < bottomItems.length; i++) {//循环菜单集合
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", bottomItems[i]);//循环图片集合添加到菜单中
            menus.add(item);//添加图片菜单到底部菜单
        }
        //菜单长度
        bottomMenu.setNumColumns(bottomItems.length);
        //底部菜单
        bottomMenu.setSelector(R.drawable.bottom_item);
        //实例化底部菜单适配器
        SimpleAdapter mAdapter = new SimpleAdapter(Addnote.this, menus,
                R.layout.item_button, new String[]{"image"},
                new int[]{R.id.item_image});
        bottomMenu.setAdapter(mAdapter);//为底部菜单添加适配器
    }
    //进入新建备忘录前的加载数据
    private void loadData() {
        // 如果是新增备忘录模式，则将editText清空
        if (editModel.equals("newAdd")) {
            et_Notes.setText("");
        }
        // 如果编辑的是已存在的记事，则将数据库的保存的数据取出，并显示在EditText中
        else if (editModel.equals("update")) {
            tv_title.setText("编辑记事");
            dop.create_db();
            Cursor cursor = dop.query_db(item_Id);
            cursor.moveToFirst();
            // 取出数据库中相应的字段内容
            context = cursor.getString(cursor.getColumnIndex("context"));
            datatype = cursor.getString(cursor.getColumnIndex("datatype"));
            datatime = cursor.getString(cursor.getColumnIndex("datatime"));
            locktype = cursor.getString(cursor.getColumnIndex("locktype"));
            lock = cursor.getString(cursor.getColumnIndex("lock"));
            if ("0".equals(locktype)) {
                ib_lk.setBackgroundResource(R.drawable.un_locky);
            } else {
                ib_lk.setBackgroundResource(R.drawable.locky);
            }
            if ("0".equals(datatype)) {
                datarl.setVisibility(View.GONE);
            } else {
                datarl.setVisibility(View.VISIBLE);
                datatv.setText("提醒时间：" + datatime);
            }
            // 定义正则表达式，用于匹配路径
            Pattern p = Pattern.compile("/([^\\.]*)\\.\\w{3}");
            Matcher m = p.matcher(context);
            int startIndex = 0;
            while (m.find()) {
                // 取出路径前的文字
                if (m.start() > 0) {
                    et_Notes.append(context.substring(startIndex, m.start()));
                }
            }
            // 将最后一个图片之后的文字添加在TextView中
            et_Notes.append(context.substring(startIndex, context.length()));
            dop.close_db();
        }
    }

    class MenuClickEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent;
            switch (position) {
                // 拍照
                case 0:
                    if (Build.VERSION.SDK_INT >= 23) {

                        if(ContextCompat.checkSelfPermission(Addnote.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                            //申请权限
                            ActivityCompat.requestPermissions(Addnote.this,
                                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    1);
                        }else {
                            // 调用系统拍照界面
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            // 区分选择相片
                            startActivityForResult(intent, 2);
                        }
                    } else {
                        // 调用系统拍照界面
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 区分选择相片
                        startActivityForResult(intent, 2);
                    }

                    break;
                // 提醒设置
                case 1:
                    setReminder();
                    break;

            }
        }
    }



    //获取系统时间
    public static long getdaytime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dt2 = null;
        try {
            dt2 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt2.getTime();
    }

    // 截取EditText中的前一部分作为标题，用于显示在主页列表中
    private String getTitle(String context) {
        // 定义正则表达式，用于匹配路径
        Pattern p = Pattern.compile("/([^\\.]*)\\.\\w{3}");
        Matcher m = p.matcher(context);
        StringBuffer strBuff = new StringBuffer();
        String title = "";
        int startIndex = 0;
        while (m.find()) {
            // 取出路径前的文字
            if (m.start() > 0) {
                strBuff.append(context.substring(startIndex, m.start()));
            }
            // 取出路径
            String path = m.group().toString();
            // 取出路径的后缀
            String type = path.substring(path.length() - 3, path.length());
            // 判断附件的类型
            if (type.equals("amr")) {
                strBuff.append("[录音]");
            } else {
                // strBuff.append("");
            }
            startIndex = m.end();
            // 只取出前15个字作为标题
            if (strBuff.length() > 15) {
                // 统一将回车,等特殊字符换成空格
                title = strBuff.toString().replaceAll("\r|\n|\t", " ");
                return title;
            }
        }
        strBuff.append(context.substring(startIndex, context.length()));
        // 统一将回车,等特殊字符换成空格
        title = strBuff.toString().replaceAll("\r|\n|\t", " ");
        return title;
    }







    // 等比例缩放图片
    private Bitmap resize(Bitmap bitmap, int S) {
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        double partion = imgWidth * 1.0 / imgHeight;
        double sqrtLength = Math.sqrt(partion * partion + 1);
        // 新的缩略图大小
        double newImgW = S * (partion / sqrtLength);
        double newImgH = S * (1 / sqrtLength);
        float scaleW = (float) (newImgW / imgWidth);
        float scaleH = (float) (newImgH / imgHeight);
        Matrix mx = new Matrix();
        // 对原图片进行缩放
        mx.postScale(scaleW, scaleH);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, mx,
                true);
        return bitmap;
    }



    // 点击闹钟提醒
    public void onDataChange(View v) {
        setReminder();
    }

    //密码锁
    public void onLOCK(View v) {
        if ("0".equals(locktype)) {//判断是否设置了密码
            inputlockDialog();//弹出设置密码弹窗
        } else {
            inputunlockDialog();//弹出取消密码弹窗
        }
    }

    //取消密码锁
    private void inputunlockDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//创建弹出框
        builder.setTitle("是否取消密码")
                .setNegativeButton("取消", null);//在弹窗上设置标题设置取消按钮
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {//设置确认按钮
                locktype = "0";//设置没有设置密码
                lock = "0";//设置密码
                ib_lk.setBackgroundResource(R.drawable.un_locky);
                Toast.makeText(Addnote.this, "密码已取消",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.show();//弹出取消密码弹窗
    }

    //设置密码弹窗
    private void inputlockDialog() {
        final EditText inputServer = new EditText(this);//创建EditText输入框
        inputServer.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置输入框类型
        inputServer.setFocusable(true);//获取焦点
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//创建弹出框
        builder.setTitle("设置密码").setView(inputServer)
                .setNegativeButton("取消", null);//在弹窗上设置标题添加输入框
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {//设置确认按钮
                String inputName = inputServer.getText().toString();
                if ("".equals(inputName)) {//判断输入框内容是否为空
                    Toast.makeText(Addnote.this, "密码不能为空 请重新输入！",
                            Toast.LENGTH_LONG).show();
                } else {//输入框内容不为空　
                    lock = inputName;//密码
                    locktype = "1";//添加了密码锁
                    ib_lk.setBackgroundResource(R.drawable.locky);//设置添加锁图案
                    Toast.makeText(Addnote.this, "密码设置成功！",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.show();//弹出设置密码弹窗
    }

    // 分享功能
    public void onFX(View v) {
        Bitmap c = getBitmapByView(sclv);//获取到图片
        try {
            saveMyBitmap("notesimge", c);//保存图片
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String imagePath = Environment.getExternalStorageDirectory()
                + File.separator + "notesimge.jpg";//图片路径
        Uri imageUri = Uri.fromFile(new File(imagePath));//获取uri地址
        Log.d("share", "uri:" + imageUri);//打印图片路径
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);//过滤条件允许分享的
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);//分享图片
        shareIntent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//默认跳转类型
        startActivity(Intent.createChooser(shareIntent, "分享到："));
    }

    //保存图片
    public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "notesimge.jpg");//初始化文件
        f.createNewFile();//创建图片文件
        FileOutputStream fOut = null;//创建文件流
        try {
            fOut = new FileOutputStream(f);//实例化文件流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);//图片保存到文件中
        try {
            fOut.flush();//文件写写入操作结束
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();//关闭文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //截取滚动栏的屏幕,作为主屏幕的预览
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#FFFFFF"));
        }
        // 创建合适scrollview大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);//绘制图片
        scrollView.draw(canvas);
        return bitmap;//返回图片
    }



}
