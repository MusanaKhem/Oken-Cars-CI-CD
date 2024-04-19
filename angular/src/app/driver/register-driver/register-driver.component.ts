import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DriverService } from '../service/driver.service';
import { Driver } from '../models/driver';

@Component({
  selector: 'app-register-driver',
  templateUrl: './register-driver.component.html',
  styleUrls: ['./register-driver.component.css']
})
export class RegisterDriverComponent implements OnInit {

       // driver = new Driver();
        driver: Driver = new Driver();
        confirmPassword: string = ''; // Track password confirmation
        passwordInvalid = false; // Property to track password validity
        passwordsMatch = true; // Property to track password matching

        namePattern = /^[\p{L} -]+$/u; // Format Nom
        emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
        phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
        addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

        constructor(private router: Router, private driverService: DriverService){}

        ngOnInit(){

        }

            saveDriver():void{
                    if (this.driver &&
                        !this.passwordInvalid &&
                        this.passwordsMatch &&
                        this.validateLastName() &&
                        this.validateFirstName() &&
                        this.validateEmailAdress() &&
                        this.validatePhone() &&
                        this.driver.permit != " ") {
                          this.driverService.createDriver(this.driver).subscribe((createdDriver: Driver) => {
                                this.router.navigateByUrl(`/driver/${createdDriver.id}`);
                          });
                    }
            }

           /* Password validation logic (S'assurer que le mot de passe contient
             une minuscule, majuscule , caractère spécial et un chiffre)*/
            validatePassword(): void {
                const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                this.passwordInvalid = !passwordRegex.test(this.driver.password);
                this.validatePasswordMatch(); // Appel de la validation de la correspondance des mots de passe
            }

             /* Valider la correspondance des mots de passe */
              validatePasswordMatch(): void {
                this.passwordsMatch = this.driver.password === this.confirmPassword;
              }
                     //Vérification de la validité du nom
                            validateLastName(): boolean {
                              return this.namePattern.test(this.driver.lastname);
                            }

                          //Vérification de la validité du prénom
                         validateFirstName(): boolean {
                              return this.namePattern.test(this.driver.firstname);
                         }

                         //Vérification de la validité de l'adresse mail
                             validateEmailAdress(): boolean {
                                   return this.emailPattern.test(this.driver.email);
                             }

                          //Vérification de la validité du numéro de téléphone
                             validatePhone(): boolean {
                                   return this.phonePattern.test(this.driver.phone);
                             }

                        validateAddressCharacters(): boolean {
                            return this.addressPattern.test(this.driver.address);
                        }

             isPasswordValid(): boolean {
                              if (this.driver.password && this.confirmPassword) {
                                const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                                return passwordRegex.test(this.driver.password) && this.driver.password === this.confirmPassword;
                              }
                              return false;
                            }

                          validatePermit(): boolean | string {
                            if (this.driver.permit && this.driver.permit.trim() !== '') {
                              return true;
                            } else {
                              return false;
                            }
                          }







}
