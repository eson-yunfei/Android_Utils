# Android_Utils

[![](https://jitpack.io/v/eson-yunfei/Android_Utils.svg)](https://jitpack.io/#eson-yunfei/Android_Utils)

## Android 常用工具类

### SLog 
日志打印，日志存储、视图化日志的工具栏

* 日志配置

  ```kotlin
  //日志存储路径
  val path :String?= getExternalFilesDir("log")?.path
  SLogManager.sLogManager.setConfig(SLogConfig().config {
  		//线程相关信息，默认不包含
      setIncludeThread(true)
      //最大堆栈信息数量，默认显示3层
      //为0 不显示堆栈相关信息
      setStackTraceDepth(5)
      //写入日志文件，不需要可不写,默认不存储
      //setSaveFile(true,path?:"")
  })
  ```

* 日志界面视图

  在需要显然日志的界面，实现  `IViewPrinter`

  ```kotlin
  class MainActivity : AppCompatActivity(),IViewPrinter<MainActivity> {
  		override fun getActivity(): MainActivity {
          return this
      }
  }
  ```

* 使用

  ```kotlin
  logD("11111") //使用默认tag(SLog)
  logTe("11111") //在类中使用，使用默认tag (T::class.java.simpleName)
  log2w("MainActivity", "1111") //自定义 tag 
  
  //自定义日志打印，只针对此条日志，不影响全局配置
  SLog.log(SLogConfig().config {
                      setIncludeThread(true)
                      setStackTraceDepth(1)
                  }, Log.ERROR, "custom", "custom message")
  ```

#### log

#### share
#### gson
#### permissions



