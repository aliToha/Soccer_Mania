package com.alimuthohhari.premierleagueschedule.main


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.alimuthohhari.premierleagueschedule.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import android.support.test.espresso.IdlingRegistry
import org.junit.Before
import org.junit.After


@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainNavigation::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun mainNavigationTest3() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.schedule), withContentDescription("Schedule"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_nav),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val viewPager = onView(
            allOf(
                withId(R.id.pager_main),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager.perform(swipeLeft())

        val tabView = onView(
            allOf(
                withContentDescription("LAST EVENT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs_event),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val viewPager2 = onView(
            allOf(
                withId(R.id.pager_match),
                childAtPosition(
                    withParent(withId(R.id.pager_main)),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager2.perform(swipeLeft())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.team), withContentDescription("Team"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_nav),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val viewPager3 = onView(
            allOf(
                withId(R.id.pager_main),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager3.perform(swipeLeft())

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.favorite), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_nav),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        val viewPager4 = onView(
            allOf(
                withId(R.id.pager_main),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager4.perform(swipeLeft())

        val tabView2 = onView(
            allOf(
                withContentDescription("MATCH"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs_event),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        val viewPager5 = onView(
            allOf(
                withId(R.id.pager_favorite),
                childAtPosition(
                    withParent(withId(R.id.pager_main)),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager5.perform(swipeLeft())

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.search), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar_main),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("arsenal"), closeSoftKeyboard())

        val tabView3 = onView(
            allOf(
                withContentDescription("TEAMS"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_search),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView3.perform(click())

        val viewPager6 = onView(
            allOf(
                withId(R.id.pager_search),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager6.perform(swipeLeft())

        val tabView4 = onView(
            allOf(
                withContentDescription("MATCH"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_search),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView4.perform(click())

        val viewPager7 = onView(
            allOf(
                withId(R.id.pager_search),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager7.perform(swipeLeft())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.search_close_btn), withContentDescription("Clear query"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.search_close_btn), withContentDescription("Clear query"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
