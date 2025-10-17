package com.example.mycalculatorapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class CalculatorSteps {
    @Given("^the calculator is open$")
    fun theCalculatorIsOpen() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @When("^I enter ([-+]?\\d+(?:\\.\\d+)?)$")
    fun iEnterNumberRaw(input: String) {
        input.forEach { ch ->
            val id = when (ch) {
                '-' -> R.id.btn_sub
                '0' -> R.id.btn_0
                '1' -> R.id.btn_1
                '2' -> R.id.btn_2
                '3' -> R.id.btn_3
                '4' -> R.id.btn_4
                '5' -> R.id.btn_5
                '6' -> R.id.btn_6
                '7' -> R.id.btn_7
                '8' -> R.id.btn_8
                '9' -> R.id.btn_9
                '.' -> R.id.btn_dot
                else -> throw IllegalArgumentException("Unsupported character in number: $ch")
            }
            onView(withId(id)).perform(click())
        }
    }

    @When("^I press add$")
    fun iPressAdd() { onView(withId(R.id.btn_add)).perform(click()) }

    @When("^I press subtract$")
    fun iPressSubtract() { onView(withId(R.id.btn_sub)).perform(click()) }

    @When("^I press multiply$")
    fun iPressMultiply() { onView(withId(R.id.btn_mul)).perform(click()) }

    @When("^I press divide$")
    fun iPressDivide() { onView(withId(R.id.btn_div)).perform(click()) }

    @When("^I press equals$")
    fun iPressEquals() { onView(withId(R.id.btn_eq)).perform(click()) }

    @When("^I press percent$")
    fun iPressPercent() { onView(withId(R.id.btn_percent)).perform(click()) }

    @When("^I press AC$")
    fun iPressAC() { onView(withId(R.id.btn_clear)).perform(click()) }

    @Then("^the result should be ([-+]?\\d+(?:\\.\\d+)?)$")
    fun theResultShouldBe(expected: String) {
        onView(withId(R.id.txt_display)).check(matches(withText(expected)))
    }

    @Then("^the display should show \"([^\"]*)\"$")
    fun theDisplayShouldShow(expected: String) {
        onView(withId(R.id.txt_display)).check(matches(withText(expected)))
    }
}

