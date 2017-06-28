

![演示图片地址no](https://github.com/qssq/giftanim/blob/master/Pictures/xxxxxanim.gif)

**功能**

支持水平 垂直滑动翻页 
水平垂直嵌套不冲突，响应点击事件 支持嵌套recyclerview

**原理**

第一个-view为 视频区域 第二个 为菜单区域。滑动的是第二个view.第一个view始终在原来的位置。
使用方法简单 直接嵌套起来使用就行了。


**存在的问题**


  如果嵌套了全屏的recyclerview作为菜单那么事件肯定全部给recyclerview了， 那么 菜单也就失去了作用了，事件问题 还请牛人改进，我暂时还在学习中，我会的时候再解决更牛逼的需求 ，recyclerview不能滑动了再让菜单继续滑动。*

**疑点**


本demo还包含了ios加载进度控件 和 循环翻页的东西

循环翻页的没有加上demo，可以自己测试使用，貌似也没什么大问题 。
我有空加上。


**QQ**

694886526 http://qssq666.cn
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
	  

