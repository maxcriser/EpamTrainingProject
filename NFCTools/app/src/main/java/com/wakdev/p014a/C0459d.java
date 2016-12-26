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

/* renamed from: com.wakdev.a.d */
public class C0459d extends BaseAdapter implements Filterable {
    protected boolean f969a;
    private ArrayList<C0446a> f970b;
    private ArrayList<C0446a> f971c;
    private LayoutInflater f972d;
    private LruCache<String, Bitmap> f973e;
    private C0458c f974f;

    /* renamed from: com.wakdev.a.d.1 */
    class C04551 extends LruCache<String, Bitmap> {
        final /* synthetic */ C0459d f963a;

        C04551(C0459d c0459d, int i) {
            this.f963a = c0459d;
            super(i);
        }

        protected int m1845a(String str, Bitmap bitmap) {
            return this.f963a.m1849a(bitmap) / 1024;
        }

        protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
            return m1845a((String) obj, (Bitmap) obj2);
        }
    }

    /* renamed from: com.wakdev.a.d.a */
    static class C0456a extends BitmapDrawable {
        private final WeakReference<C0457b> f964a;

        public C0456a(Resources resources, Bitmap bitmap, C0457b c0457b) {
            super(resources, bitmap);
            this.f964a = new WeakReference(c0457b);
        }

        public C0457b m1846a() {
            return (C0457b) this.f964a.get();
        }
    }

    /* renamed from: com.wakdev.a.d.b */
    class C0457b extends AsyncTask<File, Void, Bitmap> {
        public File f965a;
        final /* synthetic */ C0459d f966b;
        private final WeakReference<ImageView> f967c;

        public C0457b(C0459d c0459d, ImageView imageView) {
            this.f966b = c0459d;
            this.f967c = new WeakReference(imageView);
        }

        protected Bitmap m1847a(File... fileArr) {
            Throwable th;
            Bitmap bitmap;
            Throwable th2;
            Bitmap bitmap2 = null;
            if (!isCancelled()) {
                this.f965a = fileArr[0];
                if (this.f965a != null) {
                    try {
                        Bitmap thumbnail;
                        ContentResolver contentResolver = WDCore.m2174a().getContentResolver();
                        Cursor query = contentResolver.query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data='" + this.f965a.getAbsolutePath() + "'", null, null);
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
                                    this.f966b.m1859a(this.f965a.getAbsolutePath(), bitmap2);
                                }
                                return bitmap2;
                            } catch (Throwable e2) {
                                th = e2;
                                bitmap = thumbnail;
                                th2 = th;
                                th2.printStackTrace();
                                bitmap2 = bitmap;
                                if (bitmap2 != null) {
                                    this.f966b.m1859a(this.f965a.getAbsolutePath(), bitmap2);
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
                            this.f966b.m1859a(this.f965a.getAbsolutePath(), bitmap2);
                        }
                        return bitmap2;
                    } catch (Error e4) {
                        th2 = e4;
                        bitmap = null;
                        th2.printStackTrace();
                        bitmap2 = bitmap;
                        if (bitmap2 != null) {
                            this.f966b.m1859a(this.f965a.getAbsolutePath(), bitmap2);
                        }
                        return bitmap2;
                    }
                }
                if (bitmap2 != null) {
                    this.f966b.m1859a(this.f965a.getAbsolutePath(), bitmap2);
                }
            }
            return bitmap2;
        }

        protected void m1848a(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }
            if (this.f967c != null && bitmap != null) {
                ImageView imageView = (ImageView) this.f967c.get();
                if (this == C0459d.m1856b(imageView) && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1847a((File[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m1848a((Bitmap) obj);
        }
    }

    /* renamed from: com.wakdev.a.d.c */
    private class C0458c extends Filter {
        final /* synthetic */ C0459d f968a;

        private C0458c(C0459d c0459d) {
            this.f968a = c0459d;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() <= 0) {
                filterResults.count = this.f968a.f971c.size();
                filterResults.values = this.f968a.f971c;
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = this.f968a.f971c.iterator();
                while (it.hasNext()) {
                    C0446a c0446a = (C0446a) it.next();
                    if (c0446a.m1821j().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        arrayList.add(c0446a);
                    } else {
                        boolean contains = c0446a.m1822k().toLowerCase().contains(charSequence.toString().toLowerCase());
                        if (this.f968a.f969a && contains) {
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
            this.f968a.f970b = (ArrayList) filterResults.values;
            this.f968a.notifyDataSetChanged();
        }
    }

    public C0459d(Context context, ArrayList<C0446a> arrayList) {
        this.f969a = false;
        this.f970b = arrayList;
        this.f971c = arrayList;
        this.f972d = LayoutInflater.from(context);
        this.f973e = new C04551(this, ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8);
    }

    private int m1849a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    private Bitmap m1851a(String str) {
        return (Bitmap) this.f973e.get(str);
    }

    public static boolean m1855a(File file, ImageView imageView) {
        C0457b b = C0459d.m1856b(imageView);
        if (b == null) {
            return true;
        }
        if (b.f965a == file) {
            return false;
        }
        b.cancel(true);
        return true;
    }

    private static C0457b m1856b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof C0456a) {
                return ((C0456a) drawable).m1846a();
            }
        }
        return null;
    }

    public void m1857a(C0446a c0446a) {
        this.f970b.remove(c0446a);
    }

    public void m1858a(C0446a c0446a, int i) {
        this.f970b.add(i, c0446a);
    }

    protected void m1859a(String str, Bitmap bitmap) {
        if (m1851a(str) == null) {
            this.f973e.put(str, bitmap);
        }
    }

    public int getCount() {
        return this.f970b.size();
    }

    public Filter getFilter() {
        if (this.f974f == null) {
            this.f974f = new C0458c();
        }
        return this.f974f;
    }

    public Object getItem(int i) {
        return this.f970b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f972d.inflate(C0622e.row_sortable_layout, null);
        }
        TextView textView = (TextView) view.findViewById(C0621d.headline);
        TextView textView2 = (TextView) view.findViewById(C0621d.baseline);
        ImageView imageView = (ImageView) view.findViewById(C0621d.item_image);
        C0446a c0446a = (C0446a) this.f970b.get(i);
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
                Bitmap a = m1851a(file.getAbsolutePath());
                if (a != null) {
                    imageView.setImageBitmap(a);
                } else if (C0459d.m1855a(file, imageView)) {
                    C0457b c0457b = new C0457b(this, imageView);
                    Resources resources = WDCore.m2174a().getResources();
                    imageView.setImageDrawable(new C0456a(resources, BitmapFactory.decodeResource(resources, C0620c.fm_type_image), c0457b));
                    c0457b.execute(new File[]{file});
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
        return view;
    }
}
