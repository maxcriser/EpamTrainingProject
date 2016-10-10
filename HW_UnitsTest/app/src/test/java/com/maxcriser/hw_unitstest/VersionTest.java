package com.maxcriser.hw_unitstest;

import android.os.Build;
import android.view.View;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class VersionTest {

    private Version mAndroidVersion;
    private MainActivity mMainActivity;

    @BeforeClass
    public static void start() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("AfterClass");
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mMainActivity = Robolectric.setupActivity(MainActivity.class);
        mAndroidVersion = Mockito.spy(new Version());

        mMainActivity.initVersion(mAndroidVersion);

        System.out.println("Before");
    }

    @After
    public void close() {
        System.out.println("After");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        System.out.println(mMainActivity.buttonUpdate.getText());
    }

    @Test
    public void testApiVersion() throws Exception {
        when(mAndroidVersion.currentVersion()).thenReturn(Build.VERSION_CODES.N).thenReturn(Build.VERSION_CODES.M).thenReturn(Build.VERSION_CODES.KITKAT);
        assertEquals(mAndroidVersion.isUpdate(), false);
        assertEquals(mAndroidVersion.isUpdate(), true);
        assertEquals(mAndroidVersion.isUpdate(), true);
    }

    @Test
    public void testVisibility() throws Exception {
        when(mAndroidVersion.isUpdate()).thenReturn(true).thenReturn(false);

        System.out.println(View.VISIBLE);
        System.out.println(View.INVISIBLE);

        mMainActivity.setVisibility();
        assertEquals(View.VISIBLE, mMainActivity.buttonUpdate.getVisibility());

        mMainActivity.setVisibility();
        assertEquals(View.INVISIBLE, mMainActivity.buttonUpdate.getVisibility());
    }

    @Test
    public void testTextView() throws Exception {
        assertNotNull(mMainActivity);

        assertEquals(mMainActivity.title.getText().toString(), "ANDROID VERSION");
    }
}
