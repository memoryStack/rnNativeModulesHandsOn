import React from 'react';
import { NativeModules, Button } from 'react-native';

const { CalenderModule } = NativeModules;

const NewModuleButton = () => {
  const onPress = () => {
    console.log('We will invoke the native module here!');
    CalenderModule.createCalendarEvent('testName', 'testLocation');
  };

  return (
    <Button
      title="Click to invoke your native module!"
      color="#841584"
      onPress={onPress}
    />
  );
};

export default NewModuleButton;