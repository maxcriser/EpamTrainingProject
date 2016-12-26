package com.wakdev.libs.commons;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import com.wakdev.libs.core.WDCore;

/* renamed from: com.wakdev.libs.commons.l */
public class C0502l {

    /* renamed from: com.wakdev.libs.commons.l.1 */
    static class C05011 implements Runnable {
        final /* synthetic */ int f1139a;
        final /* synthetic */ int f1140b;
        final /* synthetic */ double f1141c;
        final /* synthetic */ Handler f1142d;

        C05011(int i, int i2, double d, Handler handler) {
            this.f1139a = i;
            this.f1140b = i2;
            this.f1141c = d;
            this.f1142d = handler;
        }

        public void run() {
            int i;
            int i2 = 0;
            int i3 = this.f1140b * this.f1139a;
            double[] dArr = new double[i3];
            byte[] bArr = new byte[(i3 * 2)];
            for (i = 0; i < i3; i++) {
                dArr[i] = Math.sin((6.283185307179586d * ((double) i)) / (((double) this.f1140b) / this.f1141c));
            }
            i3 = dArr.length;
            i = 0;
            while (i2 < i3) {
                short s = (short) ((int) (dArr[i2] * 32767.0d));
                int i4 = i + 1;
                bArr[i] = (byte) (s & 255);
                i = i4 + 1;
                bArr[i4] = (byte) ((s & 65280) >>> 8);
                i2++;
            }
            try {
                AudioTrack audioTrack = new AudioTrack(3, this.f1140b, 4, 2, bArr.length, 0);
                audioTrack.write(bArr, 0, bArr.length);
                audioTrack.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f1142d.removeCallbacks(this);
        }
    }

    public static int m2104a(int i) {
        return ((AudioManager) WDCore.m2174a().getApplicationContext().getSystemService("audio")).getStreamMaxVolume(i);
    }

    public static void m2105a(double d, int i, int i2) {
        Handler handler = new Handler();
        handler.post(new C05011(i2, i, d, handler));
    }

    public static int m2106b(int i) {
        return ((AudioManager) WDCore.m2174a().getApplicationContext().getSystemService("audio")).getStreamVolume(i);
    }
}
