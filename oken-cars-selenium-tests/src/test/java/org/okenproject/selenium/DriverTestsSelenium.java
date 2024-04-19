package org.okenproject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;

public class DriverTestsSelenium extends TestBase{

    @Test
    public void add_a_driver_with_good_data() throws InterruptedException {
        Actions action = new Actions(driver);

        //Attente avant bon remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie driver
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[1]")).click();

        //Aller dans la partie driver "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-driver/div/div[2]/table/tr/td[2]/a/strong/i")).click();

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Élise"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Dupont"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("+33 612345678"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("elise.dupont@example.com"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("45 Rue de la Liberte, Paris"); Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("MotDePasse123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("MotDePasse123!");

        Thread.sleep(1000);
        WebElement selectPermitType= driver.findElement(By.id("permit"));
        Select select_permit_type = new Select(selectPermitType);
        select_permit_type.selectByValue("B");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-driver/div/div/div[2]/button")).click();

    }

    @Test
    public void add_a_driver_with_bad_data() throws InterruptedException {
        Actions action = new Actions(driver);

        //Attente avant bon remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie driver
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[1]")).click();

        //Aller dans la partie driver "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-driver/div/div[2]/table/tr/td[2]/a/strong/i")).click();

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Élise12.???"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Dupon././t"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("345678"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("elise.dupontexample.com"); Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("45 Rue de la Liberte, Paris"); Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("MotDePasse123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("MotDePasse123!");

        Thread.sleep(1000);
        WebElement selectPermitType= driver.findElement(By.id("permit"));
        Select select_permit_type = new Select(selectPermitType);
        select_permit_type.selectByValue("B");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-driver/div/div/div[2]/button")).click();

    }

    @Test
    public void update_driver_with_good_data() throws InterruptedException {

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des drivers
        driver.get("http://oken-cars.oken.lan/driver-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-driver-list/div/table/tbody/tr[1]/td[5]/div/div[2]/button")).click();

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Elsa");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Cartier");
        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-driver/div/div/div[2]/button")).click();

    }

    @Test
    public void update_driver_with_bad_data(){

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des drivers
        driver.get("http://oken-cars.oken.lan/driver-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-driver-list/div/table/tbody/tr[1]/td[5]/div/div[2]/button")).click();

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("El123sa");

        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));//Timer 25 secondes

        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("CartiQ12...er");

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-driver/div/div/div[2]/button")).click();

    }

    @Test
    public void display_driver_list(){
        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la page web
        driver.get("http://oken-cars.oken.lan/driver-list");
    }

    @Test
    public void display_one_driver() throws InterruptedException {
        driver.get("http://oken-cars.oken.lan/driver-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        Thread.sleep(1000);

        //Affichage du driver
        driver.findElement(By.xpath("/html/body/app-root/app-driver-list/div/table/tbody/tr/td[5]/div/div[1]/button")).click();
    }

    @Test
    public void delete_a_driver() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        driver.get("http://oken-cars.oken.lan/driver-list");

        Thread.sleep(1000);

        //Suppression d'un driver
        driver.findElement(By.xpath("/html/body/app-root/app-driver-list/div/table/tbody/tr/td[5]/div/div[3]/button")).click();

    }


}
