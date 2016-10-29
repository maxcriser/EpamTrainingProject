package com.maxcriser.databasetester;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private static final int CM_DELETE_ID = 1;
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // открываем подключение к БД
        db = new DB(this);
        db.open();

        // формируем столбцы сопоставления
        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };

        // создаем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);
        lvData.setAdapter(scAdapter);

        // добавляем контекстное меню к списку
        registerForContextMenu(lvData);

        // создаем лоадер для чтения данных
        getSupportLoaderManager().initLoader(0, null, this);
    }

    // обработка нажатия кнопки
    public void onButtonClick(View view) {
        // добавляем запись
        db.addRec("sometext " + (scAdapter.getCount() + 1), R.drawable.ic_launcher);
        // получаем новый курсор с данными
        getSupportLoaderManager().getLoader(0).forceLoad();
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем из пункта контекстного меню данные по пункту списка
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            // извлекаем id записи и удаляем соответствующую запись в БД
            db.delRec(acmi.id);
            // получаем новый курсор с данными
            getSupportLoaderManager().getLoader(0).forceLoad();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        // закрываем подключение при выходе
        db.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        return new MyCursorLoader(this, db);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().getLoader(0).forceLoad();

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void next(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
    }

    static class MyCursorLoader extends CursorLoader {

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }
}