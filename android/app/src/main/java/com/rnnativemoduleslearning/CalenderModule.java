package com.rnnativemoduleslearning;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;

public class CalenderModule extends ReactContextBaseJavaModule {
    CalenderModule(ReactApplicationContext context) {
        super(context);
    }

    // this returned string would be used as the NativeModule name by the JavaScript Side
    @Override
    public String getName() {
        return "CalenderModule";
    }

    // All native module methods meant to be invoked from JavaScript must be annotated with @ReactMethod
    @ReactMethod
    public void createCalendarEvent(String name, String location) {
        Log.d("CalenderModule", "Create event called with name: " + name
                + " and location: " + location);
    }



}