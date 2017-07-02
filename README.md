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

