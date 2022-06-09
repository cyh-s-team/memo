package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.media.MediaPlayer
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.PepareClass.DatabaseOperation
import com.example.myapplication.PepareClass.NoteAdapter
import java.util.*

class Maindesign : AppCompatActivity() {
    private var tv_back //返回键
            : TextView? = null
    private var add_note //新增键
            : TextView? = null
    private var dop //自定义数据库类
            : DatabaseOperation? = null
    var adapter: NoteAdapter? = null
    private var tv_note_id: TextView? = null
    private var tv_locktype: TextView? = null
    private var tv_lock: TextView? = null
    private var personel: TextView? = null
    private var memoshare: TextView? = null
    private var lv_notes: AdaptView? = null
    var et_keyword // 搜索框
            : EditText? = null
    private val db //数据库对象
            : SQLiteDatabase? = null
    var am: AlarmManager? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maindesign)
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        et_keyword = findViewById(R.id.et_keyword) as EditText?
        // 数据库操作
        dop = DatabaseOperation(this, db)
        lv_notes = findViewById(R.id.lv_notes) as AdaptView?
        if (am == null) {
            am = getSystemService(ALARM_SERVICE) as AlarmManager? //通过系统服务获取
        }
        try {
            val intent = Intent(this@Maindesign, Alarm::class.java) //onCreate方法中先判断闹钟
            val sender: PendingIntent = PendingIntent.getBroadcast(
                    this@Maindesign, 0, intent, 0)
            am.setRepeating(AlarmManager.RTC_WAKEUP, 0, (60 * 1000).toLong(), sender)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        init()
    }

    private fun init() {
        tv_back = findViewById(R.id.tv_back)
        add_note = findViewById(R.id.add_note)
        personel = findViewById(R.id.personel)
        memoshare = findViewById(R.id.memo_share)
        memoshare.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Maindesign, Memo_share::class.java)
            intent.putExtra("editModel", "newAdd")
            startActivity(intent)
        })

        //返回键
        tv_back.setOnClickListener(View.OnClickListener { this@Maindesign.finish() })

        //新增键
        add_note.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Maindesign, Addnote::class.java)
            intent.putExtra("editModel", "newAdd")
            startActivity(intent)
        })
        personel.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Maindesign, Personel::class.java)
            intent.putExtra("editModel", "newAdd")
            startActivity(intent)
        })
    }

    //显示备忘录预览列表
    private fun showNotesList() {
        // 创建或打开数据库 获取数据
        dop.create_db()
        //获取数据库内容
        val cursor: Cursor = dop.query_db()
        if (cursor.count > 0) {
            val list: MutableList<SQLBean?> = ArrayList<SQLBean?>() //备忘录信息集合里
            while (cursor.moveToNext()) { // 光标移动成功
                val bean = SQLBean()
                bean.set_id("" + cursor.getInt(cursor.getColumnIndex("_id")))
                bean.setContext(cursor.getString(cursor
                        .getColumnIndex("context")))
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")))
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")))
                bean.setDatatype(cursor.getString(cursor
                        .getColumnIndex("datatype")))
                bean.setDatatime(cursor.getString(cursor
                        .getColumnIndex("datatime")))
                bean.setLocktype(cursor.getString(cursor
                        .getColumnIndex("locktype")))
                bean.setLock(cursor.getString(cursor.getColumnIndex("lock")))
                list.add(bean)
            }
            //倒序显示数据
            Collections.reverse(list)
            adapter = NoteAdapter(list, this)
            lv_notes.setAdapter(adapter)
        }
        dop.close_db() //关闭数据库
    }

    protected fun onStart() {
        // TODO Auto-generated method stub
        super.onStart()
        // 显示记事列表
        showNotesList()
        // 为记事列表添加监听器
        lv_notes.setOnItemClickListener(ItemClickEvent())
        // 为记事列表添加长按事件
        lv_notes.setOnItemLongClickListener(ItemLongClickEvent())
    }

    // 记事列表单击监听器
    internal inner class ItemClickEvent : OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int,
                                 id: Long) {
            tv_note_id = view.findViewById<View>(R.id.tv_note_id) as TextView
            tv_locktype = view.findViewById<View>(R.id.tv_locktype) as TextView
            tv_lock = view.findViewById<View>(R.id.tv_lock) as TextView
            val locktype: String = tv_locktype.getText().toString()
            val lock: String = tv_lock.getText().toString()
            val item_id: Int = tv_note_id.getText().toString().toInt()
            if ("0" == locktype) { //如果没加锁
                val intent = Intent(this@Maindesign, Addnote::class.java)
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
        override fun onItemLongClick(parent: AdapterView<*>?, view: View, position: Int, id: Long): Boolean {
            tv_note_id = view.findViewById<TextView>(R.id.tv_note_id)
            tv_locktype = view.findViewById<TextView>(R.id.tv_locktype)
            tv_lock = view.findViewById<TextView>(R.id.tv_lock)
            val locktype: String = tv_locktype.getText().toString()
            //获取控件上日记密码信息
            val lock: String = tv_lock.getText().toString()
            //获取控件上id信息转换成int类型
            val item_id: Int = tv_note_id.getText().toString().toInt()
            //弹出选择操作框方法
            simpleList(item_id, locktype, lock)
            return true
        }
    }

    // 简单列表对话框，用于选择操作
    fun simpleList(item_id: Int, locktype: String,
                   lock: String) {
        //实例化AlertDialog
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this,
                R.style.custom_dialog)
        //设置弹窗标题
        alertDialogBuilder.setTitle("选择操作")
        //设置弹窗图片
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher)
        //设置弹窗选项内容
        alertDialogBuilder.setItems(R.array.itemOperation,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            0 -> if ("0" == locktype) { //判断是否添加了秘密锁
                                val intent = Intent(this@Maindesign,
                                        Addnote::class.java) //跳转到添加日记页
                                intent.putExtra("editModel", "update") //传递编辑信息
                                intent.putExtra("noteId", item_id) //传递id信息
                                startActivity(intent) //开始跳转
                            } else { //有秘密锁
                                // 弹出输入密码框
                                inputTitleDialog(lock, 0, item_id)
                            }
                            1 -> if ("0" == locktype) { // 判断是否是加密日记 0没有
                                dop.create_db() // 打开数据库
                                dop.delete_db(item_id) //删除数据
                                dop.close_db() // 关闭数据库
                                // 刷新列表显示
                                lv_notes.invalidate()
                                showNotesList()
                            } else { //有秘密锁
                                // 弹出输入密码框
                                inputTitleDialog(lock, 1, item_id)
                                // 刷新列表显示
                                lv_notes.invalidate()
                                //显示日记列表信息
                                showNotesList()
                            }
                        }
                    }
                })
        alertDialogBuilder.create() //创造弹窗
        alertDialogBuilder.show() //显示弹窗
    }

    // 加密日记打开弹出的输入密码框
    fun inputTitleDialog(lock: String, idtype: Int,
                         item_id: Int) { // 密码输入框
        val inputServer = EditText(this)
        inputServer.setInputType(InputType.TYPE_CLASS_TEXT
                or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        inputServer.setFocusable(true)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("请输入密码").setView(inputServer)
                .setNegativeButton("取消", null)
        builder.setPositiveButton("确认", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                val inputName: String = inputServer.getText().toString()
                if ("" == inputName) {
                    Toast.makeText(this@Maindesign, "密码不能为空请重新输入！",
                            Toast.LENGTH_LONG).show()
                } else {
                    if (inputName == lock) {
                        if (0 == idtype) {
                            val intent = Intent(this@Maindesign,
                                    Addnote::class.java)
                            intent.putExtra("editModel", "update")
                            intent.putExtra("noteId", item_id)
                            startActivity(intent)
                        } else if (1 == idtype) {
                            dop.create_db()
                            dop.delete_db(item_id)
                            dop.close_db()
                            // 刷新列表显示
                            lv_notes.invalidate()
                            showNotesList()
                        }
                    } else {
                        Toast.makeText(this@Maindesign, "密码不正确！",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        builder.show()
    }

    // 搜索功能
    fun onSearch(v: View?) {
        //获取搜索关键词
        val ek: String = et_keyword.getText().toString()
        if ("" == ek) { //判断搜索关键词是否为空
            Toast.makeText(this@Maindesign, "请输入关键词！", Toast.LENGTH_LONG)
                    .show()
        } else { //搜索输入不为空
            //进入搜索结果页
            val intent = Intent(this@Maindesign, Search::class.java)
            intent.putExtra("keword", ek) //传递关键词
            startActivity(intent) //开始跳转
        }
    }

    // 日期范围搜索
    fun onData(v: View?) {
        // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
        val c = Calendar.getInstance()
        DoubleDatePickerDialog(this@Maindesign, 0,
                object : DoubleDatePickerDialog.OnDateSetListener {
                    override fun onDateSet(startDatePicker: DatePicker,
                                           startYear: Int, startMonthOfYear: Int,
                                           startDayOfMonth: Int, endDatePicker: DatePicker,
                                           endYear: Int, endMonthOfYear: Int, endDayOfMonth: Int) {
                        if (startYear < endYear || startYear == endYear
                                && startMonthOfYear <= endMonthOfYear) {
                            val st = startMonthOfYear + 1
                            val et = endMonthOfYear + 1
                            val intent = Intent(this@Maindesign, DateSearch::class.java) //跳转到日期搜索
                            // sql判断 需要在月份前补0 否则sql语句判断不正确。
                            if (st < 10) {
                                if (startDayOfMonth < 10) {
                                    intent.putExtra("startData", startYear.toString() + "-0"
                                            + st + "-" + "0" + startDayOfMonth)
                                } else {
                                    intent.putExtra("startData", startYear.toString() + "-0"
                                            + st + "-" + startDayOfMonth)
                                }
                            } else {
                                if (startDayOfMonth < 10) {
                                    intent.putExtra("startData", startYear.toString() + "-"
                                            + st + "-" + "0" + startDayOfMonth)
                                } else {
                                    intent.putExtra("startData", startYear.toString() + "-"
                                            + st + "-" + startDayOfMonth)
                                }
                            }
                            if (et < 10) {
                                if (endDayOfMonth < 10) {
                                    intent.putExtra("endData", endYear.toString() + "-0" + et
                                            + "-" + "0" + endDayOfMonth)
                                } else {
                                    intent.putExtra("endData", endYear.toString() + "-0" + et
                                            + "-" + endDayOfMonth)
                                }
                            } else {
                                if (endDayOfMonth < 10) {
                                    intent.putExtra("endData", endYear.toString() + "-" + et
                                            + "-" + "0" + endDayOfMonth)
                                } else {
                                    intent.putExtra("endData", endYear.toString() + "-" + et
                                            + "-" + endDayOfMonth)
                                }
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@Maindesign, "日期选择错误请重新选择！",
                                    Toast.LENGTH_LONG).show()
                        }
                    }
                }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DATE], false).show()
    }

    companion object {
        @JvmField
        var mediaPlayer // 音乐播放器
                : MediaPlayer? = null
        @JvmField
        var vibrator //手机震动器
                : Vibrator? = null
    }
}