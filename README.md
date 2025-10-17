# My Calculator App

A simple, functional, and modern calculator application for Android, built with Kotlin.![Calculator Screenshot](<INSERT_SCREENSHOT_URL_HERE>)

*Note: You should take a screenshot of the app running and replace the placeholder link above.*

## About The Project

This project is a complete Android calculator application that demonstrates modern Android development practices. It features a clean, intuitive user interface inspired by contemporary calculator designs and handles all basic arithmetic operations.

A key feature of this project is its comprehensive test suite, built using a **Behavior-Driven Development (BDD)** methodology with the **Cucumber** framework. This ensures that the application logic is robust, reliable, and behaves exactly as expected from a user's perspective.

## Features

*   **Clean, Modern UI:** A minimalist and user-friendly interface.
*   **Standard Operations:** Addition, subtraction, multiplication, and division.
*   **Additional Functions:** Percentage and decimal point support.
*   **Expression Display:** Shows the full calculation (e.g., "2+2") before you get the result.
*   **Input Correction:** Backspace functionality to easily correct mistakes.
*   **Robust Testing:** High test coverage using BDD (Cucumber) to validate application behavior.

## Tech Stack & Tools

*   **Language:** [Kotlin](https://kotlinlang.org/)
*   **UI:** [XML Layouts](https://developer.android.com/guide/topics/ui/declaring-layout) with `ConstraintLayout`
*   **Testing Frameworks:**
    *   [Cucumber](https://cucumber.io/) for Behavior-Driven Development (BDD)
    *   [Espresso](https://developer.android.com/training/testing/espresso) for UI testing
    *   [JUnit](https://junit.org/junit5/) for unit testing

## Testing

This project emphasizes a strong testing culture using Behavior-Driven Development (BDD).

*   **Feature Files (`.feature`):** Test cases are written in a human-readable Gherkin syntax that describes the app's behavior from a user's point of view. You can find these in `app/src/androidTest/assets/features/`.
*   **Step Definitions (`.kt`):** The Gherkin steps are linked to Kotlin code that drives the application's UI and verifies the results using Espresso. You can find this "glue code" in `app/src/androidTest/java/com/example/mycalculatorapp/CalculatorSteps.kt`.

This approach ensures that the app not only works but works in the way users expect it to.

## How To Get Started

1.  Clone the repository:
    
