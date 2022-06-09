package com.example.myapplication

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.PepareClass.DatabaseOperation
import com.example.myapplication.PepareClass.AdaptView
import android.widget.TextView
import android.widget.EditText
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.PepareClass.NoteAdapter
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.app.AlertDialog
import android.text.InputType
import android.view.View
import java.util.*

class Search : Activity() {
    private val db: SQLiteDatabase? = null
    private var dop: DatabaseOperation? = null
    private var lv_notes: AdaptView? = null
    private var tv_note_id: TextView? = null
    private var tv_locktype: TextView? = null
    private var tv_lock: TextView? = null
    var et_keyword: EditText? = null
    var keword //关键字
            : String? = null
    private var tv_back //返回键
            : TextView? = null
    var intent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        et_keyword = findViewById(R.id.et_keyword)
        intent = getIntent() //获取当前传递对象
        keword = intent.getStringExtra("keword") //获取传递参数
        et_keyword.setText(keword)
        // 数据库操作
        dop = DatabaseOperation(this, db)
        lv_notes = findViewById<View>(R.id.lv_notes) as AdaptView
        init()
    }

    private fun init() {
        tv_back = findViewById(R.id.tv_back)

        //返回键
        tv_back.setOnClickListener(View.OnClickListener { finish() })
    }

    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart()
        // 显示记事列表
        showNotesList()
        // 为记事列表添加监听器
        lv_notes!!.onItemClickListener = ItemClickEvent()
        // 为记事列表添加长按事件
        lv_notes!!.onItemLongClickListener = ItemLongClickEvent()
    }

    // 显示记事列表
    private fun showNotesList() {
        // 打开数据库
        dop!!.create_db()
        val cursor = dop!!.query_db(keword) //%模糊查询
        if (cursor.count > 0) {
            val list: MutableList<SQLBean?> = ArrayList() //实例化数据列表
            while (cursor.moveToNext()) { // 光标移动成功
                val bean = SQLBean() //创建数据库实体类
                //保存日记信息id到实体类
                bean._id = "" + cursor.getInt(cursor.getColumnIndex("_id"))
                //保存日记内容到实体类
                bean.context = cursor.getString(cursor
                        .getColumnIndex("context"))
                //保存日记标题到实体类
                bean.title = cursor.getString(cursor.getColumnIndex("title"))
                //保存日记记录时间到实体类
                bean.time = cursor.getString(cursor.getColumnIndex("time"))
                //保存日记是否设置提醒时间到实体类
                bean.datatype = cursor.getString(cursor
                        .getColumnIndex("datatype"))
                //保存日记提醒时间到实体类
                bean.datatime = cursor.getString(cursor
                        .getColumnIndex("datatime"))
                //保存日记是否设置了日记锁到实体类
                bean.locktype = cursor.getString(cursor
                        .getColumnIndex("locktype"))
                //保存日记锁密码到实体类
                bean.lock = cursor.getString(cursor.getColumnIndex("lock"))
                //把保存日记信息实体类保存到日记信息集合里
                list.add(bean)
            }
            //倒序显示数据
            Collections.reverse(list)
            //装载日记信息到首页
            val adapter = NoteAdapter(list, this)
            //日记列表设置日记信息适配器
            lv_notes!!.adapter = adapter
        } else {
            Toast.makeText(this@Search, "暂无记事！",
                    Toast.LENGTH_LONG).show()
        }
        //关闭数据库
        dop!!.close_db()
    }

    // 记事列表单击监听器
    internal inner class ItemClickEvent : OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int,
                                 id: Long) {
            tv_note_id = view.findViewById<View>(R.id.tv_note_id) as TextView
            tv_locktype = view.findViewById<View>(R.id.tv_locktype) as TextView
            tv_lock = view.findViewById<View>(R.id.tv_lock) as TextView
            val locktype = tv_locktype!!.text.toString()
            val lock = tv_lock!!.text.toString()
            val item_id = tv_note_id!!.text.toString().toInt()
            if ("0" == locktype) {
                val intent = Intent(this@Search, Addnote::class.java)
                intent.putExtra("editModel", "update")
                intent.putExtra("noteId", item_id)
                startActivity(intent)
            } else {
                inputTitleDialog(lock, 0, item_id)
            }
        }
    }

    // 记事列表长按监听器
    internal inner class ItemLongClickEvent : OnItemLongClickListener {
        override fun onItemLongClick(parent: AdapterView<*>?, view: View,
                                     position: Int, id: Long): Boolean {
            tv_note_id = view.findViewById<View>(R.id.tv_note_id) as TextView
            tv_locktype = view.findViewById<View>(R.id.tv_locktype) as TextView
            tv_lock = view.findViewById<View>(R.id.tv_lock) as TextView
            val locktype = tv_locktype!!.text.toString()
            val lock = tv_lock!!.text.toString()
            val item_id = tv_note_id!!.text.toString().toInt()
            simpleList(item_id, locktype, lock)
            return true
        }
    }

    // 简单列表对话框，用于选择操作
    fun simpleList(item_id: Int, locktype: String,
                   lock: String) {
        val alertDialogBuilder = AlertDialog.Builder(this,
                R.style.custom_dialog)
        alertDialogBuilder.setTitle("选择操作")
        alertDialogBuilder.setIcon(R.drawable.icon)
        alertDialogBuilder.setItems(R.array.itemOperation
        ) { dialog, which ->
            when (which) {
                0 -> if ("0" == locktype) {
                    val intent = Intent(this@Search, Addnote::class.java)
                    intent.putExtra("editModel", "update")
                    intent.putExtra("noteId", item_id)
                    startActivity(intent)
                } else {
                    inputTitleDialog(lock, 0, item_id)
                }
                1 -> if ("0" == locktype) {
                    dop!!.create_db()
                    dop!!.delete_db(item_id)
                    dop!!.close_db()
                    // 刷新列表显示
                    lv_notes!!.invalidate()
                    showNotesList()
                } else {
                    inputTitleDialog(lock, 1, item_id)
                    // 刷新列表显示
                    lv_notes!!.invalidate()
                    showNotesList()
                }
            }
        }
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    fun inputTitleDialog(lock: String, idtype: Int,
                         item_id: Int) { // 密码输入框
        val inputServer = EditText(this)
        inputServer.inputType = (InputType.TYPE_CLASS_TEXT
                or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        inputServer.isFocusable = true
        val builder = AlertDialog.Builder(this)
        builder.setTitle("请输入密码").setView(inputServer)
                .setNegativeButton("取消", null)
        builder.setPositiveButton("确认") { dialog, which ->
            val inputName = inputServer.text.toString()
            if ("" == inputName) {
                Toast.makeText(this@Search, "密码不能为空请重新输入！",
                        Toast.LENGTH_LONG).show()
            } else {
                if (inputName == lock) {
                    if (0 == idtype) {
                        val intent = Intent(this@Search,
                                Addnote::class.java)
                        intent.putExtra("editModel", "update")
                        intent.putExtra("noteId", item_id)
                        startActivity(intent)
                    } else if (1 == idtype) {
                        dop!!.create_db()
                        dop!!.delete_db(item_id)
                        dop!!.close_db()
                        // 刷新列表显示
                        lv_notes!!.invalidate()
                        showNotesList()
                    }
                } else {
                    Toast.makeText(this@Search, "密码不正确！",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
        builder.show()
    }

    // 搜索
    fun onSearch(v: View?) {
        val ek = et_keyword!!.text.toString()
        if ("" == ek) {
            Toast.makeText(this@Search, "请输入关键词！", Toast.LENGTH_LONG)
                    .show()
        } else {
            keword = ek
            showNotesList()
        }
    }
}