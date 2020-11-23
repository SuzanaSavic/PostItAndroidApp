package suzanasavic.github.com.android.postitapp.ui.post_list

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.Constants.Companion.FIRST_RUN
import suzanasavic.github.com.android.postitapp.MainActivity
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.R.string.preference_file_key
import suzanasavic.github.com.android.postitapp.data.entities.Post


/**
 * Created by suzana.savic on 11/23/2020.
 */
@RunWith(AndroidJUnit4::class)
class PostListFragmentTest {

    private lateinit var context: Context

    @Rule @JvmField var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun isFistRunApp(){
        context = getApplicationContext<App>()

        val isFirstRun = context.getSharedPreferences(
            context.getString(preference_file_key),
            Context.MODE_PRIVATE
        ).getBoolean(FIRST_RUN, true)

        // Assert
        assertTrue(isFirstRun)
    }

    @Test
    fun check_is_post_list_opened_Test(){
        context = getApplicationContext<App>()
        val isFirstRun = context.getSharedPreferences(
            context.getString(preference_file_key),
            Context.MODE_PRIVATE
        ).getBoolean(FIRST_RUN, true)

        if(!isFirstRun) {
            val scenario = launch(MainActivity::class.java)
            //open main activity
            scenario.onActivity {
                //navigateUp (to PostListFragment)
                val navController =
                    TestNavHostController(ApplicationProvider.getApplicationContext())
                navController.setGraph(R.navigation.nav_graph)
                navController.navigateUp()
                //check if it is first run
                if (navController.currentDestination?.id == R.id.postListFragment) {
                    onView(withText(R.string.list)).check(matches(isDisplayed()))
                }
            }
        }
    }
}