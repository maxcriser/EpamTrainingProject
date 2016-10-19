package com.maxcriser.cards;

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

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TypesCardsReaderTest {

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
        System.out.println("Before");
    }

    @After
    public void close() {
        System.out.println("After");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}