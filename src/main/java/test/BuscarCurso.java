package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import entity.Materia;
import entity.Professor;
import helper.HelperBuscarCurso;
import suporte.DriverFactory;
import type.Estados;
import type.Mensagens;
import type.URL;

public class BuscarCurso extends DriverFactory{
	
	private HelperBuscarCurso helper;
	private Professor professor;
	private Materia materia;
	
	@BeforeClass
	public void init() {
		helper = new HelperBuscarCurso();
		professor = helper.gerarProfessor();
		materia = helper.gerarMateria();
		helper.acessaAplicacao(URL.APLICACAO);
	}
	
	@Test
	public void CEN01_validarCursoPorProfessor() {
		helper
			.acessarMenuPorProfessor()
			.selecionarProfessor(professor.nome)
			.validarLabelQuantidadeCurso()
			.selecionarCurso(professor.curso.nome)
			.validarCurso(professor.curso.nome);
	}
	
	@Test
	public void CEN02_validarCursoPorMateria() {
		helper
			.acessarMenuPorMateria()
			.selecionarMateria(materia.nome)
			.validarLabelQuantidadeCurso()
			.selecionarCurso(materia.curso.nome)
			.validarCurso(materia.curso.nome);
	}
	
	@Test
	public void CEN03_validarTelaCursoPorRegiao() {
		helper
			.acessarMenuPorRegiao()
			.validarTelaCursoPorRegiao(Estados.DF);
	}
	
	@Test
	public void CEN04_cursoNaoEncontrado() {
		helper
			.acessarMenuPorProfessor()
			.selecionarProfessor(professor.nome)
			.preencherCurso("Curso Inexistente")
			.validarCursoNaoEncontrado(Mensagens.RESULTADO_NAO_ENCONTRADO);
	}
}