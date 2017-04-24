package nequi_appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author   Juan Camilo Arboleda - Programador
 * @author   Christian Fontalvo - Analista de Automatización
 *
 */
public class Nequi 
{
	/**
	* @throws java.lang.Exception
	*/
	static Funciones funciones = new Funciones();
	static Flujos flujos = new Flujos();
	public static AndroidDriver<WebElement> Obj_Android_Driver;
	public static String xPath =null;
	public static int index;
	public static WebDriverWait Obj_Webdriver_Espera;
	public static int int_Tiempo_Espera = 10;
	public static String Str_Nombre_Bolsillo = "EditarBolsillo";
	public static String Str_Nombre2_Bolsillo = "EditarBolsillo2";
	public static String Str_Nombre_Meta = "MetaAutomatico";
	public static String Str_Numero_Telefono = "3991000025";
	public static String Str_Numero_Amigo = "3006581359";
	public static String Str_Contrasena = "1397";
	public static String Str_OTP = "1234";
	public static String Str_Ruta_Evidencias = "C:\\evidencias_nequi\\";
	public static Dimension Obj_Tamano =null;
	static File file1;
	static File file2;
	public static int startx = 0;
	public static int endx = 0;
	public static int starty = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{

	}
	
	@Test
	public void Inicio_NEQUI() throws InterruptedException, IOException
	{
		System.out.println("******************Inicio de Prueba********************************************");
		Thread.sleep(10000);
		funciones.buscaElemento("//*[@resource-id='android:id/button2' and @text='Update']",Obj_Android_Driver);
		// ---------------------------- Paseo ---------------
		Thread.sleep(5000);
		//flujos.paseo(Obj_Android_Driver);
		// ****************** Entrar a la APP *********************
		flujos.entrarAppCuenta(Obj_Android_Driver,Str_Contrasena,Str_OTP,Str_Numero_Telefono);
		// ****************** Pedir Vinculado *********************
		//flujos.pedirVinculado(Obj_Android_Driver,Str_Numero_Amigo);
		// ****************** Pagar_vinc_Disponible ***************
		//flujos.pagarVinculado("prueba2",Obj_Android_Driver);  // Imprevistos / Disponible / prueba2
		// ****************** enviar_vinc_Disponible **************
		//flujos.enviarVinculado("Meta Vacaciones Tolu",Str_Contrasena,Str_Numero_Amigo,Obj_Android_Driver);		
		// ****************** Sacar_Disponible ********************
		//flujos.sacarVinculado("prueba2",Obj_Android_Driver);	// Imprevistos / Disponible / prueba2
		// ****************** Crud Bolsillo *********************
		//flujos.creaBolsillo(Obj_Android_Driver,Str_Nombre_Bolsillo);	
		// ****************** Crud Bolsillo  Parametro (editar,eliminar)*********************
		flujos.crudBolsillo("editar",Obj_Android_Driver,Str_Nombre_Bolsillo,Str_Nombre2_Bolsillo);
		// ****************** Crud Metas *********************
		//flujos.crearMeta(Obj_Android_Driver);
		// ****************** Crud Meta  Parametro (editar,romper,completar)*********************
		//flujos.crudMeta("completar",Obj_Android_Driver);
		
		
		// ****************** Guardadito *********************
		//guardadito("Saca");
		
		
		//		Formas de pago:
		//		BolsilloImprevistos
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		System.out.println("Configuración de la terminal: Iniciado");
		try 
		{
			DesiredCapabilities Obj_Capabilities = new DesiredCapabilities();
			Obj_Android_Driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), Obj_Capabilities);
			Obj_Webdriver_Espera = new WebDriverWait(Obj_Android_Driver, int_Tiempo_Espera);
		} 
		catch (Exception Obj_Error) 
		{
			System.out.println("Error: " + Obj_Error.getMessage());
		}
		System.out.println("Configuracion de la terminal: Finalizada");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		funciones.toma_Evidencias(Obj_Android_Driver);
		Thread.sleep(int_Tiempo_Espera*500);
		System.out.println("******************************************************************************************************************"
				+ '\n' + "Prueba Finalizada");
		//Obj_Android_Driver.closeApp();
		Obj_Android_Driver.close();
		Obj_Android_Driver = null;
		Obj_Webdriver_Espera = null;
		//setUp();
	}
		

	
	
	
	//***************************************    CRUD META    **************************
	
	public static void crudMeta(String accion) throws IOException{
		try {
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Thread.sleep(8000);
			funciones.buscaElemento("//*[@content-desc='Metas' and @clickable='true']",Obj_Android_Driver);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			System.out.println(" **** Clic en Metas ************** OK");
			Thread.sleep(3000);
			
			// ******* click en + para crear nueva Meta *****
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			
			// ******* click en editar el Bolsillo creado previamente *****					
			//opSelMeta(Str_Nombre_Meta);
			funciones.buscaElemento("//*[@content-desc='MetaAutomatico']",Obj_Android_Driver);
			switch (accion) 
			{ 			
					case "editar": editaMeta(); 
					break;
					case "completar": completaMeta(); 
					break; 
					case "romper": rompeMeta(); 
					break;
			}
		} catch (Exception e) {
			System.out.println("**** CRUD Meta FAIL *******" + e);
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}
	
	public static void crearMeta() throws IOException

	{
		System.out.println("***** Crear Meta **********");
		try 
		{
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Thread.sleep(5000);
			funciones.buscaElemento("//*[@content-desc='Metas' and @clickable='true']",Obj_Android_Driver);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			System.out.println(" **** Clic en Metas ************** OK");
			Thread.sleep(3000);
			
			// ******* click en + para crear nueva Meta *****
			file1 = funciones.esperarCambio(Obj_Android_Driver);
//			buscaElemento("//*[@content-desc='?' and @clickable='true']");
			
			List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View/android.view.View/android.view.View/android.widget.Button"));
			int Dbl_X = list.get(1).getLocation().getX();
			System.out.println(Dbl_X);
			int Dbl_Y = list.get(1).getLocation().getY();
			System.out.println(Dbl_Y);
			Obj_Android_Driver.tap(1, Dbl_X+50, Dbl_Y+50, 100);
			System.out.println(" **** Tap en + (Nuevo)  ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			Thread.sleep(5000);

			// ******* Nombre de la Meta *****
			
//			WebElement camp_cel = Obj_Android_Driver.findElementByXPath("//*/android.widget.EditText");
			int anch_pant = Obj_Android_Driver.manage().window().getSize().getWidth();
//			int x = camp_cel.getLocation().getX();
//			int y = camp_cel.getLocation().getY();
//			Obj_Android_Driver.tap(1, x-anch_pant,y, 0);
//			System.out.println(" **** Campo Nombre de la Meta ************** OK");
//			Thread.sleep(500);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys(Str_Nombre_Meta);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			System.out.println(" **** Ingresa el Nombre de la Meta ************** OK");
			Thread.sleep(2000);
			
			// ******* Monto de la Meta *****
			Obj_Android_Driver.tap(1,anch_pant/2,90, 0);
			Thread.sleep(2000);
		
			Obj_Android_Driver.findElementByXPath("//*[contains(@text,'$ 0') and @clickable='true']").sendKeys("200000");
			Thread.sleep(2000);
			System.out.println(" **** Ingresa el Monto de la Meta ************** OK");
			Obj_Android_Driver.tap(1,anch_pant/2,90, 0);
			Thread.sleep(2000);

			// ******* diligencia campo Fecha *****
			Thread.sleep(2000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Fecha límite') and @clickable='true']").click();
			funciones.toma_Evidencias(Obj_Android_Driver);
			Thread.sleep(2000);

			System.out.println(" **** Encuentra Campo Fechas ************** OK");

			//***************************************** Ingreso de mes *****************************	
			WebElement mes = Obj_Android_Driver.findElement(By.xpath("//*[@resource-id='android:id/month']"));
			mes.sendKeys("Sep");
			System.out.println(" **** ingresa el mes ************** OK");
			//***************************************** Ingreso de día *****************************	
			WebElement dia = Obj_Android_Driver.findElement(By.xpath("//*[@resource-id='android:id/day']"));
			dia.sendKeys("25");
			System.out.println(" **** Ingresa el día ************** OK");
			//***************************************** Ingreso de año *****************************	
			WebElement anio = Obj_Android_Driver.findElement(By.xpath("//*[@resource-id='android:id/year']"));
			anio.sendKeys("2018");
			System.out.println(" **** Ingresa el año ************** OK");
						
			System.out.println(" **** Ingresa la Fecha de la Meta ************** OK");
			
			Thread.sleep(2000);
			Obj_Android_Driver.findElement(By.xpath("//*[@resource-id='android:id/button1' and @text='Set']")).click();
	
			// ******* click en el Chulito *****
			
			List<WebElement> list2= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View/android.view.View/android.view.View/android.widget.Button"));
			int Dbl_X_4 = list2.get(1).getLocation().getX();
			System.out.println(Dbl_X_4);
			int Dbl_Y_4 = list2.get(1).getLocation().getY();
			System.out.println(Dbl_Y_4);
			Obj_Android_Driver.tap(1, Dbl_X_4, Dbl_Y_4, 100);

			System.out.println(" **** Creación de Meta - FIN ************** OK");

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Creación de Meta ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}
	
	public static void editaMeta() throws InterruptedException{
		Thread.sleep(4000);
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*[contains(@content-desc,'+')]"));
		//System.out.println("tammm: "+list.size());
		list.get(1).click();
		Thread.sleep(2000);
		List<WebElement> list1= Obj_Android_Driver.findElements(By.xpath("//*[contains(@content-desc,'+')]"));
		list1.get(1).click();
		for(int i = 0;i<4;i++){
			Thread.sleep(1000);
			list.get(1).click();
		}
		List<WebElement> list3= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View/android.view.View/android.view.View/android.widget.Button"));
		int Dbl_X = list3.get(1).getLocation().getX();
		System.out.println(Dbl_X);
		int Dbl_Y = list3.get(1).getLocation().getY();
		System.out.println(Dbl_Y);
		Obj_Android_Driver.tap(1, Dbl_X+50, Dbl_Y+50, 100);
		
		
	}
	
	public static void completaMeta() throws InterruptedException{
		Thread.sleep(4000);
		String saldoMeta=funciones.opSelMeta(Str_Nombre_Meta,Obj_Android_Driver);
		String recarga =funciones.opRecarga(Str_Nombre_Meta,Obj_Android_Driver);
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*[contains(@content-desc,'+')]"));
		while(!saldoMeta.equals(recarga)){
			
			list.get(0).click();
			recarga =funciones.opRecarga(Str_Nombre_Meta,Obj_Android_Driver);
			System.out.println("saldo "+saldoMeta);
			System.out.println("recarga "+recarga);
		}
		System.out.println("saldo "+saldoMeta);
		System.out.println("recarga "+recarga);
		
		List<WebElement> list3= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View/android.view.View/android.view.View/android.widget.Button"));
		int Dbl_X = list3.get(1).getLocation().getX();
		System.out.println(Dbl_X);
		int Dbl_Y = list3.get(1).getLocation().getY();
		System.out.println(Dbl_Y);
		Obj_Android_Driver.tap(1, Dbl_X+50, Dbl_Y+50, 100);
		
	}
	
	public static void rompeMeta(){
		
	}

	//*************************************** FLUJOS DEL GUARDADITO *********************
	
	public static void guardadito(String accion) throws IOException

	{
		System.out.println("********* Flujo del Guardadito ***********");
		try 
		{	
			// ****************** Abre Guardadito *********************
						
			Obj_Webdriver_Espera.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Guardadito' and @clickable='true']")));
			WebElement Obj_Elemento;
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[@content-desc='Guardadito' and @clickable='true']"));	
			int Dbl_X_1 = Obj_Elemento.getLocation().getX() - Obj_Android_Driver.manage().window().getSize().getWidth();
			int Dbl_Y_1 = Obj_Elemento.getLocation().getY();
			Obj_Android_Driver.tap(2, Dbl_X_1+80, Dbl_Y_1-50, 100);
			System.out.println(" **** Clic en Guardadito ************** OK");
			Thread.sleep(8000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Entendido') and @clickable='true']").click();
			System.out.println(" **** Click en Entendido ************** OK");
			Thread.sleep(3000);
			funciones.abrirVelo(Obj_Android_Driver);
			System.out.println(" **** Abre velo de Guardadito ************** OK");
			Thread.sleep(3000);
			
			//  ****************************************
			
			//Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[@content-desc='"+accion+"' and @clickable='true']")).click();	
			/*int Dbl_X_2 = Obj_Elemento.getLocation().getX() - Obj_Android_Driver.manage().window().getSize().getWidth();
			int Dbl_Y_2 = Obj_Elemento.getLocation().getY();
			Obj_Android_Driver.tap(2, Dbl_X_2+80, Dbl_Y_2-50, 100);*/

			System.out.println(" **** Clic en La accion a realizar ************** OK");
			Thread.sleep(2000);
			//  ****************************************
			
			switch (accion) 
			{ 
					case "Envía": enviaGuardadito(); 
					break; 
					case "Paga": pagaGuardadito(); 
					break; 
					case "Saca": sacaGuardadito(); 
					break;
			}
			System.out.println(" **** Flujo Seleccionado Fin ************** OK");
		}
		catch (Exception Obj_Error)
		{
			System.out.println(" **** Flujo del Guardadito ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}
	}
	
	public static void enviaGuardadito() throws IOException
	{
		System.out.println("********* Flujo del Guardadito ***********");
		try 
		{	
			// ****************** Abre Guardadito *********************
						
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Envía') and @clickable='true']").click();
			System.out.println(" **** Clic en Envía ************** OK");
			Thread.sleep(6000);

			WebElement camp_cel = Obj_Android_Driver.findElementByXPath("//*/android.widget.EditText");
			int anch_pant = Obj_Android_Driver.manage().window().getSize().getWidth();
			int x = camp_cel.getLocation().getX();
			int y = camp_cel.getLocation().getY();
			Obj_Android_Driver.tap(1, x-anch_pant,y, 1000);
			
			System.out.println(" **** Campo Celular ************** OK");
			Thread.sleep(3000);
			Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys(Str_Numero_Amigo);
			Thread.sleep(3000);
			WebElement monto = Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'$ 0') and @clickable='true']");					
			int x1 = monto.getLocation().getX();
			int y1 = monto.getLocation().getY();
			Thread.sleep(3000);
			Obj_Android_Driver.tap(1, x1-anch_pant,y1, 0);
			System.out.println(" **** CAMPO MONTO ************** OK");
			Thread.sleep(3000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'$ 0') and @clickable='true']").sendKeys("1000");
			Thread.sleep(3000);
			Obj_Android_Driver.tap(1,anch_pant/2,90, 0);
			Thread.sleep(3000);
			List<WebElement> mensaje= Obj_Android_Driver.findElements(By.xpath("//*/android.webkit.WebView/android.view.View"));
			mensaje.get(9).sendKeys("Mensaje Automatizado");
			System.out.println("**** CAMPO MENSAJE ************** OK");
			Obj_Android_Driver.tap(1,anch_pant/2,90, 0);
			Thread.sleep(3000);			
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Envía') and @class='android.widget.Button']").click();
			Thread.sleep(3000);
			funciones.contrasena(Str_Contrasena,Obj_Android_Driver);

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" **** Enviar vinculado ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}
	}
	
	public static void pagaGuardadito() throws IOException
	{
		System.out.println("****** Pagar *************");
		try 
		{
			// ****************** Abre Velo de Transacciones *********************
		
			Thread.sleep(8000);
			Obj_Webdriver_Espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@content-desc,'Paga') and @clickable='true']")));
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Paga') and @clickable='true']").click();
			System.out.println(" **** Clic en Pagar ************** OK");
			Thread.sleep(5000);
		}
		catch (Exception Obj_Error)
		{
			System.out.println(" **** Pagar ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}
	}
	
	public static void sacaGuardadito() throws IOException
	{
		System.out.println("***** Flujo Sacar **********");
		try 
		{
			// ****************** Abre Velo de Transacciones *********************
			Thread.sleep(6000);
			Obj_Webdriver_Espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@content-desc,'Saca') and @clickable='true']")));
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Saca') and @clickable='true']").click();
			System.out.println(" **** Clic en Saca ************** OK");
			Thread.sleep(5000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Cajero') and @clickable='true']").click();
			System.out.println(" **** Clic en Cajero ************** OK");	
			Thread.sleep(4000);
			funciones.contrasena(Str_Contrasena,Obj_Android_Driver);
			
			System.out.println(" **** Flujo Sacar ************** OK");

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Flujo Sacar ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}	
}