package suporte;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import type.URL;

public class Support {
	
	public DecimalFormat df;
	
	public Support() {
		df = new DecimalFormat("####.00");
	}
	
	public void anexaDescricao(String desc) {
		System.out.println(desc);
	}
	
	public void acessaAplicacao(URL url) {
		anexaDescricao("Acessando URL: ".concat(url.getURL()));
		DriverFactory.acessaURL(url.getURL());
		executeComandJavaScript("localStorage.setItem('gscw', \"{\\\"44266\\\":{\\\"show\\\":{\\\"time\\\":1624802008871,\\\"value\\\":1},\\\"$last\\\":1624802018938,\\\"submit\\\":{\\\"time\\\":1624802016820,\\\"value\\\":1},\\\"close\\\":{\\\"time\\\":1624802018938,\\\"value\\\":1}}}\")");
		executeComandJavaScript("localStorage.setItem('gscw', {\"59715\":{\"close\":{\"time\":1624888971197,\"value\":1},\"$last\":1624888971197}})");
		
		aguardaElemento(ExpectedConditions.elementToBeClickable(By.cssSelector("#onesignal-slidedown-cancel-button"))).click();
	}
	
    private void executeComandJavaScript(String comand) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript(comand);    	
    }

	protected WebElement aguardaElemento(ExpectedCondition<WebElement> expect) {
		return DriverFactory.getWait().until(ExpectedConditions.refreshed(expect));
	}
	
	protected void aguardaElemento(Function<WebDriver, ?> expect) {
		DriverFactory.getWait().until(ExpectedConditions.refreshed((ExpectedCondition<?>) expect));
	}
	
	protected void click(WebElement elemento) {
		anexaDescricao("Realizando clique no elemento: ".concat(elemento.toString()));
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).click();
	}

	protected void preencheCampo(WebElement elemento, String valor) {
		anexaDescricao("Preenchendo o campo: ".concat(elemento.toString()).concat(" Com o valor: ".concat(valor)));
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).sendKeys(valor);
	}
	
	protected void validacao(WebElement elemento, String check) {
		String desc = "Valor Atual: ".concat(elemento.getText()) + " | Valor Esperado: " + check;
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		if(elemento.getText().equalsIgnoreCase(check)) {
			anexaDescricao("SUCESSO | ".concat(desc));
		}else {
			anexaDescricao("FALHA | ".concat(desc));
		}
		assertTrue(elemento.getText().equalsIgnoreCase(check));
	}
	
	protected void validacao(WebElement elemento, String atributo, String check) {
		String desc = "Valor Atual: ".concat(elemento.getAttribute(atributo)) + " | Valor Esperado: " + check;
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		if(elemento.getAttribute(atributo).equalsIgnoreCase(check)) {
			anexaDescricao("SUCESSO | ".concat(desc));
		}else {
			anexaDescricao("FALHA | ".concat(desc));
		}
		assertEquals(elemento.getAttribute(atributo), check);
	}
	
	protected String returnCssSeletor(WebElement elemento) {
		String regex = "(.*?-> |.*\\{By.Android|.*\\{By.|.*'By.)(.*?)( ?(S|s)elector)?: (.*a?)(.*\\}\\)|\\'|])";
		return elemento.toString().replaceAll(regex, "$5");
	}

	protected WebElement returnChildElement(WebElement elementoPai, WebElement elementoFilho) {
		return elementoPai.findElement(By.cssSelector(returnCssSeletor(elementoFilho)));
	}
}
