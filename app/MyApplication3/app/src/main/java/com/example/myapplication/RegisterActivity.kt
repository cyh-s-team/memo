package com.example.myapplication

import android.graphics.Color
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private var tv_back //返回按钮
            : TextView? = null
    private var btn_register //注册按钮
            : Button? = null

    //用户名，密码，再次输入的密码的控件
    private var et_user_name: EditText? = null
    private var et_psw: EditText? = null
    private var et_psw_again: EditText? = null

    //用户名，密码，再次输入的密码的控件的获取值
    private var userName: String? = null
    private var psw: String? = null
    private var pswAgain: String? = null

    //标题布局
    private var rl_title_bar: RelativeLayout? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置页面布局 ,注册界面
        setContentView(R.layout.register)
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        init()
    }

    private fun init() {
        //从main_title_bar.xml 页面布局中获取对应的UI控件
        tv_back = findViewById(R.id.tv_back)
        //布局根元素
        rl_title_bar = findViewById(R.id.title_bar)
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT)
        btn_register = findViewById(R.id.btn_register)
        et_user_name = findViewById(R.id.et_user_name)
        et_psw = findViewById(R.id.et_psw)
        et_psw_again = findViewById(R.id.et_psw_again)
        tv_back.setOnClickListener(View.OnClickListener { //返回键
            this@RegisterActivity.finish()
        })

        //注册按钮
        btn_register!!.setOnClickListener(View.OnClickListener {
            //获取输入在相应控件中的字符串
            editString
            //判断输入框内容
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this@RegisterActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (TextUtils.isEmpty(psw)) {
                Toast.makeText(this@RegisterActivity, "请输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (TextUtils.isEmpty(pswAgain)) {
                Toast.makeText(this@RegisterActivity, "请再次输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (psw != pswAgain) {
                Toast.makeText(this@RegisterActivity, "输入两次的密码不一样", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (isExistUserName(userName)) {
                Toast.makeText(this@RegisterActivity, "此账户名已经存在", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else {
                Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
                //把账号、密码和账号标识保存到sp里面
                saveRegisterInfo(userName, psw)
                val data = Intent()
                data.putExtra("userName", userName)
                setResult(RESULT_OK, data)
                this@RegisterActivity.finish()
            }
        })
    }

    private val editString: Unit
        private get() {
            userName = et_user_name.getText().toString().trim({ it <= ' ' })
            psw = et_psw.getText().toString().trim({ it <= ' ' })
            pswAgain = et_psw_again.getText().toString().trim({ it <= ' ' })
        }

    private fun isExistUserName(userName: String?): Boolean {
        var has_userName = false
        val sp: SharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)
        //获取密码
        val spPsw: String = sp.getString(userName, "") //传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true
        }
        return has_userName
    }

    private fun saveRegisterInfo(userName: String?, psw: String?) {
        val md5Psw = psw
        val sp: SharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString(userName, md5Psw)
        editor.commit()
    }
}