package com.lottchina.baselib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lottchina.baselib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Austin
 * Date: 19-5-8
 * Description:
 */
public class DropdownPopupWindow extends PopupWindow{
    private Context mContext;
    private View view;
    private LayoutInflater inflater;
    private List<String> list = new ArrayList<>();
    private PopAdapter adapter;
    private TextView tv_dissmiss,tv_bottom;
    private RecyclerView rv_drop;
    private OnItemClickListener onItemClickListener;
    private HashMap<Integer,Boolean> mapSelected;


    public DropdownPopupWindow(Context context) {
        this(context,null);
    }

    public DropdownPopupWindow(Context context, AttributeSet attrs) {
        this(context, null,0);
    }

    public DropdownPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViews();
    }

    private void initViews() {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_drop_down_popup,null);

        tv_dissmiss = view.findViewById(R.id.tv_dissmiss);
        Log.e("aa","diss"+ tv_dissmiss);
        tv_dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rv_drop = view.findViewById(R.id.rv_drop);
        tv_bottom = view.findViewById(R.id.tv_bottom);
        setAnimationStyle(R.style.popAnim);
        initRv();



        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);

        /**
         * 设置点击外部可以消失
         */

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
                    return true;
                }
                //不是点击外部
                return false;
            }
        });

        

        setContentView(view);

    }

    private void initRv() {
        /*RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,list.size());
        rv_drop.setLayoutManager(layoutManager);
        rv_drop.setAdapter(adapter);*/

    }

    public void setTvBottomVisibility(int visibility) {
        if (tv_bottom!=null)
            tv_bottom.setVisibility(visibility);
    }

    public void setList(ArrayList<String> l) {
        this.list.clear();
        list.addAll(l);
        mapSelected = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            mapSelected.put(i,false);
            if (i == 0)
                mapSelected.put(i,true);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rv_drop.setLayoutManager(layoutManager);
        rv_drop.addItemDecoration(new MyDivider(mContext, DividerItemDecoration.VERTICAL));
        adapter = new PopAdapter();
        rv_drop.setAdapter(adapter);

    }

    class PopAdapter extends RecyclerView.Adapter<PopAdapter.MyViewHolder> {


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.item_drop_down, viewGroup,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
            holder.tv_pop.setText(list.get(i));
            holder.tv_pop.setTextColor(mContext.getResources().getColor(mapSelected.get(i) ? R.color.txtBlue : R.color.colorTxtDefault));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int n = 0; n < list.size(); n++) {
                        mapSelected.put(n,false);
                        if (i == n)
                            mapSelected.put(n,true);
                    }
                    notifyDataSetChanged();
                    if (onItemClickListener!=null)
                        onItemClickListener.onItemClick(i);
                    dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_pop;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_pop = (TextView) itemView.findViewById(R.id.tv_pop);
            }
        }
    }


    interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
