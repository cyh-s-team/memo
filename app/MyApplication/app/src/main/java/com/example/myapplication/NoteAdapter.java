package com.example.myapplication.DIYclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.DIYclass.SQLBean;
import com.example.myapplication.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class NoteAdapter extends BaseAdapter {//继承自数据适配器BaseAdapter
    private List<SQLBean> list;
    private Context context;
    private LayoutInflater inflater;
    private DisplayMetrics dm;

    class Holder {
        public TextView tv_note_id, tv_note_title, tv_note_time,tv_locktype,tv_lock;
        public RelativeLayout ll_bg;
        public ImageView iv_imge, im_datatime;
    }

    public NoteAdapter(List<SQLBean> list, Context context) {//动态装载备忘录信息到首页
        super();
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.note_item, null);
            holder.tv_note_id = (TextView) convertView
                    .findViewById(R.id.tv_note_id);
            holder.tv_locktype= (TextView) convertView
                    .findViewById(R.id.tv_locktype);
            holder.tv_lock= (TextView) convertView
                    .findViewById(R.id.tv_lock);
            holder.tv_note_title = (TextView) convertView
                    .findViewById(R.id.tv_note_title);
            holder.tv_note_time = (TextView) convertView
                    .findViewById(R.id.tv_note_time);
            holder.iv_imge = (ImageView) convertView.findViewById(R.id.iv_imge);
            holder.im_datatime = (ImageView) convertView
                    .findViewById(R.id.im_datatime);
            holder.ll_bg = (RelativeLayout) convertView
                    .findViewById(R.id.ll_bg);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        dm = context.getResources().getDisplayMetrics();//获得系统屏幕信息
        LinearLayout.LayoutParams imagebtn_params = new LinearLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        imagebtn_params.width = dm.widthPixels / 2;//
        imagebtn_params.height = dm.widthPixels / 2;//
        holder.ll_bg.setLayoutParams(imagebtn_params);
        holder.tv_note_id.setText(list.get(position).get_id());
        holder.tv_note_title.setText(list.get(position).getTitle());
        holder.tv_note_time.setText(list.get(position).getTime());
        holder.tv_locktype.setText(list.get(position).getLocktype());
        holder.tv_lock.setText(list.get(position).getLock());


   

}
