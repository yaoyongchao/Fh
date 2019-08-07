package com.lottchina.baselib.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.lottchina.baselib.R

/**
 * Author: Austin
 * Date: 19-5-8
 * Description:
 */
abstract class BaseDialogFragment: DialogFragment() {
    public lateinit var dailogView:View
    open var cancelTouchOutside = false
    open var dialogWidth = 0
    var backgroudDrawable = 0
    var cancelableOnKeyBack = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//去除Title布局
        dialog.setCancelable(false)//取消
        dialog.setCanceledOnTouchOutside(cancelTouchOutside)//点击外部不消失
        dialog.window.setBackgroundDrawableResource(if (backgroudDrawable == 0)R.drawable.bg_round_white else backgroudDrawable)
        dailogView = inflater.inflate(layoutId(), container, false)

        initVies()

        return dailogView

    }

    abstract fun initVies()

    public abstract fun layoutId() : Int

//    fun setCancelableOnKeyBack(bool:Boolean) {
//        this.cancelableOnKeyBack = bool
//    }

    open fun setBackGroundDrawableResource(resId: Int) {
        this.backgroudDrawable = resId
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

//        lp.width = width - resources.getDimensionPixelSize(R.dimen.dialog_margin)
        lp.width = width - dialogWidth
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
//        lp.alpha = 0.5f
        dialogWindow.attributes = lp
        if (!cancelableOnKeyBack) {
            dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
                if (keyCode ==  KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) true else false
            })
        }




    }

}