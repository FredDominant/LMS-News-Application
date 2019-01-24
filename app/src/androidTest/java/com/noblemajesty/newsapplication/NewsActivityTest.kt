package com.noblemajesty.newsapplication

import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import com.noblemajesty.newsapplication.views.*
import junit.framework.Assert.assertTrue
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

    private fun goToFragment(fragment: BaseFragment) {
        getActivity().goToFragment(fragment)
    }

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

    @Test
    fun newsFragment_shouldBeDisplayedProperly() {
        goToFragment(NewsFragment())
        onView(withId(R.id.newsSwipeRefresh))
                .check(matches(allOf(not(isClickable()), isDisplayed(), not(isFocusable()))))
        assert(R.id.progressBar != null)
        onView(withId(R.id.newsRecyclerView))
                .check(matches(allOf(isDisplayed(), not(isClickable()))))
        onView(withId(R.id.bottomNavigationBar))
                .check(matches(allOf(isCompletelyDisplayed(),
                        instanceOf(BottomNavigationView::class.java),
                        not(isFocusable()), isEnabled())))
    }

    @Test
    fun newsFragment_shouldBeLaunchedWhenClickedInTheBottomNavigation() {
        goToFragment(SportsFragment())
        onView(withId(R.id.news))
                .perform(click())
        onView(withId(R.id.newsSwipeRefresh))
                .check(matches(allOf(not(isClickable()), isDisplayed(), not(isFocusable()))))
        assertTrue(getActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) is NewsFragment)
    }

    @Test
    fun sportsFragment_shouldBeLaunchedWhenClickedInTheBottomNavigation() {
        goToFragment(FoodFragment())
        onView(withId(R.id.sports))
                .perform(click())
        onView(withId(R.id.sportsSwipeRefresh))
                .check(matches(allOf(not(isClickable()), isDisplayed(), not(isFocusable()))))
        assertTrue(getActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) is SportsFragment)
    }

    @Test
    fun foodFragment_shouldBeLaunchedWhenClickedInTheBottomNavigation() {
        goToFragment(SportsFragment())
        onView(withId(R.id.food))
                .perform(click())
        onView(withId(R.id.foodSwipeRefresh))
                .check(matches(allOf(not(isClickable()), isDisplayed(), not(isFocusable()))))
        assertTrue(getActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) is FoodFragment)
    }

}