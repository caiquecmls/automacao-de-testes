package tests;
//IMPORTAÇÃO STATIC DE TODOS OS MÉTODOS

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    //todo método de teste precisa ser publico
    //todo médoto de teste não pode ter estrução de retorno
    //todo método de teste começa com o sufixo test
    @Test
    public void testAdicionaUmaInformacaoAdicionalDoUsuario() {
        //APONTAR O CHROME DRIVER
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Drivers\\chromedriver.exe");
        //faz com que a variavel navegador tenha uma instância do chrome
        //Abrindo navegador
        WebDriver navegador = new ChromeDriver();

        //Navegando para a página da Taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        //Definir um timeouts, para evitar problemas de buscar elementos que ainda não foram carregados
        //vou esperar até que os elementos esteja pronto para interagir
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.manage().window().maximize();
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
        //Validar que dentro do elemento com class "me" está o texto "Hi, Caique"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();

        //validação, verifico se o nome da página está de acordo com o esperado
        assertEquals("Hi, Caique", textoNoElementoMe);

        //Fechar o navegador
        navegador.quit();
    }
}
