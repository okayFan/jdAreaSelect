

Android项目中需要实现一个类似于京东地址选择器的效果, 拿到Ui图的时候，心里想 “嘿嘿，不就京东选择器嘛,网上找找就有了”,结果搜了一下...



结果搜到了很多,但是发现要么是封装的太厉害，有些甚至还需要本地数据库来填充数据，要么就是不够人性化, 想要自定义点其他东西扩展起来太复杂了。于是就决定自己写呗~

分析了下实现思路，感觉用google提供的原生组件足以解决,不用封装过于复杂的自定义view.
效果图如下：
![效果图](https://github.com/okayFan/jdAreaSelect/blob/master/img/20181127181954473.png)

~~~Only需要recyclerview,tablayout,viewpager 就能简单实现咱们的需求,而且后期非常便于拓展 （这谁还不会用~）

其实就是viewpager里面嵌套3个fragment,分别为省市区，通过监听viewpager和tablayout的滑动，来实现一些逻辑的控制，关键点是viewpager是需要控制能否左滑或者右滑，否则用户还没有选择省或市就滑到空白的fragment, 这样体验很不好，当然！不用担心，我已经对viewpager做了封装，小伙伴们可以自如控制~~

一：
       首先当然需要数据源，项目中的数据源要么来自于网络，要么本地写死，我这边已经把从服务器拿来的地区json放到了assets目录下，如下图：

    
![效果图](https://github.com/okayFan/jdAreaSelect/blob/master/img/20181127182951552.png)


二：
      关键是对viewpager的滑动监听来对tablayout的状态变化和viewpager是否能左右滑动的控制，如下图：



三：    
             可以控制左右滑动的viewpager

        ![](https://github.com/okayFan/jdAreaSelect/blob/master/img/2018112718420055.png)




   把关键点写了下，剩下的其实就是一些点击判断的逻辑了，项目中为了方便也用到了一些第三方库比如rxbus来实现组件间通讯，相信大家都能看懂哈,大家可以根据自己的需求来修改~~
