package com.noblemajesty.newsapplication

import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import com.noblemajesty.newsapplication.views.NewsActivity
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(NewsActivity::class.java)

    private fun getActivity() = activityRule.activity

    @Test
    fun activityNewsFragmentDisplaysProperly() {
        onView(withId(R.id.fragmentContainer))
                .check(matches(allOf(isDisplayed(), not(isClickable()), hasFocus())))

        onView(withId(R.id.fragmentContainer))
                .check(matches(hasChildCount(1)))
    }

    @Test
    fun activityNewsToolBar() {
        onView(withId(R.id.toolbar))
                .check(matches(allOf(isDisplayed(), not(isFocusable()), isEnabled())))

        onView(withId(R.id.toolbar))
                .check(matches(withToolbarTitle("News Application")))
    }

    @Test
    fun newsActivityNavBarDisplaysProperly() {
        onView(withId(R.id.bottomNavigationBar))
                .check(matches(allOf(isCompletelyDisplayed(),
                        instanceOf(BottomNavigationView::class.java),
                        not(isFocusable()), isEnabled())))
    }

    companion object {
        private fun withToolbarTitle(text: CharSequence): Matcher<Any> {
            return object: BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("with toolbar title to equal $text")
                }

                override fun matchesSafely(item: Toolbar?): Boolean {
                    return (text == item?.title)
                }
            }
        }
    }
}