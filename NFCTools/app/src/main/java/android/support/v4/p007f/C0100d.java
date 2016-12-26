package android.support.v4.p007f;

import android.util.Log;
import java.io.Writer;

/* renamed from: android.support.v4.f.d */
public class C0100d extends Writer {
    private final String f277a;
    private StringBuilder f278b;

    public C0100d(String str) {
        this.f278b = new StringBuilder(128);
        this.f277a = str;
    }

    private void m446a() {
        if (this.f278b.length() > 0) {
            Log.d(this.f277a, this.f278b.toString());
            this.f278b.delete(0, this.f278b.length());
        }
    }

    public void close() {
        m446a();
    }

    public void flush() {
        m446a();
    }

    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                m446a();
            } else {
                this.f278b.append(c);
            }
        }
    }
}
