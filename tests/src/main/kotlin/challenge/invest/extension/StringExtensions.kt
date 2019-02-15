package challenge.invest.extension

import android.support.annotation.RestrictTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isTextDisplayed() = onView(withText(this)).check(matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.doesNotExists() = onView(withText(this)).check(ViewAssertions.doesNotExist())

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.clickInText() = onView(withText(this)).perform(click())