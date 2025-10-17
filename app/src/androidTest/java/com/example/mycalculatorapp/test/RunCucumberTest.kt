package com.example.mycalculatorapp.test

import io.cucumber.junit.CucumberOptions

@CucumberOptions(
    features = ["features"],
    glue = ["com.example.mycalculatorapp"],
    plugin = ["pretty"],
)
class RunCucumberTest {}



