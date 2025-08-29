package com.shunm.android.presentation.component.appbar

import androidx.compose.material3.Text
import androidx.compose.ui.test.onNodeWithText
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.shunm.android.presentation.component.runRoborazziRobotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], qualifiers = RobolectricDeviceQualifiers.Pixel4a)
class AppbarTest {
    private val dummyTitle = "Title"
    private val dummySubtitle = "Subtitle"

    @Test
    fun runTest_ClSmallAppbar() {
        runRoborazziRobotTest(
            setContent = {
                ClSmallAppbar(
                    headline = {
                        Text(text = dummyTitle)
                    },
                    subtitle = {
                        Text(text = dummySubtitle)
                    },
                )
            },
        ) {
            captureScreenWithChecks {
                onNodeWithText(dummyTitle).assertExists()
                onNodeWithText(dummySubtitle).assertExists()
            }
        }
    }
}
