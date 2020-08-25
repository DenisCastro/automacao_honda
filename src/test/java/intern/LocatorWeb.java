package intern;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class LocatorWeb {
    void locate() {
        JavascriptExecutor js = (JavascriptExecutor) Instances.getWebDriver();
        if (Instances.getWebLastElements() != null) {
            try {
                for (WebElement element : Instances.getWebLastElements()) {
                    js.executeScript("arguments[0].style.border='1px solid orange'", element);
                }
            } catch (WebDriverException e) {
                System.out.println("exception pintando pagina laranja: ");
            }
        }
        int tempoDeEspera = Instances.getDefaultWaitMilis();
        try {
            String estadoDaPagina = String.valueOf(((JavascriptExecutor) Instances.getWebDriver()).executeScript("return document.readyState"));
            System.out.println("carregamento da pagina: " + estadoDaPagina);

            if (Instances.getPageLoad()) {
                if (estadoDaPagina.equals("complete")) {
                    tempoDeEspera = 1000;
                }
            }
        }catch (WebDriverException e){
            System.out.println("exception javascript: "+String.valueOf(e).split("\n")[0]);
        }

        List<WebElement> elements = new ArrayList<>();
        Set<String> windows = Instances.getWebDriver().getWindowHandles();
        System.out.println("Amount of pages found: " + windows.size());
        int contadorDePagina = 1;
        for (String pagina : windows) {
            System.out.println("Trying the page: " + contadorDePagina);
            contadorDePagina ++;
            int localTime = tempoDeEspera;
            while (true) {
                long ti = Calendar.getInstance().getTimeInMillis();
                long tf = Calendar.getInstance().getTimeInMillis();
                int tr = (int) (tf - ti);
                Instances.getWebDriver().switchTo().window(pagina);
                try {
                    if (Instances.getWebLastXpath().startsWith("/") || Instances.getWebLastXpath().startsWith("(")) {
                        elements = Instances.getWebDriver().findElements(By.xpath(Instances.getWebLastXpath()));
                    } else {
                        elements = Instances.getWebDriver().findElements(By.id(Instances.getWebLastXpath()));
                    }
                } catch (IllegalArgumentException e1) {
                    Instances.getWebDriver().switchTo().alert();
                } catch (NoAlertPresentException ignored) {

                }
                tf = Calendar.getInstance().getTimeInMillis();
                tr = (int) (tf - ti);
                localTime = localTime - tr;
                if (localTime <= 0) {
                    break;
                }
                if (elements.size() > 0) {
                    break;
                } else {
                    int quantidadeDeFrames = Instances.getWebDriver().findElements(By.xpath("//frame")).size();
                    quantidadeDeFrames += Instances.getWebDriver().findElements(By.xpath("//iframe")).size();
                    if (quantidadeDeFrames > 0) {
                        for (int i = 0; i < quantidadeDeFrames; i++) {
                            ti = Calendar.getInstance().getTimeInMillis();
                            try {
                                Instances.getWebDriver().switchTo().frame(i);
                                if (Instances.getWebLastXpath().startsWith("/") || Instances.getWebLastXpath().startsWith("(")) {
                                    elements = Instances.getWebDriver().findElements(By.xpath(Instances.getWebLastXpath()));
                                } else {
                                    elements = Instances.getWebDriver().findElements(By.id(Instances.getWebLastXpath()));
                                }
                                if (elements.size() > 0) {
                                    break;
                                }
                            } catch (IllegalArgumentException e1) {
                                Instances.getWebDriver().switchTo().alert();
                            } catch (NoAlertPresentException | org.openqa.selenium.NoSuchFrameException ignored) {

                            }
                            tf = Calendar.getInstance().getTimeInMillis();
                            tr = (int) (tf - ti);
                            localTime = localTime - tr;
                            if (localTime <= 0) {
                                break;
                            }
                        }
                    }
                }
                if (elements.size() > 0) {
                    break;
                }
            }
            if (elements.size() > 0) {
                break;
            }
        }

        int count = elements.size();
        if (count > 1) {
            System.out.println("The xpath '" + Instances.getWebLastXpath() + "' returned " + count + " elements");
        } else if (count == 1) {
            System.out.println("The xpath '" + Instances.getWebLastXpath() + "' returned one element");
        } else {
            System.out.println("The xpath '" + Instances.getWebLastXpath() + "' returned no elements");
        }

        try {
            for (WebElement element : elements) {
                js.executeScript("arguments[0].style.border='3px solid red'", element);
            }
        }catch (WebDriverException e){
            System.out.println("exception pintando borda vermelho: "+String.valueOf(e).split("\n")[0]);
        }

        Instances.setLastWindows(Instances.getWebDriver().getWindowHandles());
        Instances.setLastIeratos(Instances.getLastWindows().iterator());

        Instances.setWebLastElements(elements);
    }
}
