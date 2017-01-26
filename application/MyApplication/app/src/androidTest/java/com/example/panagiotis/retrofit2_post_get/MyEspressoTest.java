package com.example.panagiotis.retrofit2_post_get;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MyEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;

    public MyEspressoTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception{
        super.setUp();
        mainActivity=getActivity();
    }

    public void testWhiteBox1(){
        String testEmail="example@info.com";
        String testPassword="password";
        onView(withId(R.id.login_user_name)).perform(typeText(testEmail),
                closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText(testPassword),
                closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

    }

    public void testWhiteBox2(){
        String testEmail="example@info.com";
        String testPassword="password";
        onView(withId(R.id.Profile_email)).check(matches(withText(testEmail)));
        onView(withId(R.id.Profile_Password)).check(matches(withText(testPassword)));
    }
}
