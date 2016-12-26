package com.wakdev.p014a;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.wakdev.a.c */
public class C0454c extends BaseAdapter implements Filterable {
    protected boolean f956a;
    private ArrayList<C0446a> f957b;
    private ArrayList<C0446a> f958c;
    private LayoutInflater f959d;
    private C0447b f960e;
    private LruCache<String, Bitmap> f961f;
    private C0453c f962g;

    /* renamed from: com.wakdev.a.c.1 */
    class C04481 extends LruCache<String, Bitmap> {
        final /* synthetic */ C0454c f946a;

        C04481(C0454c c0454c, int i) {
            this.f946a = c0454c;
            super(i);
        }

        protected int m1829a(String str, Bitmap bitmap) {
            return this.f946a.m1833a(bitmap) / 1024;
        }

        protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
            return m1829a((String) obj, (Bitmap) obj2);
        }
    }

    /* renamed from: com.wakdev.a.c.2 */
    class C04492 implements OnClickListener {
        final /* synthetic */ C0446a f947a;
        final /* synthetic */ C0454c f948b;

        C04492(C0454c c0454c, C0446a c0446a) {
            this.f948b = c0454c;
            this.f947a = c0446a;
        }

        public void onClick(View view) {
            this.f948b.f960e.m1827a(this.f947a);
        }
    }

    /* renamed from: com.wakdev.a.c.3 */
    class C04503 implements OnClickListener {
        final /* synthetic */ C0446a f949a;
        final /* synthetic */ C0454c f950b;

        C04503(C0454c c0454c, C0446a c0446a) {
            this.f950b = c0454c;
            this.f949a = c0446a;
        }

        public void onClick(View view) {
            this.f950b.f960e.m1828b(this.f949a);
        }
    }

    /* renamed from: com.wakdev.a.c.a */
    static class C0451a extends BitmapDrawable {
        private final WeakReference<C0452b> f951a;

        public C0451a(Resources resources, Bitmap bitmap, C0452b c0452b) {
            super(resources, bitmap);
            this.f951a = new WeakReference(c0452b);
        }

        public C0452b m1830a() {
            return (C0452b) this.f951a.get();
        }
    }

    /* renamed from: com.wakdev.a.c.b */
    class C0452b extends AsyncTask<File, Void, Bitmap> {
        public File f952a;
        final /* synthetic */ C0454c f953b;
        private final WeakReference<ImageView> f954c;

        public C0452b(C0454c c0454c, ImageView imageView) {
            this.f953b = c0454c;
            this.f954c = new WeakReference(imageView);
        }

        protected Bitmap m1831a(File... fileArr) {
            Throwable th;
            Bitmap bitmap;
            Throwable th2;
            Bitmap bitmap2 = null;
            if (!isCancelled()) {
                this.f952a = fileArr[0];
                if (this.f952a != null) {
                    try {
                        Bitmap thumbnail;
                        ContentResolver contentResolver = WDCore.m2174a().getContentResolver();
                        Cursor query = contentResolver.query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data='" + this.f952a.getAbsolutePath() + "'", null, null);
                        if (query != null) {
                            if (query.getCount() > 0) {
                                query.moveToFirst();
                                thumbnail = Thumbnails.getThumbnail(contentResolver, (long) query.getInt(0), 1, null);
                            } else {
                                thumbnail = null;
                            }
                            try {
                                query.close();
                            } catch (Throwable e) {
                                th = e;
                                bitmap = thumbnail;
                                th2 = th;
                                th2.printStackTrace();
                                bitmap2 = bitmap;
                                if (bitmap2 != null) {
                                    this.f953b.m1843a(this.f952a.getAbsolutePath(), bitmap2);
                                }
                                return bitmap2;
                            } catch (Throwable e2) {
                                th = e2;
                                bitmap = thumbnail;
                                th2 = th;
                                th2.printStackTrace();
                                bitmap2 = bitmap;
                                if (bitmap2 != null) {
                                    this.f953b.m1843a(this.f952a.getAbsolutePath(), bitmap2);
                                }
                                return bitmap2;
                            }
                        }
                        thumbnail = null;
                        bitmap2 = thumbnail;
                    } catch (Exception e3) {
                        th2 = e3;
                        bitmap = null;
                        th2.printStackTrace();
                        bitmap2 = bitmap;
                        if (bitmap2 != null) {
                            this.f953b.m1843a(this.f952a.getAbsolutePath(), bitmap2);
                        }
                        return bitmap2;
                    } catch (Error e4) {
                        th2 = e4;
                        bitmap = null;
                        th2.printStackTrace();
                        bitmap2 = bitmap;
                        if (bitmap2 != null) {
                            this.f953b.m1843a(this.f952a.getAbsolutePath(), bitmap2);
                        }
                        return bitmap2;
                    }
                }
                if (bitmap2 != null) {
                    this.f953b.m1843a(this.f952a.getAbsolutePath(), bitmap2);
                }
            }
            return bitmap2;
        }

        protected void m1832a(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }
            if (this.f954c != null && bitmap != null) {
                ImageView imageView = (ImageView) this.f954c.get();
                if (this == C0454c.m1840b(imageView) && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1831a((File[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m1832a((Bitmap) obj);
        }
    }

    /* renamed from: com.wakdev.a.c.c */
    private class C0453c extends Filter {
        final /* synthetic */ C0454c f955a;

        private C0453c(C0454c c0454c) {
            this.f955a = c0454c;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() <= 0) {
                filterResults.count = this.f955a.f958c.size();
                filterResults.values = this.f955a.f958c;
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = this.f955a.f958c.iterator();
                while (it.hasNext()) {
                    C0446a c0446a = (C0446a) it.next();
                    if (c0446a.m1821j().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        arrayList.add(c0446a);
                    } else {
                        boolean contains = c0446a.m1822k().toLowerCase().contains(charSequence.toString().toLowerCase());
                        if (this.f955a.f956a && contains) {
                            arrayList.add(c0446a);
                        }
                    }
                }
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            }
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            this.f955a.f957b = (ArrayList) filterResults.values;
            this.f955a.notifyDataSetChanged();
        }
    }

    public C0454c(Context context, ArrayList<C0446a> arrayList) {
        this.f960e = null;
        this.f956a = false;
        this.f957b = arrayList;
        this.f958c = arrayList;
        this.f959d = LayoutInflater.from(context);
        this.f961f = new C04481(this, ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8);
    }

    private int m1833a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    private Bitmap m1835a(String str) {
        return (Bitmap) this.f961f.get(str);
    }

    public static boolean m1839a(File file, ImageView imageView) {
        C0452b b = C0454c.m1840b(imageView);
        if (b == null) {
            return true;
        }
        if (b.f952a == file) {
            return false;
        }
        b.cancel(true);
        return true;
    }

    private static C0452b m1840b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof C0451a) {
                return ((C0451a) drawable).m1830a();
            }
        }
        return null;
    }

    public void m1842a(C0447b c0447b) {
        this.f960e = c0447b;
    }

    protected void m1843a(String str, Bitmap bitmap) {
        if (m1835a(str) == null) {
            this.f961f.put(str, bitmap);
        }
    }

    public void m1844a(boolean z) {
        this.f956a = z;
    }

    public int getCount() {
        return this.f957b.size();
    }

    public Filter getFilter() {
        if (this.f962g == null) {
            this.f962g = new C0453c();
        }
        return this.f962g;
    }

    public Object getItem(int i) {
        return this.f957b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f959d.inflate(C0622e.row_layout, null);
        }
        TextView textView = (TextView) view.findViewById(C0621d.headline);
        TextView textView2 = (TextView) view.findViewById(C0621d.baseline);
        ImageView imageView = (ImageView) view.findViewById(C0621d.item_image);
        ImageView imageView2 = (ImageView) view.findViewById(C0621d.item_image_right);
        C0446a c0446a = (C0446a) this.f957b.get(i);
        textView.setText(c0446a.m1821j());
        textView2.setText(c0446a.m1822k());
        if (c0446a.m1810c() != -1) {
            textView.setTextColor(c0446a.m1810c());
        }
        if (c0446a.m1813d() != -1) {
            textView2.setTextColor(c0446a.m1813d());
        }
        String g = c0446a.m1818g();
        if (g != null) {
            File file = new File(g);
            if (file != null && file.exists()) {
                imageView.setVisibility(0);
                imageView.setImageResource(C0620c.fm_type_image);
                Bitmap a = m1835a(file.getAbsolutePath());
                if (a != null) {
                    imageView.setImageBitmap(a);
                } else if (C0454c.m1839a(file, imageView)) {
                    C0452b c0452b = new C0452b(this, imageView);
                    Resources resources = WDCore.m2174a().getResources();
                    imageView.setImageDrawable(new C0451a(resources, BitmapFactory.decodeResource(resources, C0620c.fm_type_image), c0452b));
                    c0452b.execute(new File[]{file});
                }
            }
        } else if (c0446a.m1816e() != null) {
            imageView.setVisibility(0);
            imageView.setImageDrawable(c0446a.m1816e());
        } else if (c0446a.m1801a() != -1) {
            imageView.setVisibility(0);
            imageView.setImageResource(c0446a.m1801a());
        } else {
            imageView.setVisibility(4);
        }
        if (c0446a.m1817f() != null) {
            imageView2.setVisibility(0);
            imageView2.setImageDrawable(c0446a.m1817f());
        } else if (c0446a.m1806b() != -1) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(c0446a.m1806b());
        } else {
            imageView2.setVisibility(4);
        }
        if (this.f960e != null) {
            view.setOnClickListener(new C04492(this, c0446a));
            imageView2.setOnClickListener(new C04503(this, c0446a));
        }
        return view;
    }
}
