package org.okenproject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

public class CustomerTestsSelenium extends TestBase{

    @Test
    public void add_a_customer_with_good_data() throws InterruptedException {

        Actions action = new Actions(driver);

        //Attente avant remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        Thread.sleep(1000);
        //Aller dans la partie customer
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[4]")).click();

        Thread.sleep(1000);
        //Aller dans la partie customer "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-customer/div/div[2]/table/tr/td[2]/a/strong/i")).click();

        Thread.sleep(1000);


        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Jean");
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Renard");
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("+32 41234567");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("jean.renard@example.be");
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("25 Rue de Bruxelles, Bruxelles");
        driver.findElement(By.name("password")).sendKeys("bElgique123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("bElgique123!");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-customer/div/div/div[2]/button")).click();

    }


    @Test
    public void add_a_customer_with_bad_data() throws InterruptedException {

        Actions action = new Actions(driver);

        //Attente avant remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        Thread.sleep(1000);
        //Aller dans la partie customer
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[4]")).click();

        Thread.sleep(1000);
        //Aller dans la partie customer "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-customer/div/div[2]/table/tr/td[2]/a/strong/i")).click();

        Thread.sleep(1000);


        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Jean.12.?2");
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Rena@@rd");
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("+32 41234567");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("jeanrenarample.be");
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("25 Rue de Bruxelles, Bruxelles");
        driver.findElement(By.name("password")).sendKeys("bElgique123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("bElgique123!");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-customer/div/div/div[2]/button")).click();

    }

    @Test
    public void update_a_customer_with_good_data() throws InterruptedException {

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des managers
        driver.get("http://oken-cars.oken.lan/customer-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-customer-list/div/table/tbody/tr[1]/td[5]/div/div[2]/button")).click();

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Charpentier.david@gmail.com"); // Nom
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Charpentier"); // Prénom
        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-customer/div/div/div[2]/button")).click();


    }

    @Test
    public void update_a_customer_with_bad_data() throws InterruptedException {

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des managers
        driver.get("http://oken-cars.oken.lan/customer-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-customer-list/div/table/tbody/tr[1]/td[5]/div/div[2]/button")).click();

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Charpentier.damail.com"); // Nom
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("1Charpentier"); // Prénom
        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-customer/div/div/div[2]/button")).click();


    }

    @Test
    public void display_customer_list(){
        //Accès à la page web
        driver.get("http://oken-cars.oken.lan/customer-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();
    }

    @Test
    public void display_one_customer() throws InterruptedException {
        //Attente avant bon remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/customer-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Affichage d'un manager
        driver.findElement(By.xpath("/html/body/app-root/app-customer-list/div/table/tbody/tr/td[5]/div/div[1]/button")).click();
    }

    @Test
    public void delete_a_customer() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        driver.get("http://oken-cars.oken.lan/customer-list");

        Thread.sleep(1000);

        //Suppression d'un manager
        driver.findElement(By.xpath("/html/body/app-root/app-customer-list/div/table/tbody/tr[1]/td[5]/div/div[3]/button")).click();
    }


}
