# RabbitAndTurtleRaceWithAsynchronousThread 龜兔賽跑App（用非同步執行緒完成）

### 玩法
使用非同步執行緒(Asynchronus Thread)去完成的。

遊戲內容：
按下開始按鈕，兔子和烏龜會開始移動。

烏龜每0.1秒走一步。
兔子每0.1秒走三步，可是有三分之二的機會會偷懶，每偷懶一次就是休息0.3秒。

然後看誰最快到終點。

### 上畫面
![](https://i.imgur.com/xDUkwvt.gif)

### 實作用到的 Feature
1. Thread
2. Coroutines
3. AlertDialog
4. SeekBar
5. Button setOnClickListener


###### tags: `kotlin` `Game` `asynchronous thread` `Thread` `Coroutines`
