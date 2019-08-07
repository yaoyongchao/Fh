package com.lottchina.xdbao.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.lottchina.baselib.adapter.BaseAdapter
import com.lottchina.baselib.widget.MyDivider
import com.lottchina.cplib.data.bean.Terminal
import com.lottchina.xdbao.R
import kotlinx.android.synthetic.main.dialog_fragment_terminals.view.*
import kotlinx.android.synthetic.main.item_select_terminals.view.*

/**
 * Author: Austin
 * Date: 19-4-22
 * Description: 终端账号选择框
 */
@SuppressLint("ValidFragment")
class TermialsDialogFragment() : DialogFragment() {
    var adapter: BaseAdapter<Terminal>? = null
    private var terminalsList = mutableListOf<Terminal>()
    private var mapSelected = mutableMapOf<Int,Boolean>()
    private lateinit var myOnItemClickListener : MyOnItemClickListener
    constructor(list: List<Terminal>) : this() {
        this.terminalsList.addAll(list)
        for (i in terminalsList.indices) {
            mapSelected.put(i,if (i == 0) true else false)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//去除Title布局
        dialog.setCancelable(false)//取消
        dialog.setCanceledOnTouchOutside(false)//点击外部不消失
        dialog.window.setBackgroundDrawableResource(R.drawable.bg_round_white)

        var view = inflater.inflate(R.layout.dialog_fragment_terminals, container, false)
        view.tv_title.text = "选择登录账号"
        view.img_delete.setOnClickListener {
            dismiss()
        }

        initViews(view)

        return view;
    }

    override fun onStart() {
        super.onStart()
        //获取手机屏幕的长和宽
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width = wm.defaultDisplay.width
        val height = wm.defaultDisplay.height
        //这个设置宽高的必须放在onstart方法里，不能放oncreateview里面
        var dialogWindow = dialog.window
        var lp = dialogWindow.attributes
        dialogWindow.setGravity(Gravity.CENTER)//布局文件居中
        //dialogWindow.setLayout(lp.MATCH_PARENT, lp.WRAP_CONTENT);// 为了让对话框宽度铺满
        //alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明，自身不可见。
        //设置弹窗的宽度，

        lp.width = width - resources.getDimensionPixelSize(R.dimen.dialog_margin)
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
//        lp.alpha = 0.5f
        dialogWindow.attributes = lp
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if (keyCode ==  KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) true else false
        })


    }

    private fun initViews(view: View) {
        view?.rv?.layoutManager = LinearLayoutManager(context)
        adapter = BaseAdapter(R.layout.item_select_terminals,terminalsList){itemView: View, bean: Terminal, i: Int ->
            itemView.tv_code.text = bean.code
            itemView.tv_code.setTextColor(if (mapSelected?.get(i)!!) resources.getColor(R.color.txtBlue) else resources.getColor(R.color.colorTxtDefault) )

            itemView.setOnClickListener {
                for (n in terminalsList.indices) {
                    mapSelected.put(n,if (i == n) true else false)
                }

                adapter?.notifyDataSetChanged()
                myOnItemClickListener?.onItemClick(itemView,i)
                dismiss()
            }

        }
        view?.rv?.addItemDecoration(MyDivider(this!!.context!!,DividerItemDecoration.VERTICAL))
        view?.rv?.adapter = adapter
        adapter?.notifyDataSetChanged()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return super.onCreateDialog(savedInstanceState)
    }

    interface MyOnItemClickListener{
        fun onItemClick(v:View,i:Int)
    }

    fun setMyOnItemClickListener(myOnItemClickListener: MyOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}