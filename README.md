# Weather-Checking Task Scheduler

This is an Epicodus project that works as a to-do list tracker with a weather checking functionality, built with android specific layout and functionalities. This app utilizes fragments for recycler-view adapters and popup dialogs, Firebase for data persistence, and OpenWeatherMap's API for returning current weather.  

```
Week 1 - week 4
#Task Scheduler with weather checking
1.This is task scheduler that:
         * User can navigate through different tabs.
         * User can create an account and log in/log out for user specific data.
         * User can create a time specific task and save it to firebase.
         * User can view saved tasks in a list.
         * User can rearrange task order by dragging and dropping.
         * User can delete task from database by using swipe gesture on a task item.
         * Saves user defined zip code for weather checking either locally with shared preference.

2.Additional functionalities in the future:
         * Works as a normal alarm clock.
         * Have Timer and Stopwatch functionality.
         * Checks real time weather 30 minutes before the alarm time, and if is raining, it will push the timer 10 minutes
         (user defined) earlier.
         * play different alarm ringtone(user defined) depending on the weather.
```



## SET UP

* clone repo https://github.com/june11084/EpicodusGames-Android.git
* In the Constants.java class, replace "public static final String OPENWEATHER_TOKEN = BuildConfig.OPENWEATHER_TOKEN;"
   with public static final String OPENWEATHER_TOKEN = "Your Own API Key";

### Techologies Used
```
Android Studio 3.0 https://developer.android.com/studio/index.html
OpenWeatherMap API https://openweathermap.org/appid#get
Firebase https://console.firebase.google.com
```

## Authors
* **Jun Li** - [june11084](https://github.com/june11084)


## Requirements
```
Android Studio 3.0
```
