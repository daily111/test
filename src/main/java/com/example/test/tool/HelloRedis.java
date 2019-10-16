package com.example.test.tool;

import redis.clients.jedis.Jedis;

public class HelloRedis {

    public static void main(String[] args) {
        try {
            /**以下参数分别填写您的 Redis 实例内网 IP、端口号、实例 ID 和密码*/
            String host = "127.0.0.1";
            int port = 6379;
            String instanceid = "crs-l8m7ydb4";
            String password = "Dj123456";
            //连接Redis
            Jedis jedis = new Jedis(host, port);
            //鉴权
            jedis.auth(instanceid + ":" + password);

            /**接下来可以开始操作 Redis 实例，可以参考 https://github.com/xetorthio/jedis */
            //设置 Key
            jedis.set("redis", "tencent");
            System.out.println("set key redis suc, value is: tencent");
            //获取 Key
            String value = jedis.get("redis");
            System.out.println("get key redis is: " + value);

            //关闭退出
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}