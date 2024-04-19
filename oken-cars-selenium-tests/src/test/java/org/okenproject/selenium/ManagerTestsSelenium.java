package org.okenproject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ManagerTestsSelenium extends TestBase{

    @Test
    public void add_a_manager_with_good_data() throws InterruptedException {

        Actions action = new Actions(driver);
        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie manager
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[2]")).click(); Thread.sleep(1000); //Attendre 1 seconde

        //Aller dans la partie manager "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-manager/div/div[2]/table/tr/td[2]/a")).click();
        Thread.sleep(1000);

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.name("lastname")).sendKeys("Sow"); Thread.sleep(1000);// Nom
        driver.findElement(By.name("firstname")).sendKeys("Mamadou"); Thread.sleep(1000);// Prénom
        driver.findElement(By.name("email")).sendKeys("mamadou.sow@hotmail.fr"); Thread.sleep(1000);// Adresse mail
        driver.findElement(By.name("phone")).sendKeys("+221 771234567"); Thread.sleep(1000);// Numéro de téléphone
        driver.findElement(By.name("address")).sendKeys("12 Rue Dakar, Dakar"); Thread.sleep(1000);// Adresse
        driver.findElement(By.name("password")).sendKeys("Senegal123!"); // Mot de passe
        driver.findElement(By.name("confirmPassword")).sendKeys("Senegal123!"); // Confirmation du mot de passe

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-manager/div/div/form/button")).click();

    }

    @Test
    public void add_a_manager_with_bad_data() throws InterruptedException {

        Actions action = new Actions(driver);
        driver.get("http://oken-cars.oken.lan/home");

        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Aller dans la partie manager
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/a[2]")).click(); Thread.sleep(1000); //Attendre 1 seconde

        //Aller dans la partie manager "inscription"
        driver.findElement(By.xpath("/html/body/app-root/app-login-manager/div/div[2]/table/tr/td[2]/a")).click();
        Thread.sleep(1000);

        //Remplissage du formulaire
        //Automatisation remplissage et validation d'un formulaire (VALIDITE)
        driver.findElement(By.name("lastname")).sendKeys("Sow123"); Thread.sleep(1000);// Nom
        driver.findElement(By.name("firstname")).sendKeys("Mamadou"); Thread.sleep(1000);// Prénom
        driver.findElement(By.name("email")).sendKeys("mamadou.sowotmail.fr"); Thread.sleep(1000);// Adresse mail
        driver.findElement(By.name("phone")).sendKeys("+221 771234567"); Thread.sleep(1000);// Numéro de téléphone
        driver.findElement(By.name("address")).sendKeys("12 Rue Dakar, Dakar"); Thread.sleep(1000);// Adresse
        driver.findElement(By.name("password")).sendKeys("Senegal123!"); // Mot de passe
        driver.findElement(By.name("confirmPassword")).sendKeys("Senega23!"); // Confirmation du mot de passe

        //Simuler l'appui sur la touche tabulation
        action.sendKeys(Keys.TAB).perform();

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-register-manager/div/div/form/button")).click();

    }

    @Test
    public void update_a_manager_with_good_data() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des managers
        driver.get("http://oken-cars.oken.lan/manager-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("//*[@id=\"editButton\"]")).click();

        Thread.sleep(1000);

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Ndiaye"); Thread.sleep(1000);// Nom

        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Ibrahima"); Thread.sleep(1000);// Prénom

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("Ndiaye.Ibrahima@78outlook.fr"); Thread.sleep(1000);// Adresse mail

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-manager/div/div/div[2]/form/button")).click();

    }

    @Test
    public void update_a_manager_with_bad_data() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        //Accès à la liste des managers
        driver.get("http://oken-cars.oken.lan/manager-list");

        //Accès au boutton de mise à jour
        driver.findElement(By.xpath("//*[@id=\"editButton\"]")).click();

        Thread.sleep(1000);

        //remplissage formulaire de mise a jour (effacer ancien contenu et le remplacer
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("123"); Thread.sleep(1000);// Nom

        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Sa1m"); Thread.sleep(1000);// Prénom

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("Ndiaye.Ibrahima78outlook.fr"); Thread.sleep(1000);// Adresse mail

        //Validation du formulaire
        driver.findElement(By.xpath("/html/body/app-root/app-edit-manager/div/div/div[2]/form/button")).click();

    }

    @Test
    public void display_manager_list(){
        //Accès à la page web
        driver.get("http://oken-cars.oken.lan/manager-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();
    }

    @Test
    public void display_one_manager() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("http://oken-cars.oken.lan/manager-list");

        //maximiser affichage ecran
        driver.manage().window().maximize();
        Thread.sleep(1000);
        //Affichage d'un manager
        driver.findElement(By.xpath("//*[@id=\"viewButton\"]")).click();
    }

    @Test
    public void delete_a_manager() throws InterruptedException {
        //maximiser affichage ecran
        driver.manage().window().maximize();

        driver.get("http://oken-cars.oken.lan/manager-list");

        Thread.sleep(1000);

        //Suppression d'un manager
        driver.findElement(By.xpath("//*[@id=\"deleteButton\"]")).click();
    }


}
