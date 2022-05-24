package com.example.myapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DIYclass.AdaptView;
import com.example.myapplication.DIYclass.Alarm;
import com.example.myapplication.DIYclass.DatabaseOperation;
import com.example.myapplication.DIYclass.SQLBean;
import com.example.myapplication.DIYclass.NoteAdapter;
import com.example.myapplication.DIYclass.DoubleDatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Maindesign extends AppCompatActivity{
    private TextView tv_back;//返回键
    private TextView add_note;//新增键
    private DatabaseOperation dop;//自定义数据库类
    public NoteAdapter adapter;
    private TextView tv_note_id, tv_locktype, tv_lock,personel,memoshare;
    private AdaptView lv_notes;
    public EditText et_keyword;// 搜索框
    private SQLiteDatabase db;//数据库对象
    public AlarmManager am;
    public static MediaPlayer mediaPlayer;// 音乐播放器
    public static Vibrator vibrator;//手机震动器

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindesign);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        et_keyword = (EditText) findViewById(R.id.et_keyword);
        // 数据库操作
        dop = new DatabaseOperation(this, db);
        lv_notes = (AdaptView) findViewById(R.id.lv_notes);
        if (am == null) {
            am = (AlarmManager) getSystemService(ALARM_SERVICE);//通过系统服务获取
        }
        try {
            Intent intent = new Intent(Maindesign.this, Alarm.class);//onCreate方法中先判断闹钟
            PendingIntent sender = PendingIntent.getBroadcast(
                    Maindesign.this, 0, intent, 0);
            am.setRepeating(AlarmManager.RTC_WAKEUP, 0, 60 * 1000, sender);
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();

    }
    private void init(){
        tv_back=findViewById(R.id.tv_back);
        add_note=findViewById(R.id.add_note);
        personel=findViewById(R.id.personel);
        memoshare=findViewById(R.id.memo_share);

        memoshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Maindesign.this, Memo_share.class);
                intent.putExtra("editModel", "newAdd");
                startActivity(intent);
            }
        });

        //返回键
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Maindesign.this.finish();
            }
        });

        //新增键
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Maindesign.this, Addnote.class);
                intent.putExtra("editModel", "newAdd");
                startActivity(intent);
            }
        });

        personel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Maindesign.this, Personel.class);
                intent.putExtra("editModel1", "newAdd1");
                startActivity(intent);
            }
        });


    }

    //显示备忘录预览列表
    private void showNotesList() {
        // 创建或打开数据库 获取数据
        dop.create_db();
        //获取数据库内容
        Cursor cursor = dop.query_db();
        if (cursor.getCount() > 0) {
            List<SQLBean> list = new ArrayList<SQLBean>();//备忘录信息集合里
            while (cursor.moveToNext()) {// 光标移动成功
                SQLBean bean = new SQLBean();
                bean.set_id("" + cursor.getInt(cursor.getColumnIndex("_id")));
                bean.setContext(cursor.getString(cursor
                        .getColumnIndex("context")));
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                bean.setDatatype(cursor.getString(cursor
                        .getColumnIndex("datatype")));
                bean.setDatatime(cursor.getString(cursor
                        .getColumnIndex("datatime")));
                bean.setLocktype(cursor.getString(cursor
                        .getColumnIndex("locktype")));
                bean.setLock(cursor.getString(cursor.getColumnIndex("lock")));
                list.add(bean);
            }
            //倒序显示数据
            Collections.reverse(list);
            adapter = new NoteAdapter(list, this);
            lv_notes.setAdapter(adapter);
        }
        dop.close_db();//关闭数据库
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // 显示记事列表
        showNotesList();
        // 为记事列表添加监听器
        lv_notes.setOnItemClickListener(new ItemClickEvent());
        // 为记事列表添加长按事件
        lv_notes.setOnItemLongClickListener(new ItemLongClickEvent());
    }


    // 记事列表单击监听器
    class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            tv_note_id = (TextView) view.findViewById(R.id.tv_note_id);
            tv_locktype = (TextView) view.findViewById(R.id.tv_locktype);
            tv_lock = (TextView) view.findViewById(R.id.tv_lock);
            String locktype = tv_locktype.getText().toString();
            String lock = tv_lock.getText().toString();
            int item_id = Integer.parseInt(tv_note_id.getText().toString());
            if ("0".equals(locktype)) {//如果没加锁
                Intent intent = new Intent(Maindesign.this, Addnote.class);
                intent.putExtra("editModel", "update");
                intent.putExtra("noteId", item_id);
                startActivity(intent);
            } else {
                inputTitleDialog(lock, 0, item_id);
            }
        }
    }
    // 记事列表长按监听器
    class ItemLongClickEvent implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            tv_note_id = view.findViewById(R.id.tv_note_id);
            tv_locktype = view.findViewById(R.id.tv_locktype);
            tv_lock = view.findViewById(R.id.tv_lock);
            String locktype = tv_locktype.getText().toString();
            //获取控件上日记密码信息
            String lock = tv_lock.getText().toString();
            //获取控件上id信息转换成int类型
            int item_id = Integer.parseInt(tv_note_id.getText().toString());
            //弹出选择操作框方法
            simpleList(item_id, locktype, lock);
            return true;
        }
    }
    // 简单列表对话框，用于选择操作
    public void simpleList(final int item_id, final String locktype,
                           final String lock) {
        //实例化AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,
                R.style.custom_dialog);
        //设置弹窗标题
        alertDialogBuilder.setTitle("选择操作");
        //设置弹窗图片
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        //设置弹窗选项内容
        alertDialogBuilder.setItems(R.array.itemOperation,
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            // 编辑
                            case 0:
                                if ("0".equals(locktype)) {//判断是否添加了秘密锁
                                    Intent intent = new Intent(Maindesign.this,
                                            Addnote.class);//跳转到添加日记页
                                    intent.putExtra("editModel", "update");//传递编辑信息
                                    intent.putExtra("noteId", item_id);//传递id信息
                                    startActivity(intent);//开始跳转
                                } else {//有秘密锁
                                    // 弹出输入密码框
                                    inputTitleDialog(lock, 0, item_id);
                                }
                                break;
                            // 删除
                            case 1:
                                if ("0".equals(locktype)) {// 判断是否是加密日记 0没有
                                    dop.create_db();// 打开数据库
                                    dop.delete_db(item_id);//删除数据
                                    dop.close_db();// 关闭数据库
                                    // 刷新列表显示
                                    lv_notes.invalidate();
                                    showNotesList();
                                } else {//有秘密锁
                                    // 弹出输入密码框
                                    inputTitleDialog(lock, 1, item_id);
                                    // 刷新列表显示
                                    lv_notes.invalidate();
                                    //显示日记列表信息
                                    showNotesList();
                                }
                                break;
                        }
                    }
                });
        alertDialogBuilder.create();//创造弹窗
        alertDialogBuilder.show();//显示弹窗
    }





}
