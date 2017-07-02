# DataBinding

### 1. config build.gradle of app;
```
android {
...
    dataBinding {
        enabled = true
    }
}
```

### 2. config layout
#### 2.1 change "setContentView()" to "DataBindingUtil.setContentView()", and the "ActivityMainBinding" is layout name appends "Binding"
```
// setContentView(R.layout.activity_main);
   ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
```

2.2 create model

```
public class User {
    public String firstName;
    public String lastName;
    public String phone; // 电话
    public boolean isShowPhone;
    /**
     * 获取全名
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public String getFullName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(firstName)) {
            sb.append(firstName);
        }
        if (!TextUtils.isEmpty(lastName)) {
            sb.append(" " + lastName);
        }
        return sb.toString();
    }
}
```
### 2.3 config layout
a. user common code build layout

```
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        xmlns:android="http://schemas.android.com/apk/res/android">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="First Name : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Zhang" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Phone Number : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="133 4444 5555" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Full Name : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Zhang Huakai" />
        </LinearLayout>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="show toast" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="run Task" />

    </LinearLayout>
```

b. change to databinding layout

```
/*user "layout" as root tab. and use "<data></data>" import data*/

<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="user"
            type="com.example.zhanghua.databindingdemo.User"></variable>
    </data>
/*the common code are ect*/
...
</layout>
```
c. set data in MainActivity

```
        User user = new User();
        user.firstName="Albert";
        user.lastName="Einstein";
        user.isShowPhone=true;
        user.phone="001-043234";
        binding.setUser(user);
```

### 3. use databinding show data

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <import type="android.view.View" />

        <variable
            name="user"
            type="com.example.zhanghua.databindingdemo.User"></variable>
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <!--1. show firstName-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="First Name : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@{user.firstName}" />
        </LinearLayout>
        <!--2. show phone-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Phone Number : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@{user.phone}" />
        </LinearLayout>
        <!--3. method-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Full Name : " />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@{user.getFullName()}"
                android:visibility="@{user.isShowPhone?View.VISIBLE:View.GONE}" />
        </LinearLayout>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="show toast" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="run Task" />

    </LinearLayout>
</layout>

``` 
### 4. lambda

#### 4.1 create task
```
public class MyTask implements Runnable {

    private Context mContext;

    public MyTask(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        Toast.makeText(mContext, "tash run", Toast.LENGTH_SHORT).show();
    }
}
```
#### 4.2 create handler

```
public class MyHandler {

    public void showTost(View view) {
        Toast.makeText(view.getContext(), "This is a toast", Toast.LENGTH_SHORT).show();
    }

    public void onEventListenerExecute(MyTask task) {
        task.run();
    }

}
```

#### 4.3
add MyHandler and MyTask to MainActivityBinding

```
    <data>
    		
		...
    
        <variable
            name="myHandler"
            type="com.example.zhanghua.databindingdemo.MyHandler"></variable>

        <variable
            name="myTask"
            type="com.example.zhanghua.databindingdemo.MyTask"></variable>
    </data>
```
#### 4.4

需要注意的是：此功能在 Android Gradle Plugin version 2.0 或更新版本上可用，支持lambda表达式
在方法引用中，方法的参数必须与监听器对象的参数相匹配。
而在监听绑定中，只要返回值与监听器对象的预期返回值相匹配即可

```
        <!--lambda no need to set view param for "showTost(View view)"-->
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="@{myHandler::showTost}"
            android:text="show toast" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="@{()->myHandler.onEventListenerExecute(myTask)}"
            android:text="run Task" />

```
#### 4.5 bind method and click listener

```
        MyHandler handler = new MyHandler();
        MyTask task = new MyTask(this);
        binding.setMyHandler(handler);
        binding.setMyTask(task);
```

