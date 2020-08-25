package intern.plugin;

import cucumber.api.PickleStepTestStep;
import cucumber.api.event.*;
import intern.Instances;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class ListCucumberExecute{

    private static boolean started = false;
    public static void testStepStarted(TestStepStarted event) {
        try {
            PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;
            System.out.println("BDD> " + ((PickleStepTestStep) event.testStep).getStepText());
            Instances.getReportClassInstance().createBDD(((PickleStepTestStep) event.testStep).getStepText());
            Instances.setExecutionActionTimer(Calendar.getInstance().getTimeInMillis());
        } catch (Exception ignored) {
        }
    }
    
    public static void testStepFinished(TestStepFinished event){
        System.out.println("=====================================================");
    }
    
    public static void testCaseStarted(TestCaseStarted event){
        String relativePath = event.testCase.getUri();
        String raizProduto = "";
        if(relativePath.contains("produtos/features/")){
            raizProduto = relativePath.split("produtos/features/")[1];
        }else {
            raizProduto = relativePath.split("produtos/")[1];
        }

        Instances.mongoNameOfProject = raizProduto.split("/")[0];

        System.out.println("===== INICIANDO CASO DE TESTE: " + event.testCase.getName() + " ==============");

        System.out.println("pacote: " + event.testCase.getUri());

        String title = "Não definido";
        String author = "Não definido";
        String category = "Não definido";
        String environment = "Não definido";
        String[] path = event.testCase.getUri().split("/");

        String[] productPathArray;
        if(event.testCase.getUri().contains("/produtos/features/")){
            productPathArray = event.testCase.getUri().split("/produtos/features/");
        }else{
            productPathArray = event.testCase.getUri().split("/produtos/");
        }
        String[] productNameArray = productPathArray[1].split("/");
        Instances.setProduct(productNameArray[0]);

        try {
            String[] partesScenarioTitle = event.testCase.getName().split("Author:");
            title = partesScenarioTitle[0].replace(",", "").trim();
            String[] partesScenarioAuthorAndCategory = partesScenarioTitle[1].split("Category:");
            author = partesScenarioAuthorAndCategory[0].replace(",", "").trim();
            String[] partesScenarioCategoryAndEnvironment = partesScenarioAuthorAndCategory[1].split("Environment:");
            category = partesScenarioCategoryAndEnvironment[0].replace(",", "").trim();
            environment = partesScenarioCategoryAndEnvironment[1].replace(",", "").trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            String[] partesScenarioTitle = event.testCase.getName().split("Autor:");
            title = partesScenarioTitle[0].replace(",", "").trim();
            String[] partesScenarioAuthorAndCategory = partesScenarioTitle[1].split("Categoria:");
            author = partesScenarioAuthorAndCategory[0].replace(",", "").trim();
            category = partesScenarioAuthorAndCategory[1].replace(",", "").trim();

        }

        Instances.setAuthor(author.replace(" ", "_"));
        Instances.setEnvironment(environment.replace(" ", "_"));
        Instances.setCategory(category.replace(" ", "_"));


        if (Instances.getExtent() == null) {
            try {
                FileUtils.deleteDirectory(new File(Instances.getJenkinsPath()));
            } catch (IOException ignored) {
            }
            Instances.setFeature(path[path.length - 1].replace(".feature", ""));
            Instances.setScenario(title);
            Instances.createInstance();
        }

        if (!Instances.getFeature().equals(path[path.length - 1].replace(".feature", ""))) {
            Instances.setFeature(path[path.length - 1].replace(".feature", ""));
            Instances.createFeatureSection();
            Instances.scenario = "";
            Instances.step = "";
        }

        if (started) {
            Instances.setScenario(title);
            Instances.createScenarioSection();
        } else {
            started = true;
        }
    }
    
    public static void testCaseFinished(TestCaseFinished event){
        System.out.println("===== FINALIZANDO CASO DE TESTE: " + event.testCase.getName() + " ==============");
        if (!Instances.getTestsKilled()) {
            if (
                    event.result.getStatus().toString().equals("FAILED")
                            && !event.result.getErrorMessage().contains("[FALHA]")
                            && !event.result.getErrorMessage().contains("[ALERTA]")
                            && !event.result.getErrorMessage().contains("[AMBIENTE]")
            ) {
                System.out.println(event.result.getErrorMessage());
                Instances.getReportClassInstance().stepError(event.result.getError());
            }
            if (event.result.getStatus().toString().equals("UNDEFINED")) {
                Instances.getReportClassInstance().stepError(event.result.getError());
            }
        }
        Instances.setTestsKilled(false);
        System.out.println("result: " + event.result.getStatus());
        Instances.killWebDriver();
    }
    
    public static void testRunStarted(TestRunStarted event){
        System.out.println("-------INICIANDO TESTES-------");
        System.out.println("Usuário de execução: " + System.getProperty("user.name"));
    }
    
    public static void testRunFinished(TestRunFinished event){
        System.out.println("-------FINALIZANDO TESTES-------");
        System.out.println("Removendo processos remanescentes do chromedriver");
        Instances.commandEx("taskkill /F /IM \"chromedriver.exe\" /T");
        System.out.println("--------------------------------");
        Instances.mongoCategoryOfExecution = "desenv";
    }
    
}
