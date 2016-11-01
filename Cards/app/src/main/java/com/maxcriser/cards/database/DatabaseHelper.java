package com.maxcriser.cards.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.maxcriser.cards.database.annotations.Table;
import com.maxcriser.cards.database.annotations.dbBlob;
import com.maxcriser.cards.database.annotations.dbBoolean;
import com.maxcriser.cards.database.annotations.dbFloat;
import com.maxcriser.cards.database.annotations.dbInteger;
import com.maxcriser.cards.database.annotations.dbString;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SQL_NAME = "cardsDatabase";
    private static final String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s";

    public DatabaseHelper(final Context context, final int version) {
        super(context, SQL_NAME, null, version);
    }

    @Nullable
    public static String getTableName(final AnnotatedElement clazz) {
        final Table table = clazz.getAnnotation(Table.class);

        if (table != null) {
            return table.name();
        } else {
            return null;
        }
    }

    @Nullable
    public static String getTableCreateQuery(final Class<?> clazz) {
        final Table table = clazz.getAnnotation(Table.class);

        if (table != null) {
            try {
                final String name = table.name();

                final StringBuilder builder = new StringBuilder();
                final Field[] fields = clazz.getFields();

                for (int i = 0; i < fields.length; i++) {
                    final Field field = fields[i];

                    final Annotation[] annotations = field.getAnnotations();

                    String type = null;

                    for (final Annotation annotation : annotations) {
                        if (annotation instanceof dbInteger) {
                            type = ((dbInteger) annotation).value();
                        } else if (annotation instanceof dbFloat) {
                            type = ((dbFloat) annotation).value();
                        } else if (annotation instanceof dbString) {
                            type = ((dbString) annotation).value();
                        } else if (annotation instanceof dbBlob) {
                            type = ((dbBlob) annotation).value();
                        } else if (annotation instanceof dbBoolean) {
                            type = ((dbBoolean) annotation).value();
                        }
                    }

                    if (type == null) {
                        return null;
                    }

                    final String value = (String) field.get(null);

                    builder.append(String.format(Locale.US, SQL_TABLE_CREATE_FIELD_TEMPLATE, value, type));

                    if (i < fields.length - 1) {
                        builder.append(",");
                    }
                }

                return String.format(Locale.US, SQL_TABLE_CREATE_TEMPLATE, name, builder);
            } catch (final Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        for (final Class<?> clazz : ModelList.MODELS) {
            final String sql = getTableCreateQuery(clazz);

            if (sql != null) {
                db.execSQL(sql);
            }
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

    }

    public Cursor query(final String sql, final String... args) {
        final SQLiteDatabase database = getReadableDatabase();

        return database.rawQuery(sql, args);
    }

    public long insert(final Class<?> table, final ContentValues values) {
        final String name = getTableName(table);

        if (name != null) {
            final SQLiteDatabase database = getWritableDatabase();
            long id;

            try {
                database.beginTransaction();

                id = database.insert(name, null, values);

                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }

            return id;
        } else {
            throw new RuntimeException();
        }
    }

    public int bulkInsert(final Class<?> table, final List<ContentValues> values) {
        final String name = getTableName(table);

        if (name != null) {
            final SQLiteDatabase database = getWritableDatabase();
            int count = 0;

            try {
                database.beginTransaction();

                for (final ContentValues value : values) {
                    database.insert(name, null, value);

                    count++;
                }

                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }

            return count;
        } else {
            throw new RuntimeException();
        }
    }

    public int delete(final Class<?> table, final String sql, final String... args) {
        final String name = getTableName(table);

        if (name != null) {
            final SQLiteDatabase database = getWritableDatabase();
            int count = 0;

            try {
                database.beginTransaction();

                count = database.delete(name, sql, args);

                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
            return count;
        } else {
            throw new RuntimeException();
        }
     }
}
