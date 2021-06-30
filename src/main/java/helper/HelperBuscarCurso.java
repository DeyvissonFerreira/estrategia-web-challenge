package helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import entity.Curso;
import entity.Materia;
import entity.Professor;
import page.PageBuscarCurso;
import suporte.Support;
import type.Estados;
import type.Mensagens;

public class HelperBuscarCurso extends Support{
	
	private PageBuscarCurso page;
	private double valorCurso;
	private double valorParcela;
	private int quantidadeParcelas;
	
	public HelperBuscarCurso() {
		page = new PageBuscarCurso();
	}
	
	public Professor gerarProfessor() {
		return new Professor("Ena Loiola", new Curso("ANEEL (Todos os cargos de Analista Administrativo) Inglês - 2021 (Pré-Edital)"));
	}
	
	public Materia gerarMateria() {
		return new Materia("Inglês", new Curso("Inglês p/ SEED-AP (Professor - Inglês) - 2021 - Pré-Edital"));
	}
	
	public HelperBuscarCurso acessarMenuPorProfessor() {
		click(page.MENU_POR_PROFESSOR);
		
		return this;
	}
	
	public HelperBuscarCurso acessarMenuPorMateria() {
		click(page.MENU_POR_MATERIA);
		
		return this;
	}
	
	public HelperBuscarCurso acessarMenuPorRegiao() {
		click(page.MENU_POR_REGIAO);
		
		return this;
	}
	
	public HelperBuscarCurso selecionarProfessor(String professor) {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(page.LISTA_PROFESSORES));
		click(page.LISTA_PROFESSORES.stream().filter(p -> p.getText().equalsIgnoreCase(professor)).findFirst().get());
		
		return this;
	}
	
	public HelperBuscarCurso selecionarMateria(String materia) {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(page.LISTA_MATERIAS));
		click(page.LISTA_MATERIAS.stream().filter(p -> p.getText().equalsIgnoreCase(materia)).findFirst().get());
		
		return this;
	}
	
	public HelperBuscarCurso preencherCurso(String curso) {
		preencheCampo(page.CAMPO_FILTRO_CURSO, curso);
		
		return this;
	}
	
	public HelperBuscarCurso selecionarCurso(String curso) {
		// Encontra o registro de acordo com o nome do curso.
		WebElement cursoEl = page.LISTA_CURSO.stream().filter(p -> p.getAttribute("innerText").contains(curso)).findFirst().get();
		
		// Extra as informações de Quantidade de Parcelas e Valor da Parcela.
		String textoParcelas = returnChildElement(cursoEl, page.VALOR_CURSO_LISTA).getAttribute("innerText");
		valorParcela = Double.parseDouble(textoParcelas.substring(textoParcelas.indexOf("$") + 1).trim().replace(",", "."));
		quantidadeParcelas = Integer.parseInt(textoParcelas.substring(textoParcelas.indexOf("x") - 2, textoParcelas.indexOf("x")));
		
		// Cálculo do valor total do curso.
		valorCurso = valorParcela * quantidadeParcelas;
		
		click(returnChildElement(cursoEl, page.NOME_CURSO));
		
		return this;
	}
	
	public HelperBuscarCurso validarLabelQuantidadeCurso() {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(page.LISTA_CURSO));
		validacao(page.LABEL_QNTD_CURSOS, "innerText", String.valueOf(page.LISTA_CURSO.size()).concat(" cursos disponíveis"));
		
		return this;
	}
	
	public HelperBuscarCurso validarCurso(String curso) {
		validacao(page.NOME_CURSO_DETALHE, "innerText", curso);
		validacao(page.VALOR_CURSO_DETALHE, "innerText", "R$ ".concat(df.format(Math.rint(valorCurso))));
		validacao(page.VALOR_CURSO_PARCELA_DETALHE, "innerText", "ou 12x de R$ ".concat(df.format(valorParcela)));
		
		return this;
	}
	
	public HelperBuscarCurso validarTelaCursoPorRegiao(Estados UF) {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(page.LISTA_ESTADOS));
		click(page.LISTA_ESTADOS.stream().filter(e -> e.getAttribute("innerText").equalsIgnoreCase(UF.getEstado())).findFirst().get());
		aguardaElemento(ExpectedConditions.attributeContains(page.TITULO_CURSO_POR_REGIAO, "innerText", UF.getEstado()));
		validacao(page.TITULO_CURSO_POR_REGIAO, "innerText", "Cursos em ".concat(UF.getEstado()));
		
		return this;
	}
	
	public HelperBuscarCurso validarCursoNaoEncontrado(Mensagens mensagem) {
		aguardaElemento(ExpectedConditions.visibilityOf(page.MSG_RESULTADO_NAO_ENCONTRADO));
		validacao(page.MSG_RESULTADO_NAO_ENCONTRADO, mensagem.getMensagem());
		
		return this;
	}
}