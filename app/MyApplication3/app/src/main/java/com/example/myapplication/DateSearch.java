package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.myapplication.PepareClass.NoteAdapter;
import com.example.myapplication.PepareClass.DatabaseOperation;
import com.example.myapplication.PepareClass.AdaptView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DateSearch extends Activity {
    private SQLiteDatabase db;
    private TextView tv_back;//返回键
    private DatabaseOperation dop;
    private AdaptView lv_notes;
    private TextView tv_note_id, tv_locktype, tv_lock;
    public static Vibrator vibrator;//震动器
    public TextView et_keyword;
    public String startData, endData;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datesearch);
        et_keyword = (TextView) findViewById(R.id.et_keyword);
        intent = getIntent();
        startData = intent.getStringExtra("startData");
        endData = intent.getStringExtra("endData");
        // 数据库操作
        et_keyword.setText("开始时间：" + startData + " \n" + "结束时间：" + endData);
        dop = new DatabaseOperation(this, db);
        lv_notes = findViewById(R.id.lv_notes);
        init();
    }

    private void init(){
        tv_back=findViewById(R.id.tv_back);

        //返回键
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSearch.this.finish();
            }
        });

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

    // 显示记事列表
    private void showNotesList() {
        // 创建或打开数据库
        dop.create_db();
        Cursor cursor = dop.query_db(startData, endData);//开始结束时间段查询
        if (cursor.getCount() > 0) {
            List<SQLBean> list = new ArrayList<SQLBean>();
            while (cursor.moveToNext()) {// 光标移动成功
                SQLBean bean = new SQLBean();//创建数据库实体类
                //保存备忘录信息
                bean.set_id("" + cursor.getInt(cursor.getColumnIndex("_id")));
                //保存备忘录内容
                bean.setContext(cursor.getString(cursor
                        .getColumnIndex("context")));
                //保存备忘录标题
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                //保存备忘录记录时间
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                //保存备忘录是否设置提醒时间
                bean.setDatatype(cursor.getString(cursor
                        .getColumnIndex("datatype")));
                //保存备忘录提醒时间
                bean.setDatatime(cursor.getString(cursor
                        .getColumnIndex("datatime")));
                //保存备忘录是否设置了日记锁
                bean.setLocktype(cursor.getString(cursor
                        .getColumnIndex("locktype")));
                //保存密码锁
                bean.setLock(cursor.getString(cursor.getColumnIndex("lock")));
                list.add(bean);
            }
            //倒序显示数据
            Collections.reverse(list);
            //装载日记信息到首页
            NoteAdapter adapter = new NoteAdapter(list, this);
            //日记列表设置日记信息适配器
            lv_notes.setAdapter(adapter);
        }else{
            Toast.makeText(DateSearch.this, "暂无记事！",
                    Toast.LENGTH_LONG).show();
        }
        //关闭数据库
        dop.close_db();
    }

    // 记事列表单击监听器
    class ItemClickEvent implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            tv_note_id = (TextView) view.findViewById(R.id.tv_note_id);
            tv_locktype = (TextView) view.findViewById(R.id.tv_locktype);
            tv_lock = (TextView) view.findViewById(R.id.tv_lock);
            String locktype = tv_locktype.getText().toString();
            String lock = tv_lock.getText().toString();
            int item_id = Integer.parseInt(tv_note_id.getText().toString());
            if ("0".equals(locktype)) {
                Intent intent = new Intent(DateSearch.this, Addnote.class);
                intent.putExtra("editModel", "update");
                intent.putExtra("noteId", item_id);
                startActivity(intent);
            } else {
                inputTitleDialog(lock, 0, item_id);
            }
        }
    }

    // 记事列表长按监听器
    class ItemLongClickEvent implements OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       int position, long id) {
            tv_note_id = (TextView) view.findViewById(R.id.tv_note_id);
            tv_locktype = (TextView) view.findViewById(R.id.tv_locktype);
            tv_lock = (TextView) view.findViewById(R.id.tv_lock);
            String locktype = tv_locktype.getText().toString();
            String lock = tv_lock.getText().toString();
            int item_id = Integer.parseInt(tv_note_id.getText().toString());
            simpleList(item_id, locktype, lock);
            return true;
        }
    }

    // 简单列表对话框，用于选择操作
    public void simpleList(final int item_id, final String locktype,
                           final String lock) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,
                R.style.custom_dialog);
        alertDialogBuilder.setTitle("选择操作");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setItems(R.array.itemOperation,
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            // 编辑
                            case 0:
                                if ("0".equals(locktype)) {
                                    Intent intent = new Intent(
                                            DateSearch.this, Addnote.class);
                                    intent.putExtra("editModel", "update");
                                    intent.putExtra("noteId", item_id);
                                    startActivity(intent);
                                } else {
                                    inputTitleDialog(lock, 0, item_id);
                                }
                                break;
                            // 删除
                            case 1:
                                if ("0".equals(locktype)) {
                                    dop.create_db();
                                    dop.delete_db(item_id);
                                    dop.close_db();
                                    // 刷新列表显示
                                    lv_notes.invalidate();
                                    showNotesList();
                                } else {
                                    inputTitleDialog(lock, 1, item_id);
                                    // 刷新列表显示
                                    lv_notes.invalidate();
                                    showNotesList();
                                }
                                break;
                        }
                    }
                });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }
    // 密码输入框
    public void inputTitleDialog(final String lock, final int idtype,
                                 final int item_id) {
        final EditText inputServer = new EditText(this);
        inputServer.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入密码").setView(inputServer)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String inputName = inputServer.getText().toString();
                if ("".equals(inputName)) {
                    Toast.makeText(DateSearch.this, "密码不能为空请重新输入！",
                            Toast.LENGTH_LONG).show();
                } else {
                    if (inputName.equals(lock)) {
                        if (0 == idtype) {
                            Intent intent = new Intent(DateSearch.this, Addnote.class);
                            intent.putExtra("editModel", "update");
                            intent.putExtra("noteId", item_id);
                            startActivity(intent);
                        } else if (1 == idtype) {
                            dop.create_db();
                            dop.delete_db(item_id);
                            dop.close_db();
                            // 刷新列表显示
                            lv_notes.invalidate();
                            showNotesList();
                        }
                    } else {
                        Toast.makeText(DateSearch.this, "密码不正确！",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        builder.show();
    }



}
