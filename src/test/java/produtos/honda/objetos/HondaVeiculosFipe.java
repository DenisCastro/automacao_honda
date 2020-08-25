package produtos.honda.objetos;

import java.util.LinkedHashMap;

public class HondaVeiculosFipe {


    public static String hondaVeiculosFipePathPlanilha = System.getProperty("user.dir") + "\\src\\test\\resources\\produtos\\massas\\honda\\planilha_veiculos_base.xlsx";
    public static String hondaVeiculosFipePathPlanilhaAnexo = System.getProperty("user.dir") + "\\src\\test\\resources\\jenkins\\anexos\\planilha_veiculos_base.xlsx";
    public static String hondaVeiculosFipePathConteudoEmail = System.getProperty("user.dir") + "\\src\\test\\resources\\produtos\\massas\\honda\\conteudo_email.html";
    public static String hondaVeiculosFipePathConteudoEmailAnexo = System.getProperty("user.dir") + "\\src\\test\\resources\\jenkins\\anexos\\email.html";

    public static String hondaVeiculosFipeTextPrecoMedioVeiculo = "//h1[text()=\"Preço Médio de Veículos\"]";
    public static String hondaVeiculosFipeButtonMenuLateralPrecoMedioVeiuclos = "//div[@title=\"Tabela Fipe\"]";
    public static String hondaVeiculosFipeButtonAbrirConsultaCarros = "//div[text()=\"Consulta de Carros e Utilitários Pequenos\"]/ancestor::li[@class=\"ilustra\"]";
    public static String hondaVeiculosFipeButtonConsultaCarros = "//div[text()=\"Consulta de Carros e Utilitários Pequenos\"]";

    public static String hondaVeiculosFipeButtonAbrirCampoMarcaCarro = "//div[@id=\"selectMarcacarro_chosen\"]//b";
    //public static String hondaVeiculosFipeButtonAbrirCampoMarcaCarro = "//div[@id=\"selectMarcacarro_chosen\"]";
    public static String hondaVeiculosFipeInputMarcaVeiculoCarro = "//div[@id=\"selectMarcacarro_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaMarcaCarro = "//div[@id=\"selectMarcacarro_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaMarcaCarro = "//div[@id=\"selectMarcacarro_chosen\"]//li/em[text()=\"arg0\"]";

    public static String hondaVeiculosFipeButtonAbrirCampoModeloCarro = "//div[@id=\"selectAnoModelocarro_chosen\"]//b";
    public static String hondaVeiculosFipeInputModeloVeiculoCarro = "//div[@id=\"selectAnoModelocarro_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaModeloCarro = "//div[@id=\"selectAnoModelocarro_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaModeloCarro = "//div[@id=\"selectAnoModelocarro_chosen\"]//li/em[text()=\"arg0\"]";

    public static String hondaVeiculosFipeButtonAbrirCampoAnoModeloCarro = "//div[@id=\"selectAnocarro_chosen\"]//b";
    public static String hondaVeiculosFipeInputAnoModeloVeiculoCarro = "//div[@id=\"selectAnocarro_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaAnoModeloCarro = "//div[@id=\"selectAnocarro_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaAnoModeloCarro = "//div[@id=\"selectAnocarro_chosen\"]//li/em[contains(., \"arg0\")]";

    public static String hondaVeiculosFipeButtonPesquisarCarro = "//a[@id=\"buttonPesquisarcarro\"]";
    public static String hondaVeiculosFipeTextModeloCarroResultadoPesquisa = "//div[@id=\"resultadoConsultacarroFiltros\"]//p[text()=\"Modelo:\"]/ancestor::td/following-sibling::td/p";
    public static String hondaVeiculosFipeButtonLimparPesquisaCarro = "//article[@idbuttonclear=\"buttonLimparPesquisarcarro\"]//a[contains(., \"Limpar Pesquisa\")]";

    public static String hondaVeiculosFipeTextResultadoPrecoCarro = "//div[@id=\"resultadoConsultacarroFiltros\"]//p[text()=\"Preço Médio\"]/ancestor::td//following-sibling::td/p";
    public static String hondaVeiculosFipeTextResultadoModeloVeiculo = "//div[@id=\"resultadoConsultacarroFiltros\"]//p[text()=\"Modelo:\"]/ancestor::td//following-sibling::td/p";



    public static String hondaVeiculosFipeButtonConsultaMotoFechado = "//div[text()=\"Consulta de Motos\"]/ancestor::li[@class=\"ilustra\"]";
    public static String hondaVeiculosFipeButtonAbrirConsultaMoto = "//div[text()=\"Consulta de Motos\"]";
    public static String hondaVeiculosFipeButtonAbrirCampoMarcaMoto = "//div[@id=\"selectMarcamoto_chosen\"]//b";
    public static String hondaVeiculosFipeInputMarcaMoto = "//div[@id=\"selectMarcamoto_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaMarcaMoto = "//div[@id=\"selectMarcamoto_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaMarcaMoto = "//div[@id=\"selectMarcamoto_chosen\"]//li/em";

    public static String hondaVeiculosFipeButtonAbrirCampoModeloMoto = "//div[@id=\"selectAnoModelomoto_chosen\"]//b";
    public static String hondaVeiculosFipeInputModeloMoto = "//div[@id=\"selectAnoModelomoto_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaModeloMoto = "//div[@id=\"selectAnoModelomoto_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaModeloMoto = "//div[@id=\"selectAnoModelomoto_chosen\"]//li/em[text()=\"arg0\"]";

    public static String hondaVeiculosFipeButtonAbrirCampoAnoModeloMoto = "//div[@id=\"selectAnomoto_chosen\"]//b";
    public static String hondaVeiculosFipeInputAnoModeloVeiculoMoto = "//div[@id=\"selectAnomoto_chosen\"]//input";
    //public static String hondaVeiculosFipeTextRetornoPesquisaAnoModeloMoto = "//div[@id=\"selectAnomoto_chosen\"]//li";
    public static String hondaVeiculosFipeTextRetornoPesquisaAnoModeloMoto = "//div[@id=\"selectAnomoto_chosen\"]//li/em[text()=\"arg0\"]";

    public static String hondaVeiculosFipeButtonPesquisarMoto = "//a[@id=\"buttonPesquisarmoto\"]";
    public static String hondaVeiculosFipeTextResultadoPrecoMoto = "//div[@id=\"resultadoConsultamotoFiltros\"]//p[text()=\"Preço Médio\"]/ancestor::td/following-sibling::td/p";
    public static String hondaVeiculosFipeTextModeloMotoResultadoPesquisa = "//div[@id=\"resultadoConsultamotoFiltros\"]//p[text()=\"Modelo:\"]/ancestor::td/following-sibling::td/p";
    public static String hondaVeiculosFipeButtonLimparPesquisaMoto = "//div[@id=\"buttonLimparPesquisarmoto\"]/a";


    public static LinkedHashMap<String, String> hondaVeiculosFipe = createData();
    private static LinkedHashMap<String, String> createData() {
        LinkedHashMap<String, String> ret = new LinkedHashMap<>();
        ret.put(hondaVeiculosFipeTextPrecoMedioVeiculo, "Valida tela preço médio de veículos exibida");
        ret.put(hondaVeiculosFipeButtonMenuLateralPrecoMedioVeiuclos, "Menu lateral preço médio de veículos");
        ret.put(hondaVeiculosFipeButtonConsultaCarros, "abrir form consulta de carros e utilitários pequenos");
        ret.put(hondaVeiculosFipeButtonAbrirConsultaCarros, "abrir form consulta de carros e utilitários pequenos");
        ret.put(hondaVeiculosFipeButtonAbrirCampoMarcaCarro, "abrir combobox marca do veículo");
        ret.put(hondaVeiculosFipeInputMarcaVeiculoCarro, "input marca veículo");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaMarcaCarro, "retorno pesquisa marca do veículo");
        ret.put(hondaVeiculosFipeButtonAbrirCampoModeloCarro, "abrir combobox modelo do veículo");
        ret.put(hondaVeiculosFipeInputModeloVeiculoCarro, "input modelo veículo");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaModeloCarro, "retorno pesquisa modelo do veículo");
        ret.put(hondaVeiculosFipeButtonAbrirCampoAnoModeloCarro, "abrir combobox ano modelo do veículo");
        ret.put(hondaVeiculosFipeInputAnoModeloVeiculoCarro, "input ano modelo veículo");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaAnoModeloCarro, "retorno pesquisa ano modelo do veículo");
        ret.put(hondaVeiculosFipeButtonPesquisarCarro, "pesquisar carro");
        ret.put(hondaVeiculosFipeButtonLimparPesquisaCarro, "limpar pesquisa carro");
        ret.put(hondaVeiculosFipeTextResultadoPrecoCarro, "resultado preço médio carro");
        ret.put(hondaVeiculosFipeTextResultadoModeloVeiculo, "resultado modelo veículo");
        ret.put(hondaVeiculosFipeButtonConsultaMotoFechado, "valida form consulta moto fechado");
        ret.put(hondaVeiculosFipeButtonAbrirCampoMarcaMoto, "abrir combobox marca moto");
        ret.put(hondaVeiculosFipeInputMarcaMoto, "input marca moto");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaMarcaMoto, "retorno pesquisa marca moto");
        ret.put(hondaVeiculosFipeButtonAbrirCampoModeloMoto, "abrir campo modelo moto");
        ret.put(hondaVeiculosFipeInputModeloMoto, "input modelo moto");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaModeloMoto, "retorno pesquisa modelo moto");
        ret.put(hondaVeiculosFipeButtonAbrirCampoAnoModeloMoto, "abrir campo ano modelo moto");
        ret.put(hondaVeiculosFipeInputAnoModeloVeiculoMoto, "input ano modelo moto");
        ret.put(hondaVeiculosFipeTextRetornoPesquisaAnoModeloMoto, "retorno pesquisa ano modelo moto");
        ret.put(hondaVeiculosFipeButtonAbrirConsultaMoto, "abrir consulta moto");
        ret.put(hondaVeiculosFipeButtonPesquisarMoto, "pesquisar moto");
        ret.put(hondaVeiculosFipeTextResultadoPrecoMoto, "resultado preço moto");
        ret.put(hondaVeiculosFipeButtonLimparPesquisaMoto, "limpar pesquisa moto");
        ret.put(hondaVeiculosFipeTextModeloCarroResultadoPesquisa, "modelo carro retorno pesquisa");
        ret.put(hondaVeiculosFipeTextModeloMotoResultadoPesquisa, "modelo moto retorno pesquisa");


        return ret;
    }
}
