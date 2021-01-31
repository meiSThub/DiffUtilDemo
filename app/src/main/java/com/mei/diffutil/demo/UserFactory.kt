package com.mei.diffutil.demo

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/31
 */
object UserFactory {

    fun createUserList(): List<User> {
        var list = ArrayList<User>()
        for (i in 0 until 10) {
            var user = User(i, "姓名：${i + 1}", (i + 1) * 5)
            list.add(user)
        }
        return list
    }

    fun updateUserList(): List<User> {
        var list = ArrayList<User>()
        for (i in 0..10) {
            var user = User(i, "姓名：${i + 1}", (i + 1) * 5)
            if (i % 5 == 0) {
                user.name = "张三：${i + 1}"
            }
            if (i % 3 == 0) {
                user.age = 1000
            }
            list.add(user)
        }
        return list
    }
}
