

![演示图片地址no](https://github.com/qssq/slidemenu/blob/master/Pictures/1.gif)

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
	  

