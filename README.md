# mqImplement
## 发生的问题
项目使用阿里云ons,在多人开发的情况下 会发生 在不同的Jvm中使用相同的 Consumer ID 启动多个 Consumer实例。
这时候阿里云会将消息随机发送给一个消费者，在很大的情况下会该消息的生产者和消费者并不是一个开发者，给开发带来困扰。
## 想要解决的问题
所以决定在本地的环境不使用aliyun的ons，而使用rocket mq。
该项目对两套消息系统做了简单的整合，能稍优美一点的使用该两套mq系统。

## rocket mq 下载
[Apache Rocket MQ](https://github.com/alibaba/rocketmq)的代码里面没有供Windows系统下运行的bat,
不会自己写（捂脸），所以用了[Rocket MQ 3.*](https://github.com/alibaba/RocketMQ/releases)版本，
编译的方式见[幸运小猴子的专栏](http://blog.csdn.net/ruishenh/article/details/22390809)

小提示 在启动mqnamesrv.exe 后 要在控制台中设置NAMESRV_ADDR的环境变量
 ```$xslt
set NAMESRV_ADDR=127.0.0.1:9876
```
然后再启动mqbroker.exe

