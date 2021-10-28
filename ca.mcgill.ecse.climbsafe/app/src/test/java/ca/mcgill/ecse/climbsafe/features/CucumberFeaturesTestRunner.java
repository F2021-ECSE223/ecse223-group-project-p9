package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", updateEquipmentBundle = "src/test/resources/addEquipment.feature")
public class CucumberFeaturesTestRunner {
}