package com.shunm.android.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import com.github.takahirom.roborazzi.captureRoboImage

fun runRoborazziRobotTest(
    setContent: @Composable context(ComposeUiTest) () -> Unit,
    block: context(ComposeUiTest) RoborazziRobot.() -> Unit,
) {
    runComposeUiTest {
        setContent {
            setContent()
        }
        RoborazziRobotImpl().apply {
            block()
        }
    }
}

sealed interface RoborazziRobot {
    context(composeUiTest: ComposeUiTest)
    fun captureScreenWithChecks(checks: ComposeUiTest.() -> Unit)
}

private class RoborazziRobotImpl : RoborazziRobot {
    context(composeUiTest: ComposeUiTest)
    override fun captureScreenWithChecks(checks: ComposeUiTest.() -> Unit) {
        checks(composeUiTest)
        composeUiTest.onRoot().captureRoboImage()
    }
}
