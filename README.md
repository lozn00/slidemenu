

![演示图片地址no](https://github.com/qssq/slidemenu/blob/master/Pictures/1.gif)

### 用法 



gradle
```
 compile 'cn.qssq666:slidemenu:0.2'


```

**布局文件** 
```
<cn.qssq666.slidemenu.LoopVerticalHorizontalPager
  android:id="@+id/viewpager"
  android:layout_width="match_parent"
  android:layout_height="150dp">
  </cn.qssq666.slidemenu.LoopVerticalHorizontalPager>





<cn.qssq666.slidemenu.VerticalLPager
  android:id="@+id/viewpager"
  android:layout_width="match_parent"
  android:layout_height="150dp">
  </cn.qssq666.slidemenu.VerticalLPager>
  
          
          
<cn.qssq666.slidemenu.HorizontalPager
    android:id="@+id/viewpager"
    android:layout_width="match_parent"
    android:layout_height="150dp">
    </cn.qssq666.slidemenu.VerticalLPager>
    
    
    cn.qssq666.slidemenu.HorizontalPager

```
放入2个ViewGroup包裹起来即可即可使用，不懂的朋友可以看看demo.
demo有很多菜单演示。

**功能**

支持水平 垂直滑动翻页 
水平垂直嵌套不冲突，响应点击事件 支持嵌套recyclerview


支持嵌套不需要松手的平滑滑动 和加速度  ，触摸在recyclerview,当recyclerview不可以滑动的时候 那么 见证奇迹的时候到了 ，该 menu滑动了。

项目跑起来后就有很多个垂直测试垂直 水平垂直水平嵌套滚动的activity方便大家测试!


**激情**

一直没有研究触摸，搁置了很久，现在发现逃避问题不是办法的，遇到变态的需求还是求大神，然而大神都是不鸟我的，那么还是自己造轮子了。
我想解决一切基于NestedScrollingParent NestedScrollingChild的控件 ，这样可以适配各种控件的触摸冲突，而且用起来见到直接套进去就好了。

**原理**

第一个-view为 视频区域 第二个 为菜单区域。滑动的是第二个view.第一个view始终在原来的位置。
使用方法简单 直接嵌套起来使用就行了。
利用嵌套滑动解决 触摸recyclerview无法滑动


**存在的问题**

目前verticalPage是已经完美了的，至于菜单关闭还是没关闭的回调功能因为新加了嵌套滑动 ，所以这里还没弄 ，另外水平的也是 。
目前支持嵌套不需要松手的平滑滑动 和加速度  ，触摸在recyclerview,当recyclerview不可以滑动的时候 那么 见证奇迹的时候到了 ，该 menu滑动了。

**点击事件问题**


getViewHorizontalDragRange(View child)返回0 则当点击事件view作为全屏view的时候全部被它强行占有,当返回>0的时候和recyclerview冲突，
我没有什么解决方法，只能推荐朋友用这种方法解决这个问题了。
```
     getFavorLayout().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        addFavor();
                    }
                    return false;
                }
            });
```


**疑问**


本demo还包含了ios加载进度控件 和 循环翻页的东西  

循环翻页的没有加上demo，可以自己测试使用，貌似也没什么大问题 。
我有空加上。


**共同学习 共同进步**

QQ 694886526 http://qssq666.cn
#



		 
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		android:orientation="vertical" android:layout_width="match_parent"
		android:layout_height="match_parent">
		
		<cn.qssq666.slidemenu.HorizontalPager
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:background="#ff00">
		
		<FrameLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent">
		
		<ImageView
		android:layout_width="match_parent"
		
		android:layout_height="match_parent"
		android:background="@drawable/girl1"
		android:tag="最底層view">
		
		/></ImageView>
		
		<TextView
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_gravity="center"
		
		android:text="我是背景层"
		android:textColor="@android:color/white" />
		
		
		</FrameLayout>
		
		<FrameLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:background="#55ff0000">
		
		
		<android.support.v7.widget.RecyclerView
		android:id="@+id/recycler_view_horzontal"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:layout_gravity="top|right"
		android:layout_marginBottom="30dp"
		
		android:layout_marginLeft="50dp"
		android:layout_marginTop="30dp"
		android:tag="recyclerview">
		
		
		</android.support.v7.widget.RecyclerView>
		
		<android.support.v7.widget.RecyclerView
		android:id="@+id/recycler_view_vertical"
		android:layout_width="wrap_content"
		android:layout_height="230dp"
		android:layout_gravity="bottom"
		android:layout_marginBottom="30dp"
		
		android:layout_marginTop="30dp"
		android:background="#ffffff"
		android:tag="recyclerview">
		
		
		</android.support.v7.widget.RecyclerView>
		</FrameLayout>
		
		</cn.qssq666.slidemenu.HorizontalPager>
		</LinearLayout>



VerticalLPager


		 <cn.qssq666.slidemenu.VerticalLPager
	android:layout_width="match_parent"
	android:layout_height="match_parent">
	<FrameLayout
	android:tag="第一个view"
	android:background="#f00"
	android:layout_width="match_parent"
	android:layout_height="match_parent">
	<TextView
	android:layout_width="wrap_content"
	android:layout_gravity="center"
	android:textColor="@android:color/white"
	android:text="我是封面"
	
	android:layout_height="wrap_content" />
	</FrameLayout>
	
	<android.support.v7.widget.RecyclerView
	android:id="@+id/recycler_view"
	android:layout_marginLeft="50dp"
	android:tag="recyclerview"
	android:layout_marginRight="50dp"
	android:background="#fff"
	android:layout_width="match_parent"
	android:layout_height="match_parent">
	
	
	</android.support.v7.widget.RecyclerView>
	
	</cn.qssq666.slidemenu.VerticalLPager>
	  

**demoMainActivity界面**

```

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <!--实现方法1  合并-->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/black"
        android:text="这是一个垂直水平翻页并解决冲突的demo by qssq"
        android:textColor="@android:color/white" />

    <cn.qssq666.slidemenu.LoopVerticalHorizontalPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="150dp">

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#ff3333">

            <TextView
                style="@style/text_size_bg_tip"
                android:text="LoopVerticalHorizontalPager第一层 " />
        </FrameLayout>

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#333333">

            <TextView
                style="@style/text_size_bg_tip"

                android:layout_marginTop="30dp"
                android:text="LoopVerticalHorizontalPager第二层 " />

            <ListView
                android:id="@+id/listview"
                android:background="@color/colorAccent"
                android:layout_width="100dp"
                android:layout_height="100dp"></ListView>

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="centerCrop" />

        </FrameLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#8fff"
            android:visibility="gone">

            <ListView
                android:id="@+id/listview1"
                android:layout_width="200dp"
                android:layout_height="match_parent"
                android:layout_marginTop="80dp"></ListView>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="我是第三層" />

        </LinearLayout>

    </cn.qssq666.slidemenu.LoopVerticalHorizontalPager>


    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <cn.qssq666.slidemenu.VerticalLPager
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <FrameLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ccff0000">

                <TextView
                    style="@style/text_size_bg_tip"
                    android:text="VerticalLPager第一层" />
            </FrameLayout>

            <FrameLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#000000">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="VerticalLPager第二层"
                    android:textColor="@android:color/white"
                    android:textSize="20sp"
                    android:textStyle="bold" />

                <android.support.v7.widget.GridLayout xmlns:app="http://schemas.android.com/apk/res-auto"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_gravity="center"


                    app:alignmentMode="alignBounds"
                    app:columnCount="2">

                    <Button
                        android:id="@+id/btn_vertical_scroll"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="垂直滚动" />

                    <Button
                        android:id="@+id/btn_horzontal_scroll"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="水平滚动" />

                    <Button

                        android:id="@+id/btn_vertical_scoll_confict"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="垂直嵌套水平" />

                    <Button

                        android:id="@+id/btn_horzontall_scoll_confict"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="水平嵌套垂直" />

                    <Button

                        android:id="@+id/btn_vertical_scoll_vertical_confict"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="垂直嵌套垂直" />

                    <Button

                        android:id="@+id/btn_horzontal_scoll_horzontal_confict"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="水平嵌套水平" />


                </android.support.v7.widget.GridLayout>
            </FrameLayout>

        </cn.qssq666.slidemenu.VerticalLPager>
    </FrameLayout>
</LinearLayout>

```
