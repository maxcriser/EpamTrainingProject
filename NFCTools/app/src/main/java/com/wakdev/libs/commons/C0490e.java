package com.wakdev.libs.commons;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/* renamed from: com.wakdev.libs.commons.e */
public class C0490e {
    public static void m2074a(CheckBox checkBox, String str) {
        if (str != null && !str.isEmpty() && checkBox != null) {
            if (str.equals(String.valueOf(true))) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
    }

    public static void m2075a(EditText editText, String str) {
        if (str != null && !str.isEmpty() && editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    public static void m2076a(NumberPicker numberPicker, String str) {
        if (str != null && !str.isEmpty() && numberPicker != null) {
            try {
                numberPicker.setValue(Integer.valueOf(str).intValue());
            } catch (Exception e) {
            }
        }
    }

    public static void m2077a(SeekBar seekBar, String str, int i) {
        if (str != null && !str.isEmpty() && seekBar != null) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt <= i) {
                    seekBar.setProgress(parseInt);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void m2078a(Spinner spinner, String str) {
        if (str != null && !str.isEmpty() && spinner != null) {
            try {
                spinner.setSelection(Integer.parseInt(str));
            } catch (Exception e) {
            }
        }
    }

    public static void m2079a(TimePicker timePicker, String str, String str2) {
        if (str != null && str2 != null && !str.isEmpty() && !str2.isEmpty() && timePicker != null) {
            try {
                timePicker.setCurrentHour(Integer.valueOf(str));
                timePicker.setCurrentMinute(Integer.valueOf(str2));
            } catch (Exception e) {
            }
        }
    }

    public static void m2080a(ToggleButton toggleButton, String str) {
        if (str != null && !str.isEmpty() && toggleButton != null) {
            try {
                toggleButton.setChecked(Boolean.parseBoolean(str));
            } catch (Exception e) {
            }
        }
    }
}
