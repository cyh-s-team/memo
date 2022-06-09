

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


object HttpUtil{
    fun sendOkHttpRequest(address:String,callback:okhttp3.Callback){
        val client=OkHttpClient()
        val request=Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }

    fun sendOkHttpResponse(address: String, requestBody: RequestBody, callback: Callback) {
        val client = OkHttpClient()
        //JSONObject这里是要提交的数据部分
        val request= Request.Builder().url(address).post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}


/*
object HttpUtil {
    //提供一个静态方法，当别的地方需要发起网络请求时，简单的调用这个方法即可
    //请求实例
    //OKHttp请求
    //callback是okhttp自带的回调接口，这里写的是使用GET方式获取服务器数据
    fun sendOkHttpRequest(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(address)
            .build()
        //enqueue方法内部已经帮助我们开启好了线程，最终的结果会回调到callback中
        client.newCall(request).enqueue(callback)
    }

    //使用POST方式向服务器提交数据并获取返回提示数据
    fun sendOkHttpResponse(
        address: String,
        requestBody: RequestBody, callback: Callback
    ) {
        val client = OkHttpClient()
        //JSONObject这里是要提交的数据部分
        val request: Request = Request.Builder()
            .url(address)
            .post(requestBody)
            .build()
        client.newCall(request).enqueue(callback)
    }

    //使用DELETE方式向服务器提交数据并获取返回提示数据
    fun sendOkHttpDelete(
        address: String, callback: Callback
    ) {
        val client = OkHttpClient()
        //JSONObject这里是要提交的数据部分
        val request: Request = Request.Builder()
            .url(address)
            .delete()
            .build()
        client.newCall(request).enqueue(callback)
    }

    //使用PUT方式向服务器提交数据并获取返回提示数据
    fun sendOkHttpPUT(
        address: String,
        requestBody: RequestBody, callback: Callback
    ) {
        val client = OkHttpClient()
        //JSONObject这里是要提交的数据部分
        val request: Request = Request.Builder()
            .url(address)
            .put(requestBody)
            .build()
        client.newCall(request).enqueue(callback)
    }
}*/
