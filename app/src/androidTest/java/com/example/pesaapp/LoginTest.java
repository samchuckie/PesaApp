package com.example.pesaapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    ActivityTestRule<Login> loginActivityTestRule =  new ActivityTestRule<>(Login.class);

    @Test
    public void emptyDetails(){
        onView(withId(R.id.login_btn)).perform(click());
    }

    @Test
    public void loginbuttonClicked(){
    }

    @Test
    public void signupClicked(){
        onView(withId(R.id.login_btn)).perform(click());
        onView(withId(R.id.email)).check(matches(isDisplayed()));

    }
}
