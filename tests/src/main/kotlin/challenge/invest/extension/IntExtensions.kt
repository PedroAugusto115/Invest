package challenge.invest.extension

import android.support.annotation.RestrictTo
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isDisplayed() = onView(withId(this)).check(matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isNotDisplayed() = onView(withId(this)).check(matches(not(ViewMatchers.isDisplayed())))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.doesNotExists() = onView(withId(this)).check(ViewAssertions.doesNotExist())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isDisabled() = onView(withId(this)).check(matches(not(ViewMatchers.isEnabled())))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isEnabled() = onView(withId(this)).check(matches(ViewMatchers.isEnabled()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.hasText(text: String) = onView(withId(this)).check(matches(withText(text)))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.clickInId() = onView(withId(this)).perform(ViewActions.click())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isTextDisplayed() = onView(withText(this)).check(matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.typeText(message: String) = onView(withId(this)).perform(ViewActions.typeText(message))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.cleanText() = onView(withId(this)).perform(ViewActions.clearText())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.typeTextInChild(childId: Int, message: String) = onView(allOf(withId(childId), withParent(withId(this))))
        .perform(ViewActions.typeText(message))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.clearTextInChild(childId: Int) = onView(allOf(withId(childId), withParent(withId(this))))
        .perform(ViewActions.clearText())