package com.ygfh.doctor.ui.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.tencent.trtc.TRTCCloud
import com.fh.cplib.utils.RouteUrl
import com.ygfh.doctor.R
import com.ygfh.doctor.av.TRTCCloudListenerImpl
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:22
 * Description: 登录页面
 */

@Route(path = RouteUrl.login)
class LoginActivity1 : BaseActivity() {
    private val REQ_PERMISSION_CODE = 0x1000
    private lateinit var trtcListener : TRTCCloudListenerImpl
    private var trtcCloud: TRTCCloud? = null
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        // 申请动态权限
        checkPermission()
//        trtcListener = TRTCCloudListenerImpl(this)
        trtcCloud = TRTCCloud.sharedInstance(this)
        trtcCloud!!.setListener(trtcListener)
        btn.setOnClickListener {

        }
    }
    private fun startJoinRoom() {
        var roomId = 123
        try {
            roomId = Integer.valueOf(19674)
        } catch (e: Exception) {
            Toast.makeText(this, "请输入有效的房间号", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = "337290"
        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(this, "请输入有效的用户名", Toast.LENGTH_SHORT).show()
            return
        }


        startJoinRoomInternal(roomId, userId)
    }


    /**
     * Function: 读取用户输入，并创建（或加入）音视频房间
     *
     *
     * 此段示例代码最主要的作用是组装 TRTC SDK 进房所需的 TRTCParams
     *
     *
     * TRTCParams.sdkAppId => 可以在腾讯云实时音视频控制台（https://console.cloud.tencent.com/rav）获取
     * TRTCParams.userId   => 此处即用户输入的用户名，它是一个字符串
     * TRTCParams.roomId   => 此处即用户输入的音视频房间号，比如 125
     * TRTCParams.userSig  => 此处示例代码展示了本地签发 userSig 的示例。
     * 目前 Demo 为了方便您接入，使用的是本地签发 sig 的方式，您的项目上线，务必要保证将签发逻辑转移到服务端，否者会出现 key 被盗用，流量盗用的风险。
     *
     *
     * 参考文档：https://cloud.tencent.com/document/product/647/17275
     */
    private fun startJoinRoomInternal(roomId: Int, userId: String) {
        /*val intent = Intent(this, TRTCMainActivity::class.java)
        // sdkAppId 和 userSig
        // 【*****】目前 Demo 为了方便您接入，使用的是本地签发 sig 的方式，您的项目上线，务必要保证将签发逻辑转移到服务端，否者会出现 key 被盗用，流量盗用的风险。
        // 【*****】目前 Demo 为了方便您接入，使用的是本地签发 sig 的方式，您的项目上线，务必要保证将签发逻辑转移到服务端，否者会出现 key 被盗用，流量盗用的风险。
        // 【*****】目前 Demo 为了方便您接入，使用的是本地签发 sig 的方式，您的项目上线，务必要保证将签发逻辑转移到服务端，否者会出现 key 被盗用，流量盗用的风险。
        val sdkAppId = GenerateTestUserSig.SDKAPPID
        val userSig = GenerateTestUserSig.genTestUserSig(userId)
        intent.putExtra(TRTCMainActivity.KEY_SDK_APP_ID, sdkAppId)
        intent.putExtra(TRTCMainActivity.KEY_USER_SIG, userSig)

        // roomId userId
        intent.putExtra(TRTCMainActivity.KEY_ROOM_ID, roomId)
        intent.putExtra(TRTCMainActivity.KEY_USER_ID, userId)

        saveUserInfo(roomId.toString(), userId)

        // 模式选择
        if (mCurrentType == 1) {// 直播低延时大房间
            intent.putExtra(TRTCMainActivity.KEY_APP_SCENE, TRTCCloudDef.TRTC_APP_SCENE_LIVE)
            val rbAnchor = findViewById<View>(R.id.rb_anchor) as RadioButton
            intent.putExtra(TRTCMainActivity.KEY_ROLE, if (rbAnchor.isChecked) TRTCCloudDef.TRTCRoleAnchor else TRTCCloudDef.TRTCRoleAudience)
        } else {// 视频通话
            intent.putExtra(TRTCMainActivity.KEY_APP_SCENE, TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL)
        }

        // 是否使用外部采集
        var isCustomVideoCapture = (findViewById<View>(R.id.rb_video_file) as RadioButton).isChecked
        if (TextUtils.isEmpty(mVideoFile)) isCustomVideoCapture = false
        intent.putExtra(TRTCMainActivity.KEY_CUSTOM_CAPTURE, isCustomVideoCapture)
        intent.putExtra(TRTCMainActivity.KEY_VIDEO_FILE_PATH, mVideoFile)

        startActivity(intent)*/
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    //////////////////////////////////    动态权限申请   ////////////////////////////////////////

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = ArrayList<String>()
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA)
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO)
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (permissions.size != 0) {
                ActivityCompat.requestPermissions(this,
                        permissions.toTypedArray(),
                        REQ_PERMISSION_CODE)
                return false
            }
        }

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        trtcCloud!!.setListener(null)
        trtcCloud = null
        TRTCCloud.destroySharedInstance()
    }

}
