# rnNativeModulesHandsOn

RoadMap (doing all these things on android only right now)
  1. first i will follow the react-native docs guide for Native Modules using the given calender program
  2. will write sudoku puzzles generator in c++ library and use it on JS Side
  3. will write Native Module for Sudoku App's foreground/background transitions to determine if the game should be paused or not
  4. will publish a Gaussian Blur Native Module for React Native

Theory about how the NativeModule works overall
  1. Native Modules System exposes istances (or objects) of Java/Objective-C/c++ classes to JS as JS objects. And we can run native code from JS side.
  2. Native Modules would be Java classes which will extend "ReactContextBaseJavaModule" class and will implement the functionality requiered by the JS.
      We can also extend "BaseJavaModule" or implement a "NativeModule" but if we extend "ReactContextBaseJavaModule" then "ReactContextBaseJavaModule" gives access
      to the ReactApplicationContext (RAC) we can also hook into the lifecycle methods of the activity. we get some other benefits also which i don't understand right now.
  3. Once a native module is written, it needs to be registered with React Native. We achieve it by adding your native module to a "ReactPackage"
      and register the "ReactPackage" with "React Native". During initialization, React Native will loop over all packages, and for each "ReactPackage", register each native module within.
      (this above method is a factor why react-native apps have higher load time, technique to reduce it is to load the native modules partially. load some modules on initialization which are required at the load time and load rest as we require them or later).
      "TurboModules" is a soultion for this which will be easy to use in future. Will learn it in near future.
      React Native invokes the method "createNativeModules()" on a "ReactPackage" in order to get the list of native modules to register.
      Note: For Android, if a module is not instantiated and returned in createNativeModules it will not be available from JavaScript. (TODO: any safe practice for this ??)
  4. In the calender example we created a ReactPackage named "MyAppPackage.java" that implements "ReactPackage" and "createNativeModules" method mentioned above will be called 
      by react-native in this file. And this method will return a list of the native modules instances.
  5. Now you must add "MyAppPackage" to the list of packages returned in ReactNativeHost's getPackages() method in your MainApplication.java file.

Some Interesting Features Support of Native Modules :- 
  Note: For full read goto here https://reactnative.dev/docs/native-modules-android#beyond-a-calendar-native-module
  1. Callbacks
      Native modules also support a unique kind of argument: a callback. Callbacks are used to pass data from Java to JavaScript for "asynchronous methods" (async methods of JS or JAVA ??). They can also be used to asynchronously execute JavaScript from the native side.
      There are a couple of nuances with callback arguments that will soon be lifted with "TurboModules".
        1. you can only have two callbacks in your function arguments- a "successCallback" and a "failureCallback" (like JS promises)
        2. the last argument to a native module method call, if it's a function, is treated as the successCallback, and the second to last argument to a native module          method call, if it's a function, is treated as the failure callback.
        3. NOTE: you can only pass serializable data through the callback from native code to JavaScript. (TODO: what is serializable data ??)
        4. It is also important to highlight that the callback is not invoked immediately after the native function completes
        5. Another important detail to note is that a native module method can only invoke one callback, one time. This means that you can either call a success callback      or a failure callback, but not both, and each callback can only be invoked at most one time. A native module can, however, store the callback and invoke 
            it later.
  2. Promises
      Native modules can also fulfill a Promise, which can simplify your JavaScript, especially when using ES2016's "async/await" syntax.
      Rules of fulfilling the promise are same as the callbacks execution.
      TODO: let's understand this more deeply. i possibly will use it.
  3. Sending Events to JavaScript
      Native modules can signal events to JavaScript without being invoked directly. (isn't it just wow ??)
      For example, you might want to signal to JavaScript a reminder that a calendar event from the native Android calendar app will occur soon.
      (can it happen even when our app is closed ??)
  4. Getting Activity Result from startActivityForResult
  5. Listening to Lifecycle Events
  6. Threading
      No date, on Android, all native module async methods execute on one thread, (NativeModules thread).
      In future it will change, if some blocking call is required then the heavy work should be dispatched to an internally managed worker thread, and any callbacks distributed from there.