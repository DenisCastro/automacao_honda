package produtos.honda.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/produtos/features/honda/",
        glue={"produtos.honda.stepdefs"},
        plugin = { "intern.plugin.ListCucumberDesenv" }
        ,tags = {"@POCConsultaFipe"}
)
public class RunDesenv {
}


