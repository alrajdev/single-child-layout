# Single Child Layout
An Androd FrameLayout implementation which can only have a single direct child visible/invisible

Add to your project:
---

In your root `build.gradle` file:
```groovy
allproject {
  repositories {
    ...
    maven { url 'jitpack.io' }
  }
}
```

and in your module `build.gradle` file: Latest version: **`1.0`**
```groovy
dependencies {
  implementation 'com.github.alraj-dev:singlechildlayout:latest-version'
}
```

When using FrameLayout to show alternative view on certain conditions, we need to show one view and hide others, which is frustrating.
This Layout can have only one visible child at a time, here visible means both `View.VISIBLE` and  `View.INVISIBLE`.

In XML when multiple child view is visible, every child view is set to `View.GONE` except the last visible child.

Eg:
```xml
 <SingleChildLayout
    android:layout_width="match_parent"
    android:layout_height="match_payment">
    
    <TextView
        android:id="@+id/textview1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView1"/>
        
    <TextView
        android:id="@+id/textview2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView2"/>
        
    <TextView
        android:id="@+id/textview3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView3"/>
        
    <TextView
        android:id="@+id/textview4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView4"/> 
</SingleChildLayout>
```

Here only textview4 is visible and other textviews are set to `View.GONE`.
Only the last child which has visibility set to `View.VISIBLE` or `View.INVISIBLE` is kept as such.

When another child is shown, the previous visible child is hidden

Eg:
```kotlin 
textview1.visibility = View.VISIBLE
```
or
```kotlin
textview1.visiblilty = View.INVISIBLE
```

textview1 is automatically set to `View.GONE`, so no need to set other child view's to `View.GONE` manually. So,
```kotlin
textview1.visibility = View.VISIBLE
```

instead of
```kotlin
textview1.visibility = View.VISIBLE
textview4.visibility = View.GONE
```
