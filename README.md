# android-splash-demo

This is a demo app using [Splash Screen API (androidx.core.splashscreen)](https://developer.android.com/reference/kotlin/androidx/core/splashscreen/SplashScreen)

## Problem in Android 12 and later

When a new activity is launhed by calling `startActivity() + Intent.FLAG_ACTIVITY_NEW_TASK`,
the splash icon is **NOT** shown while the splash screen is displayed.

## Solution

- Android 13 and later: set [`<item name="android:windowSplashScreenBehavior">icon_preferred</item>`](https://developer.android.com/develop/ui/views/launch/splash-screen#set-theme) in the theme applied to the target activity
- Android 12: [pass an option of `bundleOf("android.activity.splashScreenStyle" to 1)`](https://issuetracker.google.com/issues/205021357#comment14) when calling `startActivity()`


## Screenshot

| before | after |  
|----|----|  
|<video src="https://github.com/Seo-4d696b75/android-splash-demo/assets/25225028/0beeecb0-b74c-4212-ad89-af5c25a042af"> | <video src="https://github.com/Seo-4d696b75/android-splash-demo/assets/25225028/1609811c-ee5b-4cfb-8791-b31c8e25bdbc"> |  

