package page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import suporte.DriverFactory;

public class PageBuscarCurso {
	
	public PageBuscarCurso() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(css = "div.nav-header-inner a[href*='/cursos/professor']")
	public WebElement MENU_POR_PROFESSOR;
	
	@FindBy(css = "div.nav-header-inner a[href*='/cursos/materia']")
	public WebElement MENU_POR_MATERIA;
	
	@FindBy(css = "div.nav-header-inner a[href*='/cursos/regiao']")
	public WebElement MENU_POR_REGIAO;

	@FindBy(css = "div[data-url*='/professor/'] div.page-result-list h1.card-prod-title a")
	public List<WebElement> LISTA_PROFESSORES;
	
	@FindBy(css = "div[data-type='todos'] section")
	public List<WebElement> LISTA_CURSO;

	@FindBy(css = "div.page-result-list h1.card-prod-title a")
	public List<WebElement> LISTA_MATERIAS;

	@FindBy(css = "div[data-type='todos'] h1.card-prod-title a")
	public WebElement NOME_CURSO;

	@FindBy(css = "section.section-content div.item span.text")
	public WebElement LABEL_QNTD_CURSOS;
	
	@FindBy(css = "div.cur-details-shopping-price div.value")
	public WebElement VALOR_CURSO_DETALHE;

	@FindBy(css = "h1.cur-details-info-title")
	public WebElement NOME_CURSO_DETALHE;

	@FindBy(css = "div.cur-details-shopping-installments")
	public WebElement VALOR_CURSO_PARCELA_DETALHE;

	@FindBy(css = "div.card-prod-price")
	public WebElement VALOR_CURSO_LISTA;

	@FindBy(css = "div.js-card-prod-container p")
	public WebElement MSG_RESULTADO_NAO_ENCONTRADO;

	@FindBy(css = "div.page-courses-filters input")
	public WebElement CAMPO_FILTRO_CURSO;
	
	@FindBy(css = "section.regions-list-item li")
	public List<WebElement> LISTA_ESTADOS;

	@FindBy(css = "div.page-courses .section-header-title")
	public WebElement TITULO_CURSO_POR_REGIAO;
}