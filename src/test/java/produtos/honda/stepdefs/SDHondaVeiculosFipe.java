package produtos.honda.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import produtos.honda.objetos.HondaVeiculosFipe;
import support.CoreWeb;
import support.tbi.Excel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static produtos.honda.objetos.HondaVeiculosFipe.*;

public class SDHondaVeiculosFipe extends CoreWeb {

    public static String tipoVeiculo = "";
    public static String veiculoMarca = "";
    public static String veiculoModelo = "";
    public static String veiculoAno = "";

    @Given("O usuario acessa a tela de preco medio veioculos fipe")
    public void oUsuarioAcessaATelaDePrecoMedioVeioculosFipe() {
        webDriver().set().options().maximized();
        webDriver().navigate("https://veiculos.fipe.org.br/");
        if (find(hondaVeiculosFipeTextPrecoMedioVeiculo).exists()) {
            evidence("Página preço médio de veículos exibida");
        } else {
            find(hondaVeiculosFipeButtonMenuLateralPrecoMedioVeiuclos).click();
            sleep().untilAppear(hondaVeiculosFipeTextPrecoMedioVeiculo);
        }
    }

    @When("O usuario faz a consulta dos veiculos presentes na planilia")
    public void oUsuarioFazAConsultaDosVeiculosPresentesNaPlanilia() {
        log().setLocator(hondaVeiculosFipe);
        scroll().down(1300);

        List<List<String>> conteudoPlanilia = Excel.lerPlanilhaXlsxComEspacoEmBranco(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros");
        System.out.println("****************************************************************************************************");
        System.out.println(conteudoPlanilia);
        String conteudoLinhaEmail = "            <tr>\n" +
                "                <td class=\"td_tabela\"> arg0 </td>\n" +
                "                <td class=\"td_tabela\"> arg1 </td>\n" +
                "                <td class=\"td_tabela\"> arg2 </td>\n" +
                "                <td class=\"td_tabela\"> arg3 </td>\n" +
                "                <td class=\"td_tabela\"> arg4 </td>\n" +
                "                <td class=\"td_tabela\"> arg5 </td>\n" +
                "            </tr>";
        StringBuilder sbConteudoEmail = new StringBuilder();
        int linhaAtual = 0;
        int colunaModelo = 4;
        int colunaPreco = 5;
        for (List<String> linha : conteudoPlanilia) {
                if (linha.get(0).trim().startsWith("entrada") || linha.get(0).trim().startsWith("carro/moto")) {
                    linhaAtual++;
                    continue;
                }
                Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "");
                Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "");
                linhaAtual++;
        }
        linhaAtual = 0;
        for (List<String> linha : conteudoPlanilia) {
                if (linha.get(0).trim().startsWith("entrada") || linha.get(0).trim().startsWith("carro/moto")) {
                    linhaAtual++;
                    continue;
                }
                System.out.println(" antes do linha.size < 4:" + linha);
                System.out.println(linha);
                if (linha.size() < 4) {
                    System.out.println("entrou no if linha.size < 4");
                    // percorre todas as celulas da linha
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                    linhaAtual++;
                    continue;
                }
                tipoVeiculo = linha.get(0);
                veiculoMarca = linha.get(1);
                veiculoModelo = linha.get(2);
                veiculoAno = linha.get(3);
                if (veiculoAno.contains(".0")) {
                    veiculoAno = veiculoAno.substring(0, 4);
                }
                String conteudoLinhaEmailTemporaria = "";
                System.out.println("ANTES DO SWITCH CASE");
                if (tipoVeiculo != "carro" || tipoVeiculo != "moto"){
                    System.out.println("entrou no if de tipo de veiculo diferente de carro ou moto");
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                    conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                    conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                }
                switch (tipoVeiculo) {
                    case "carro":
                        System.out.println("ANTES DO CASE CARRO");
                        sleep().setMaxTime(500);
                        if (find(hondaVeiculosFipeButtonAbrirConsultaCarros).exists()) {
                            find(hondaVeiculosFipeButtonConsultaCarros).click();
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoMarcaCarro).click();
                        find(hondaVeiculosFipeInputMarcaVeiculoCarro).send().text(veiculoMarca);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaMarcaCarro.replace("arg0", veiculoMarca)).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaMarcaCarro.replace("arg0", veiculoMarca)).click();
                        } else {
                            find(hondaVeiculosFipeInputMarcaVeiculoCarro).clear();
                            sleep().setDefaultTime();
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoModeloCarro).click();
                        find(hondaVeiculosFipeInputModeloVeiculoCarro).send().text(veiculoModelo);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaModeloCarro.replace("arg0", veiculoModelo)).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaModeloCarro.replace("arg0", veiculoModelo)).click();
                        } else {
                            find(hondaVeiculosFipeInputModeloVeiculoCarro).clear();
                            sleep().setDefaultTime();
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoAnoModeloCarro).click();
                        find(hondaVeiculosFipeInputAnoModeloVeiculoCarro).send().text(veiculoAno);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaAnoModeloCarro.replace("arg0", veiculoAno)).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaAnoModeloCarro.replace("arg0", veiculoAno)).click();
                        } else {
                            find(hondaVeiculosFipeInputAnoModeloVeiculoCarro).clear();
                            sleep().setDefaultTime();
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonPesquisarCarro).click();
                        conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                        String modeloCarro = find(hondaVeiculosFipeTextModeloCarroResultadoPesquisa).get().text().toString();
                        Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, modeloCarro);
                        String precoCarro = find(hondaVeiculosFipeTextResultadoPrecoCarro).get().text().toString();
                        Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, precoCarro);
                        conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg5", precoCarro);
                        find(hondaVeiculosFipeButtonLimparPesquisaCarro).click();
                        sleep().setDefaultTime();
                        break;
                    case "moto":
                        System.out.println("ANTES DO CASE MOTO");
                        sleep().setMaxTime(500);
                        if (find(hondaVeiculosFipeButtonConsultaMotoFechado).exists()) {
                            find(hondaVeiculosFipeButtonAbrirConsultaMoto).click();
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoMarcaMoto).click();
                        find(hondaVeiculosFipeInputMarcaMoto).send().text(veiculoMarca);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaMarcaMoto).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaMarcaMoto).click();
                        } else {
                            find(hondaVeiculosFipeInputMarcaMoto).clear();
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoModeloMoto).click();
                        find(hondaVeiculosFipeInputModeloMoto).send().text(veiculoModelo);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaModeloMoto.replace("arg0", veiculoModelo)).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaModeloMoto.replace("arg0", veiculoModelo)).click();
                        } else {
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonAbrirCampoAnoModeloMoto).click();
                        find(hondaVeiculosFipeInputAnoModeloVeiculoMoto).send().text(veiculoAno);
                        if (find(hondaVeiculosFipeTextRetornoPesquisaAnoModeloMoto.replace("arg0", veiculoAno)).exists()) {
                            find(hondaVeiculosFipeTextRetornoPesquisaAnoModeloMoto.replace("arg0", veiculoAno)).click();
                        } else {
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                            Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                            conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg4", "Falha").replace("arg5", "Falha");
                            break;
                        }
                        find(hondaVeiculosFipeButtonPesquisarMoto).click();
                        conteudoLinhaEmailTemporaria = conteudoLinhaEmail;
                        String modeloMoto = find(hondaVeiculosFipeTextModeloMotoResultadoPesquisa).get().text().toString();
                        Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, modeloMoto);
                        String precoMoto = find(hondaVeiculosFipeTextResultadoPrecoMoto).get().text().toString();
                        Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, precoMoto);
                        conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg5", precoMoto);
                        find(hondaVeiculosFipeButtonLimparPesquisaMoto).click();
                        sleep().setDefaultTime();
                        break;
                }
                System.out.println("FORA DO SWITH CASE");
                linhaAtual++;
                conteudoLinhaEmailTemporaria = conteudoLinhaEmailTemporaria.replace("arg0", tipoVeiculo).replace("arg1", veiculoMarca).replace("arg2", veiculoModelo).replace("arg3", veiculoAno).replace("arg4", veiculoModelo);
                sbConteudoEmail.append(conteudoLinhaEmailTemporaria);
        }
        try {
            FileUtils.copyFile(new File(hondaVeiculosFipePathPlanilha), new File(hondaVeiculosFipePathPlanilhaAnexo));
            FileUtils.copyFile(new File(hondaVeiculosFipePathConteudoEmail), new File(hondaVeiculosFipePathConteudoEmailAnexo));
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(hondaVeiculosFipePathConteudoEmailAnexo));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String conteudoDoEmail = sb.toString();
            conteudoDoEmail = conteudoDoEmail.replace("arg6", sbConteudoEmail.toString());
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(hondaVeiculosFipePathConteudoEmailAnexo, false), StandardCharsets.UTF_8))) {
                writer.write(conteudoDoEmail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("O usuario preenche os campos de saida da pesquisa dos veiculos que falharam")
    public void oUsuarioPreencheOsCamposDeSaidaDaPesquisaDosVeiculosQueFalharam() {
        int linhaAtual = 0;
        int colunaModelo = 4;
        int colunaPreco = 5;
        List<List<String>> conteudoPlanilia = Excel.lerPlanilhaXlsxComEspacoEmBranco(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros");
        for (List<String> linha : conteudoPlanilia) {
            if (linha != null && linha.size() > 0 && linha.get(0) != null && linha.get(0).length() > 0) {
                if (linha.get(0).trim().startsWith("entrada") || linha.get(0).trim().startsWith("carro/moto")) {
                    linhaAtual++;
                    continue;
                }
                if (linha.size() <= 4) {
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaModelo, "Falha");
                    Excel.escreverPlanilhaXlsx(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros", linhaAtual, colunaPreco, "Falha");
                }
                linhaAtual++;
            }
        }
    }

    @Then("O usuario apresenta o resultado da busca de preco veiculos")
    public void oUsuarioApresentaOResultadoDaBuscaDePrecoVeiculos() {
        List<List<String>> conteudoPlanilia = Excel.lerPlanilhaXlsxComEspacoEmBranco(HondaVeiculosFipe.hondaVeiculosFipePathPlanilha, "Carros");
        int linhaAtual = 0;
        for (List<String> linha : conteudoPlanilia) {
            if (linha != null && linha.size() > 0 && linha.get(0) != null && linha.get(0).length() > 0) {
                if (linha.get(0).trim().startsWith("entrada") || linha.get(0).trim().startsWith("carro/moto")) {
                    System.out.println("entrou no if linha entrada ou carro/moto");
                    linhaAtual++;
                    continue;
                }
                if (linha.size() > 3){
                    log().type("<b>Veículo:</b> " + linha.get(4) + ("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;") + " <b>Valor:</b> " + linha.get(5));
                    linhaAtual++;
                }
            }
        }
    }


}

