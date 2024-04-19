package org.okenproject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;

public class CarTestsSelenium extends TestBase{

    @Test
    public void add_a_car() throws InterruptedException {

        Actions action = new Actions(driver);
        driver.get("http://oken-cars.oken.lan/new-car");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        Thread.sleep(1000);

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        WebElement selectBrand = driver.findElement(By.id("brandOfCars"));
        Select select_brand = new Select(selectBrand);
        select_brand.selectByValue("Tesla");
        Thread.sleep(1000);

        driver.findElement(By.name("mileage")).sendKeys("12000");  Thread.sleep(1000);

        //Selection du modèle de la voiture
        WebElement selectModel = driver.findElement(By.id("inputGroupSelect01"));
        Select select_model = new Select(selectModel);
        select_model.selectByValue("Model S");
        Thread.sleep(1000);

        //Selection d'une couleur
        WebElement selectColor = driver.findElement(By.id("inputGroupSelect02"));
        Select select_color = new Select(selectColor);
        select_color.selectByValue("Pink");

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        Thread.sleep(1000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-new-car/div/div/form/button")).click();

    }

    @Test
    public void update_a_car_with_good_data() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des managers
        driver.get("http://oken-cars.oken.lan/car-list");

        Thread.sleep(3000);

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("//*[@id=\"editButton\"]")).click();

        //Selection d'une couleur
        WebElement selectColor = driver.findElement(By.id("inputGroupSelect02"));
        Select select_color = new Select(selectColor);
        select_color.selectByValue("Black");

        Thread.sleep(5000);

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-car/div/div/div[2]/form/button")).click();

    }



    @Test
    public void display_car_list(){
        //Accès à la page web
        driver.get("http://oken-cars.oken.lan/car-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();
    }

    @Test
    public void display_one_car() throws InterruptedException {
        //Attente avant bon remplissage
        Thread.sleep(2000);

        driver.get("http://oken-cars.oken.lan/car-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Affichage d'un manager
        driver.findElement(By.xpath("//*[@id=\"viewButton\"]")).click();
    }

    @Test
    public void delete_one_car() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        driver.get("http://oken-cars.oken.lan/car-list");

        Thread.sleep(1000);

        //Suppression d'un manager
        driver.findElement(By.xpath("//*[@id=\"deleteButton\"]")).click();
    }



}
