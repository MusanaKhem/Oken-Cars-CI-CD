package org.okenproject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

public class OwnerTestsSelenium extends TestBase{

    @Test
    public void add_an_owner_with_good_data() throws InterruptedException {

        Actions action = new Actions(driver);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie owner
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[3]")).click();

        //Aller dans la partie owner "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-owner/div/div[2]/table/tr/td[2]/a")).click();

        Thread.sleep(1000);

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Pierre");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Ngomo");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("+241 0612345678");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pierre.ngomo@example.ga");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("34 Avenue Libreville, Libreville");   Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("aGabon123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("aGabon123!");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-owner/div/div/div[2]/button")).click();

    }

    @Test
    public void add_an_owner_with_bad_data() throws InterruptedException {

        Actions action = new Actions(driver);

        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie owner
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[3]")).click();

        //Aller dans la partie owner "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-owner/div/div[2]/table/tr/td[2]/a")).click();

        Thread.sleep(1000);

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Pier123re");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Ngomo///");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("12345678");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pierre.ngomoexample.ga");   Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("34 Avenue Libreville, Libreville");   Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("aGabon123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("aGabon123!");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-owner/div/div/div[2]/button")).click();

    }

    @Test
    public void update_owner_with_good_data() throws InterruptedException {
        driver.get("http://oken-cars.oken.lan/owner-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-owner-list/div/table/tbody/tr/td[5]/div/div[2]/button")).click();

        Thread.sleep(1000);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("ngoma.pierre@yahoo.fr"); // Adresse mail

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        Thread.sleep(1000);
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Ngoma"); // Nom

        driver.findElement(By.xpath("/html/body/app-root/app-edit-owner/div/div/div[2]/button")).click();

    }

    @Test
    public void update_owner_with_bad_data() throws InterruptedException {
        driver.get("http://oken-cars.oken.lan/owner-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("/html/body/app-root/app-owner-list/div/table/tbody/tr/td[5]/div/div[2]/button")).click();

        Thread.sleep(1000);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("ngoma.pierreyahoo.fr"); // Adresse mail

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        Thread.sleep(1000);
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Ngoma::"); // Nom

        driver.findElement(By.xpath("/html/body/app-root/app-edit-owner/div/div/div[2]/button")).click();

    }

    @Test
    public void display_owner_list() throws InterruptedException{
        //Attente avant bon remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/owner-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();
    }

    @Test
    public void display_one_owner() throws InterruptedException{
        //Attente avant bon remplissage
        Thread.sleep(1000);

        driver.get("http://oken-cars.oken.lan/owner-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        Thread.sleep(1000);

        //Affichage d'un manager
        driver.findElement(By.xpath("/html/body/app-root/app-owner-list/div/table/tbody/tr/td[5]/div/div[1]/button")).click();

    }

    @Test
    public void delete_an_owner() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        driver.get("http://oken-cars.oken.lan/owner-list");

        Thread.sleep(1000);

        //Suppression d'un owner
        driver.findElement(By.xpath("/html/body/app-root/app-owner-list/div/table/tbody/tr/td[5]/div/div[3]/button")).click();

    }


}
