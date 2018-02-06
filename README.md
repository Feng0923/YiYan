一言网 Api 接口说明
===
##1、说明

一言网(Hitokoto.cn)创立于2016年，隶属于萌创Team，目前网站主要提供一句话服务。

动漫也好、小说也好、网络也好，不论在哪里，我们总会看到有那么一两个句子能穿透你的心。我们把这些句子汇聚起来，形成一言网络，以传递更多的感动。如果可以，我们希望我们没有停止服务的那一天。

简单来说，一言指的就是一句话，可以是动漫中的台词，也可以是网络上的各种小段子。
或是感动，或是开心，有或是单纯的回忆。来到这里，留下你所喜欢的那一句句话，与大家分享，这就是一言存在的目的。
*:本段文本源自hitokoto.us
##2、Api

请求地址：
HTTP : http://api.hitokoto.cn/
SSL(推荐) : https://sslapi.hitokoto.cn/
##3、参数

| 参数名称 | 类型 |	描述|
|--|:--:|--:|
c 	可选 	Cat，即类型。提交不同的参数代表不同的类别，具体：
- a 	Anime - 动画
- b 	Comic – 漫画
- c 	Game – 游戏
- d 	Novel – 小说
- e 	Myself – 原创
- f 	Internet – 来自网络
- g 	Other – 其他

其他不存在参数 	任意类型随机取得<br/>
encode 	可选
- text 	返回纯净文本
- json 	返回不进行unicode转码的json文本

其他不存在参数 	返回unicode转码的json文本

##4、返回（默认json格式）
返回参数名称 	
- id 	本条一言的id。可以链接到https://hitokoto.cn?id=[id]查看这个一言的完整信息。
- hitokoto 	一言正文。编码方式usc2。使用utf-8。
- type 	类型。请参考第三节参数的表格。
- from 	一言的出处。
- creator 	添加者。
- cearted_at 	添加时间。

注意：如果encode参数为text，那么输出的只有一言正文。
## 5、示例

https://sslapi.hitokoto.cn/（从7种分类中随机抽取）

https://sslapi.hitokoto.cn/?c=b （请求获得一个分类是漫画的句子）

https://sslapi.hitokoto.cn/?c=f&encode=text （请求获得一个来自网络的句子，并以纯文本格式输出） 