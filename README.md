# 🚀 Koranger - The Ultimate Range Selector for Android

**Koranger** is a highly customizable and performance-optimized range bar for Android. Built to support **both vertical and horizontal orientations**, it allows you to define ranges, customize colors, adjust the radius, and integrate seamlessly with both **Jetpack Compose** and **native Android views**. Whether you're creating a music player, a filter selector, or a settings slider, RangeBarView has got you covered!

## 🌟 Features

- **Super customizable**: Change colors, adjust radii, set ranges, and more!
- **Horizontal and Vertical**: Fully supports both orientations out of the box.
- **Compose & Native**: Works with Jetpack Compose and traditional Android views.
- **Smooth Performance**: Built for high performance and low memory usage.
- **Highly Compatible**: Works on all modern Android versions.

## 🎉 Installation

Add the following to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.muhammadzubair.koranger:rangebarview:1.0.0")
}
```


## ✨ Jetpack Compose Setup
If you’re using Jetpack Compose, setting up the RangeBarView is a breeze!

```kotlin
import com.muhammadzubair.koranger.rangebarview.compose.RangeBar

@Composable
fun RangeBarExample() {
    RangeBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        minValue = 0f,
        maxValue = 100f,
        startValue = 25f,
        endValue = 75f,
        onValueChange = { start, end ->
            // Handle the changed range values here
        },
        barColor = Color.Gray,
        thumbColor = Color.Blue,
        activeBarColor = Color.Green,
        barThickness = 8.dp
    )
}
```

## 🎨 Jetpack Compose Attributes:
- **minValue**: The minimum value for the range.
- **maxValue**: The maximum value for the range.
- **startValue & endValue**: Initial values for the range selection.
- **onValueChange**: Callback for detecting changes in range values.
- **barColor, activeBarColor, thumbColor**: Customizable color attributes.
- **barThickness**: Thickness of the range bar.

## 🎯 Native Android XML Setup
For native Android usage, you can easily drop it into your layout XML:

```xml
<com.muhammadzubair.koranger.rangebarview.RangeBarView
    android:id="@+id/rangeBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:minValue="0"
    app:maxValue="100"
    app:startValue="20"
    app:endValue="80"
    app:barColor="@color/bar_color"
    app:thumbColor="@color/thumb_color"
    app:activeBarColor="@color/active_bar_color"
    app:orientation="horizontal"
    app:barThickness="6dp" />
```

## 🎨 Native Attributes:
- **app:minValue**: The minimum value for the range.
- **app:maxValue**: The maximum value for the range.
- **app:startValue & app:endValue**: Initial range values.
- **app:barColor, app:activeBarColor, app:thumbColor**: Customizable color attributes.
- **app:barThickness**: Adjust the thickness of the range bar.
****

## 🚀 Quick Kotlin Setup for Native

```kotlin
val rangeBarView = findViewById<RangeBarView>(R.id.rangeBar)

rangeBarView.setOnRangeChangeListener { start, end ->
    // Handle value changes here
}

rangeBarView.apply {
    minValue = 0f
    maxValue = 100f
    startValue = 10f
    endValue = 90f
    barColor = Color.GRAY
    thumbColor = Color.RED
    activeBarColor = Color.GREEN
}

```

## 🚀 Why Use Koranger?
- **📱 Customizable**: Full control over appearance and behavior.
- **⚡ High Performance**: Efficient rendering, perfect for real-time apps.
- **🧱 Composable & Native**: The best of both worlds for modern Android development.

## 📜 License
```text
MIT License
```

Enjoy creating with Koranger! 🌟
