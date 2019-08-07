package com.lottchina.xdbao.demo

import java.util.HashMap

/**
 * Author: Austin
 * Date: 19-4-28
 * Description:
 */
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        /*User u = new User();
        u.setAge(0);
        u.setName("张三");
        String str1 = GsonUtil.GsonString(u);
        log(str1);

        User u1 = new User();
        u1.setAge(0);
        String str2 = GsonUtil.GsonString(u1);
        log(str2);*/
        val map = HashMap<String, String>()
        map["1"] = "张三"
        log(map["1"])
        log(map["2"])
        log("111$map")
        log(map["1"])

        object : Thread() {
            override fun run() {
                super.run()
                map["3"] = "李四"
                log("小四 啊--" + map["3"]!!)
            }
        }.start()

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        log(map["3"])


        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for (int i = 0; i < list.size(); i++) {
            log("--" + i);
            if (i == 3)
                i--;
        }*/
    }

    private fun log(str: String?) {
        println(str)
    }
}
