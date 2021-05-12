package tests;
//IMPORTAÇÃO STATIC DE TODOS OS MÉTODOS
import static org.junit.Assert.*;
import org.junit.After;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @BeforeEach
    public void setUp(){
        System.out.println("AbstractBaseTest.setUp");
        //APONTAR O CHROME DRIVER
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Drivers\\chromedriver.exe");
        //faz com que a variavel navegador tenha uma instância do chrome
        //Abrindo navegador
        navegador = new ChromeDriver();

        //Definir um timeouts, para evitar problemas de buscar elementos que ainda não foram carregados
        //vou esperar até que os elementos esteja pronto para interagir
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Navegando para a página da Taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        //Clicar no Link que possui o texto "Sign in"
        navegador.findElement(new By.ByLinkText("Sign in")).click();

        //Identificando o formulário de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo de texto com o name "login" que está dentro do formulário de id "signinbox" colocar o texto "caiquecmls"
        formularioSignInBox.findElement(By.name("login")).sendKeys("caiquecmls");

        //Digitar no campo de texto com o name "password" que está dentro do formulário de id "signinbox" colocar o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no Link que possui o texto "SIGN IN"
        navegador.findElement(new By.ByLinkText("SIGN IN")).click();

        //Clicar no Link que possui o texto "Hi, Caique"
        navegador.findElement(new By.ByLinkText("Hi, Caique")).click();

        //Clicar no Link que possui o texto "More data about you"
        navegador.findElement(new By.ByLinkText("MORE DATA ABOUT YOU")).click();
    }
    @AfterEach
    public void tearDown(){
        System.out.println("AbstractBaseTest.tearDown");
        //Fechar o navegador
//        navegador.quit();
    }

//    @Test
    public void testAdicionaUmaInformacaoAdicionalDoUsuario() {
        //Clica no botão através do seu xpath
        navegador.findElement(new By.ByXPath("//*[@id=\"moredata\"]/div[2]/button")).click();

        //Identificar o popUp onde está o formulário de id addmoredata
        WebElement popup = navegador.findElement(new By.ById("addmoredata"));

        //Na combo de name "type" escolhe a opção "Phone"
        WebElement campoType = popup.findElement(new By.ByName("type"));
        new Select(campoType).selectByVisibleText("Phone");

        //No campo de name "contact" digitar "+5511999999999"
        popup.findElement(new By.ByName("contact")).sendKeys("+5511999999999");

        //Clica no link de text "SAVE" que está no popUp
        popup.findElement(new By.ByLinkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement contact = navegador.findElement(new By.ById("toast-container"));
        String textoNoElemento = contact.getText();
        assertEquals("Your contact has been added!",textoNoElemento);

//        //Validar que dentro do elemento com class "me" está o texto "Hi, Caique"
//        WebElement me = navegador.findElement(By.className("me"));
//        String textoNoElementoMe = me.getText();
//        assertEquals("Hi, Caique", textoNoElementoMe);
    }

    @Test
    public void removerUmContantoDeUmUsuario(){
        //Clica no elemento pelo seu xpath //<span[text()="+551133334444"]/following-sibling::a</span>
        navegador.findElement(new By.ByXPath("//span[text()='+551133334444']/following-sibling::a")).click();

        //confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(new By.ById("toast-container"));
        String textoNoElemento = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!",textoNoElemento);

        //Aguardar até 10 segundos para que a janela desapareça
        long segundos = 10;
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        //Clica no link com o testo "Logout"
        navegador.findElement(new By.ByLinkText("Logout")).click();

    }
}
