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
  