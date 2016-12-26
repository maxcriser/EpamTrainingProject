package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

/* renamed from: android.support.v4.view.o */
public class C0216o {
    static final C0213c f426a;

    /* renamed from: android.support.v4.view.o.c */
    interface C0213c {
        int m939a(MotionEvent motionEvent);

        int m940a(MotionEvent motionEvent, int i);

        int m941b(MotionEvent motionEvent, int i);

        float m942c(MotionEvent motionEvent, int i);

        float m943d(MotionEvent motionEvent, int i);
    }

    /* renamed from: android.support.v4.view.o.a */
    static class C0214a implements C0213c {
        C0214a() {
        }

        public int m944a(MotionEvent motionEvent) {
            return 1;
        }

        public int m945a(MotionEvent motionEvent, int i) {
            return i == 0 ? 0 : -1;
        }

        public int m946b(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m947c(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m948d(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
    }

    /* renamed from: android.support.v4.view.o.b */
    static class C0215b implements C0213c {
        C0215b() {
        }

        public int m949a(MotionEvent motionEvent) {
            return C0217p.m961a(motionEvent);
        }

        public int m950a(MotionEvent motionEvent, int i) {
            return C0217p.m962a(motionEvent, i);
        }

        public int m951b(MotionEvent motionEvent, int i) {
            return C0217p.m963b(motionEvent, i);
        }

        public float m952c(MotionEvent motionEvent, int i) {
            return C0217p.m964c(motionEvent, i);
        }

        public float m953d(MotionEvent motionEvent, int i) {
            return C0217p.m965d(motionEvent, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 5) {
            f426a = new C0215b();
        } else {
            f426a = new C0214a();
        }
    }

    public static int m954a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int m955a(MotionEvent motionEvent, int i) {
        return f426a.m940a(motionEvent, i);
    }

    public static int m956b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int m957b(MotionEvent motionEvent, int i) {
        return f426a.m941b(motionEvent, i);
    }

    public static float m958c(MotionEvent motionEvent, int i) {
        return f426a.m942c(motionEvent, i);
    }

    public static int m959c(MotionEvent motionEvent) {
        return f426a.m939a(motionEvent);
    }

    public static float m960d(MotionEvent motionEvent, int i) {
        return f426a.m943d(motionEvent, i);
    }
}
