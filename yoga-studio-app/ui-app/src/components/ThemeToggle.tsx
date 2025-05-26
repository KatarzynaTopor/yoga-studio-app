import React, { useState } from 'react';
import useDarkSide from './useDarkSide';
import { DarkModeSwitch } from 'react-toggle-dark-mode';

const ThemeToggle = () => {
  const [colorTheme, setTheme] = useDarkSide();
  const [darkSide, setDarkSide] = useState(colorTheme === 'light');

  const toggleDarkMode = (checked: boolean) => {
    setTheme(checked ? 'dark' : 'light');
    setDarkSide(checked);
  };

  return (
    <DarkModeSwitch
      checked={darkSide}
      onChange={toggleDarkMode}
      size={28}
    />
  );
};

export default ThemeToggle;
