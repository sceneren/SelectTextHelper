package com.xiaoguang.selecttextview

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoguang.selecttext.SelectTextHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 绑定表情 方法一：
        // SelectTextHelper.putEmojiMap("\\[笑脸\\]", R.drawable.emoji_00)
        // SelectTextHelper.putEmojiMap("\\[瘪嘴\\]", R.drawable.emoji_01)
        // SelectTextHelper.putEmojiMap("\\[色\\]", R.drawable.emoji_02)
        // SelectTextHelper.putEmojiMap("\\[瞪大眼\\]", R.drawable.emoji_03)
        // SelectTextHelper.putEmojiMap("\\[酷\\]", R.drawable.emoji_04)
        // 绑定表情 方法二：
        val emojiMap: MutableMap<String, Int> = HashMap()
        emojiMap["\\[笑脸\\]"] = R.drawable.emoji_00
        emojiMap["\\[瘪嘴\\]"] = R.drawable.emoji_01
        emojiMap["\\[色\\]"] = R.drawable.emoji_02
        emojiMap["\\[瞪大眼\\]"] = R.drawable.emoji_03
        emojiMap["\\[酷\\]"] = R.drawable.emoji_04
        SelectTextHelper.putAllEmojiMap(emojiMap)

        // todo 一：展示在列表中自由复制、双击查看文本
        val rvMsg = findViewById<RecyclerView>(R.id.rv_msg)
        rvMsg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val mList: MutableList<MsgBean> = ArrayList()
        addList(mList)
        val adapter = MsgAdapter(this, mList)
        rvMsg.adapter = adapter

        // todo 二：展示查看文本
        // val dialog = SelectTextDialog(this, TEXT9)
        // dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        SelectTextEventBus.instance.unregister()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            SelectTextEventBus.instance.dispatch(SelectTextEvent("dismissAllPopDelayed"))
        }
        return super.dispatchTouchEvent(ev)
    }

    // 1：文本、2：图片、3：链接
    private fun addList(mList: MutableList<MsgBean>) {
        mList.add(MsgBean(1, true, TEXT1))
        mList.add(MsgBean(2, false, IMG1))
        mList.add(MsgBean(3, true, LINK1))
        mList.add(MsgBean(1, false, TEXT2))
        mList.add(MsgBean(1, true, TEXT3))
        mList.add(MsgBean(1, false, TEXT4))
        mList.add(MsgBean(1, true, TEXT5))
        mList.add(MsgBean(1, false, TEXT6))
        mList.add(MsgBean(1, true, TEXT7))
        mList.add(MsgBean(1, false, TEXT8))
        mList.add(MsgBean(1, true, TEXT9))
    }

    companion object {
        var TEXT1 = "www.baidu.com Android 13 将支持在锁屏界面添加QR扫描器"
        var TEXT2 = "https://github.com/ITxiaoguang\n作者QQ：929842234\n点点star"
        var TEXT3 = "Emoji表情：\uD83D\uDE04\uD83D\uDE03\uD83D\uDE00\uD83D\uDE0A\uD83D\uDE09\uD83D\uDE0D\uD83D\uDE18\uD83D\uDCAF\uD83D\uDD18\uD83D\uDD17\uD83D\uDD31"
        var TEXT4 = "ImageSpan表情：[笑脸][笑脸][瞪大眼][瞪大眼][色][色][瘪嘴][瘪嘴][酷][酷]"
        var TEXT5 = "Android 13 将拥有更多改进，比如为单个 App 指定语言、蓝牙 LE Audio。"
        var TEXT6 = "22022年5月11日-5月12日在线上举行谷歌I/O大会，这次大会以线上的形式和广大开发者见面，带来全新一代Android 13操作系统，Android 13开发者预览版已经上线，谷歌Pixel系列机型可以尝鲜这一操作系统。\n" +
                "2022年7月，Android 13测试版推送后，接下来就是正式版了，所以也可以叫它是最终测试版，对于正式版的到来，OPPO、vivo、realme、一加、小米等厂商都表示会第一时间跟进推出相应的版本"
        var TEXT7 = "Beta 版 \uD83D\uDD14\uD83D\uDD15✡✝\uD83D\uDD2F\uD83D\uDCDB\uD83D\uDD30\uD83D\uDD31⭕✅☑✔✖❌❎➕➖➗➰➿〽✳✴❇‼⁉❓❔❕❗©®™\uD83C\uDFA6\uD83D\uDD05\uD83D\uDD06\uD83D\uDCAF\uD83D\uDD20\uD83D\uDD21\uD83D\uDD22\uD83D\uDD23\uD83D\uDD24\uD83C\uDD70\uD83C\uDD8E\uD83C\uDD71\uD83C\uDD91\uD83C\uDD92\uD83C\uDD93ℹ\uD83C\uDD94Ⓜ\uD83C\uDD95\uD83C\uDD96\uD83C\uDD7E\uD83C\uDD97\uD83C\uDD7F\uD83C\uDD98\uD83C\uDD99\uD83C\uDD9A\uD83C\uDE01\uD83C\uDE02\uD83C\uDE37\uD83C\uDE362 现已推出，其中包含最新功能和变更，供您在应用中试用。如需开始使用，请先获取 Beta 版并更新您的工具。Android 12 Beta 版现已面向用户和开发者推出，请务必测试您的应用是否兼容，并根据需要发布任何相关更新。欢迎试用新功能，并通过我们的问题跟踪器分享反馈。"
        var TEXT8 = "提供更\uD83C\uDE35\uD83D\uDD34\uD83D\uDFE0\uD83D\uDFE1\uD83D\uDFE2\uD83D\uDD35\uD83D\uDFE3\uD83D\uDFE4⚫⚪\uD83D\uDFE5\uD83D\uDFE7\uD83D\uDFE8\uD83D\uDFE9\uD83D\uDFE6\uD83D\uDFEA\uD83D\uDFEB⬛⬜◼️◻️◾◽▪️▫️\uD83D\uDD36\uD83D\uDD37\uD83D\uDD38\uD83D\uDD39\uD83D\uDD3A\uD83D\uDD3B\uD83D\uDCA0\uD83D\uDD18\uD83D\uDD33\uD83D\uDD32\uD83C\uDFC1\uD83D\uDEA9\uD83C\uDF8C\uD83C\uDFF4\uD83C\uDFF3️\uD83C\uDFF3️\u200D\uD83C\uDF08\uD83C\uDFF3️\u200D⚧️\uD83C\uDFF4\u200D☠️\uD83C\uDDE6\uD83C\uDDE8\uD83C\uDDE6\uD83C\uDDE9\uD83C\uDDE6\uD83C\uDDEA\uD83C\uDDE6\uD83C\uDDEB\uD83C\uDDE6\uD83C\uDDEC\uD83C\uDDE6\uD83C\uDDEE\uD83C\uDDE6\uD83C\uDDF1\uD83C\uDDE6\uD83C\uDDF2\uD83C\uDDE6\uD83C\uDDF4\uD83C\uDDE6\uD83C\uDDF6\uD83C\uDDE6\uD83C\uDDF7\uD83C\uDDE6\uD83C\uDDF8\uD83C\uDDE6\uD83C\uDDF9\uD83C\uDDE6\uD83C\uDDFA\uD83C\uDDE6\uD83C\uDDFC\uD83C\uDDE6\uD83C\uDDFD\uD83C\uDDE6\uD83C\uDDFF\uD83C\uDDE7\uD83C\uDDE6\uD83C\uDDE7\uD83C\uDDE7\uD83C\uDDE7\uD83C\uDDE9\uD83C\uDDE7\uD83C\uDDEA\uD83C\uDDE7\uD83C\uDDEB\uD83C\uDDE7\uD83C\uDDEC\uD83C\uDDE7\uD83C\uDDED\uD83C\uDDE7\uD83C\uDDEE\uD83C\uDDE7\uD83C\uDDEF\uD83C\uDDE7\uD83C\uDDF1\uD83C\uDDE7\uD83C\uDDF2\uD83C\uDDE7\uD83C\uDDF3\uD83C\uDDE7\uD83C\uDDF4\uD83C\uDDE7\uD83C\uDDF6\uD83C\uDDE7\uD83C\uDDF7\uD83C\uDDE7\uD83C\uDDF8\uD83C\uDDE7\uD83C\uDDF9\uD83C\uDDE7\uD83C\uDDFB\uD83C\uDDE7\uD83C\uDDFC\uD83C\uDDE7\uD83C\uDDFE\uD83C\uDDE7\uD83C\uDDFF\uD83C\uDDE8\uD83C\uDDE6\uD83C\uDDE8\uD83C\uDDE8\uD83C\uDDE8\uD83C\uDDE9\uD83C\uDDE8\uD83C\uDDEB\uD83C\uDDE8\uD83C\uDDEC\uD83C\uDDE8\uD83C\uDDED\uD83C\uDDE8\uD83C\uDDEE\uD83C\uDDE8\uD83C\uDDF0\uD83C\uDDE8\uD83C\uDDF1\uD83C\uDDE8\uD83C\uDDF2\uD83C\uDDE8\uD83C\uDDF3\uD83C\uDDE8\uD83C\uDDF4\uD83C\uDDE8\uD83C\uDDF5\uD83C\uDDE8\uD83C\uDDF7\uD83C\uDDE8\uD83C\uDDFA\uD83C\uDDE8\uD83C\uDDFB\uD83C\uDDE8\uD83C\uDDFC\uD83C\uDDE8\uD83C\uDDFD\uD83C\uDDE8\uD83C\uDDFE\uD83C\uDDE8\uD83C\uDDFF\uD83C\uDDE9\uD83C\uDDEA\uD83C\uDDE9\uD83C\uDDEC\uD83C\uDDE9\uD83C\uDDEF\uD83C\uDDE9\uD83C\uDDF0\uD83C\uDDE9\uD83C\uDDF2\uD83C\uDDE9\uD83C\uDDF4\uD83C\uDDE9\uD83C\uDDFF\uD83C\uDDEA\uD83C\uDDE6\uD83C\uDDEA\uD83C\uDDE8\uD83C\uDDEA\uD83C\uDDEA\uD83C\uDDEA\uD83C\uDDEC\uD83C\uDDEA\uD83C\uDDED\uD83C\uDDEA\uD83C\uDDF7\uD83C\uDDEA\uD83C\uDDF8\uD83C\uDDEA\uD83C\uDDF9\uD83C\uDDEA\uD83C\uDDFA\uD83C\uDDEB\uD83C\uDDEE\uD83C\uDDEB\uD83C\uDDEF\uD83C\uDDEB\uD83C\uDDF0\uD83C\uDDEB\uD83C\uDDF2\uD83C\uDDEB\uD83C\uDDF4\uD83C\uDDEB\uD83C\uDDF7\uD83C\uDDEC\uD83C\uDDE6\uD83C\uDDEC\uD83C\uDDE7\uD83C\uDDEC\uD83C\uDDE9\uD83C\uDDEC\uD83C\uDDEA\uD83C\uDDEC\uD83C\uDDEB\uD83C\uDDEC\uD83C\uDDEC\uD83C\uDDEC\uD83C\uDDED\uD83C\uDDEC\uD83C\uDDEE\uD83C\uDDEC\uD83C\uDDF1\uD83C\uDDEC\uD83C\uDDF2\uD83C\uDDEC\uD83C\uDDF3\uD83C\uDDEC\uD83C\uDDF5\uD83C\uDDEC\uD83C\uDDF6\uD83C\uDDEC\uD83C\uDDF7\uD83C\uDDEC\uD83C\uDDF8\uD83C\uDDEC\uD83C\uDDF9\uD83C\uDDEC\uD83C\uDDFA\uD83C\uDDEC\uD83C\uDDFC\uD83C\uDDEC\uD83C\uDDFE\uD83C\uDDED\uD83C\uDDF0\uD83C\uDDED\uD83C\uDDF2\uD83C\uDDED\uD83C\uDDF3\uD83C\uDDED\uD83C\uDDF7\uD83C\uDDED\uD83C\uDDF9\uD83C\uDDED\uD83C\uDDFA\uD83C\uDDEE\uD83C\uDDE8\uD83C\uDDEE\uD83C\uDDE9\uD83C\uDDEE\uD83C\uDDEA\uD83C\uDDEE\uD83C\uDDF1\uD83C\uDDEE\uD83C\uDDF2\uD83C\uDDEE\uD83C\uDDF3\uD83C\uDDEE\uD83C\uDDF4\uD83C\uDDEE\uD83C\uDDF6\uD83C\uDDEE\uD83C\uDDF7\uD83C\uDDEE\uD83C\uDDF8\uD83C\uDDEE\uD83C\uDDF9\uD83C\uDDEF\uD83C\uDDEA\uD83C\uDDEF\uD83C\uDDF2\uD83C\uDDEF\uD83C\uDDF4\uD83C\uDDEF\uD83C\uDDF5\uD83C\uDDF0\uD83C\uDDEA\uD83C\uDDF0\uD83C\uDDEC\uD83C\uDDF0\uD83C\uDDED\uD83C\uDDF0\uD83C\uDDEE\uD83C\uDDF0\uD83C\uDDF2\uD83C\uDDF0\uD83C\uDDF3\uD83C\uDDF0\uD83C\uDDF5\uD83C\uDDF0\uD83C\uDDF7\uD83C\uDDF0\uD83C\uDDFC\uD83C\uDDF0\uD83C\uDDFE\uD83C\uDDF0\uD83C\uDDFF\uD83C\uDDF1\uD83C\uDDE6\uD83C\uDDF1\uD83C\uDDE7\uD83C\uDDF1\uD83C\uDDE8\uD83C\uDDF1\uD83C\uDDEE\uD83C\uDDF1\uD83C\uDDF0\uD83C\uDDF1\uD83C\uDDF7\uD83C\uDDF1\uD83C\uDDF8\uD83C\uDDF1\uD83C\uDDF9\uD83C\uDDF1\uD83C\uDDFA\uD83C\uDDF1\uD83C\uDDFB\uD83C\uDDF1\uD83C\uDDFE\uD83C\uDDF2\uD83C\uDDE6\uD83C\uDDF2\uD83C\uDDE8\uD83C\uDDF2\uD83C\uDDE9\uD83C\uDDF2\uD83C\uDDEA\uD83C\uDDF2\uD83C\uDDEB\uD83C\uDDF2\uD83C\uDDEC\uD83C\uDDF2\uD83C\uDDED\uD83C\uDDF2\uD83C\uDDF0\uD83C\uDDF2\uD83C\uDDF1\uD83C\uDDF2\uD83C\uDDF2\uD83C\uDDF2\uD83C\uDDF3\uD83C\uDDF2\uD83C\uDDF4\uD83C\uDDF2\uD83C\uDDF5\uD83C\uDDF2\uD83C\uDDF6\uD83C\uDDF2\uD83C\uDDF7\uD83C\uDDF2\uD83C\uDDF8\uD83C\uDDF2\uD83C\uDDF9\uD83C\uDDF2\uD83C\uDDFA\uD83C\uDDF2\uD83C\uDDFB\uD83C\uDDF2\uD83C\uDDFC\uD83C\uDDF2\uD83C\uDDFD\uD83C\uDDF2\uD83C\uDDFE\uD83C\uDDF2\uD83C\uDDFF\uD83C\uDDF3\uD83C\uDDE6\uD83C\uDDF3\uD83C\uDDE8\uD83C\uDDF3\uD83C\uDDEA\uD83C\uDDF3\uD83C\uDDEB\uD83C\uDDF3\uD83C\uDDEC\uD83C\uDDF3\uD83C\uDDEE\uD83C\uDDF3\uD83C\uDDF1\uD83C\uDDF3\uD83C\uDDF4\uD83C\uDDF3\uD83C\uDDF5\uD83C\uDDF3\uD83C\uDDF7\uD83C\uDDF3\uD83C\uDDFA\uD83C\uDDF3\uD83C\uDDFF\uD83C\uDDF4\uD83C\uDDF2\uD83C\uDDF5\uD83C\uDDE6\uD83C\uDDF5\uD83C\uDDEA\uD83C\uDDF5\uD83C\uDDEB\uD83C\uDDF5\uD83C\uDDEC\uD83C\uDDF5\uD83C\uDDED\uD83C\uDDF5\uD83C\uDDF0\uD83C\uDDF5\uD83C\uDDF1\uD83C\uDDF5\uD83C\uDDF2\uD83C\uDDF5\uD83C\uDDF3\uD83C\uDDF5\uD83C\uDDF7\uD83C\uDDF5\uD83C\uDDF8\uD83C\uDDF5\uD83C\uDDF9\uD83C\uDDF5\uD83C\uDDFC\uD83C\uDDF5\uD83C\uDDFE\uD83C\uDDF6\uD83C\uDDE6\uD83C\uDDF7\uD83C\uDDEA\uD83C\uDDF7\uD83C\uDDF4\uD83C\uDDF7\uD83C\uDDF8\uD83C\uDDF7\uD83C\uDDFA\uD83C\uDDF7\uD83C\uDDFC\uD83C\uDDF8\uD83C\uDDE6\uD83C\uDDF8\uD83C\uDDE7\uD83C\uDDF8\uD83C\uDDE8\uD83C\uDDF8\uD83C\uDDE9\uD83C\uDDF8\uD83C\uDDEA\uD83C\uDDF8\uD83C\uDDEC\uD83C\uDDF8\uD83C\uDDED\uD83C\uDDF8\uD83C\uDDEE\uD83C\uDDF8\uD83C\uDDEF\uD83C\uDDF8\uD83C\uDDF0\uD83C\uDDF8\uD83C\uDDF1\uD83C\uDDF8\uD83C\uDDF2\uD83C\uDDF8\uD83C\uDDF3\uD83C\uDDF8\uD83C\uDDF4\uD83C\uDDF8\uD83C\uDDF7\uD83C\uDDF8\uD83C\uDDF8\uD83C\uDDF8\uD83C\uDDF9\uD83C\uDDF8\uD83C\uDDFB\uD83C\uDDF8\uD83C\uDDFD\uD83C\uDDF8\uD83C\uDDFE\uD83C\uDDF8\uD83C\uDDFF\uD83C\uDDF9\uD83C\uDDE6\uD83C\uDDF9\uD83C\uDDE8\uD83C\uDDF9\uD83C\uDDE9\uD83C\uDDF9\uD83C\uDDEB\uD83C\uDDF9\uD83C\uDDEC\uD83C\uDDF9\uD83C\uDDED\uD83C\uDDF9\uD83C\uDDEF\uD83C\uDDF9\uD83C\uDDF0\uD83C\uDDF9\uD83C\uDDF1\uD83C\uDDF9\uD83C\uDDF2\uD83C\uDDF9\uD83C\uDDF3\uD83C\uDDF9\uD83C\uDDF4\uD83C\uDDF9\uD83C\uDDF7\uD83C\uDDF9\uD83C\uDDF9\uD83C\uDDF9\uD83C\uDDFB\uD83C\uDDF9\uD83C\uDDFC\uD83C\uDDF9\uD83C\uDDFF\uD83C\uDDFA\uD83C\uDDE6\uD83C\uDDFA\uD83C\uDDEC\uD83C\uDDFA\uD83C\uDDF2\uD83C\uDDFA\uD83C\uDDF3\uD83C\uDDFA\uD83C\uDDF8\uD83C\uDDFA\uD83C\uDDFE\uD83C\uDDFA\uD83C\uDDFF\uD83C\uDDFB\uD83C\uDDE6\uD83C\uDDFB\uD83C\uDDE8\uD83C\uDDFB\uD83C\uDDEA\uD83C\uDDFB\uD83C\uDDEC\uD83C\uDDFB\uD83C\uDDEE\uD83C\uDDFB\uD83C\uDDF3\uD83C\uDDFB\uD83C\uDDFA\uD83C\uDDFC\uD83C\uDDEB\uD83C\uDDFC\uD83C\uDDF8\uD83C\uDDFD\uD83C\uDDF0\uD83C\uDDFE\uD83C\uDDEA\uD83C\uDDFE\uD83C\uDDF9\uD83C\uDDFF\uD83C\uDDE6\uD83C\uDDFF\uD83C\uDDF2\uD83C\uDDFF\uD83C\uDDFC\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC73\uDB40\uDC63\uDB40\uDC74\uDB40\uDC7F\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC77\uDB40\uDC6C\uDB40\uDC73\uDB40\uDC7F\uD83C\uDFF4\uDB40\uDC75\uDB40\uDC73\uDB40\uDC74\uDB40\uDC78\uDB40\uDC7F\uDB40\uDC75\uDB40\uDC73\uDB40\uDC74\uDB40\uDC78\uDB40\uDC7F \uDB40\uDC75\uDB40\uDC73\uDB40\uDC74\uDB40\uDC78\uDB40\uDC7F流畅的体验"
        var TEXT9 = "Android 13 为通过 Wi-Fi 管理设备与附近接入点连接的应用程序引入了 NEARBY_WIFI_DEVICES 运行时权限（NEARBY_DEVICES 权限组的一部分）。调用许多常用的 Wi-Fi API 的应用程序将需要新的权限，并使应用程序能够通过 Wi-Fi 发现和连接附近的设备，而不需要位置权限。[酷]"
        var IMG1: String? = null
        var LINK1 = "https://developer.android.google.cn/"
    }
}