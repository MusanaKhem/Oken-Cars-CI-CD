import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Manager } from '../models/manager';
import { ManagerService } from '../service/manager.service';



@Component({
  selector: 'app-register-manager',
  templateUrl: './register-manager.component.html',
  styleUrls: ['./register-manager.component.scss']
})
export class RegisterManagerComponent {
      manager: Manager = new Manager();
          confirmPassword: string = ''; // Track password confirmation
          passwordInvalid = false; // Property to track password validity
          passwordsMatch = true; // Property to track password matching

          namePattern = /^[A-Za-z- ]+$/;//Format Nom
          emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
          phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
          addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

          constructor(private managerService: ManagerService,public router:Router) { }

          /* Création d'un nouveau manager et redirection vers sa page d'info pour vérification*/
          addManager(): void {
              //this.validatePasswordMatch();
              if (this.manager &&
                  !this.passwordInvalid &&
                  this.passwordsMatch &&
                  this.validateLastName() &&
                  this.validateFirstName() &&
                  this.validateEmailAdress() &&
                  this.validatePhone() ) {
                    this.managerService.newManager(this.manager).subscribe((createdManager: Manager) => {
                              this.router.navigateByUrl(`/manager/${createdManager.id}`);
                    });
              }

          }

          /* Password validation logic (S'assurer que le mot de passe contient
           une minuscule, majuscule , caractère spécial et un chiffre)*/
          validatePassword(): void {
              const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
              this.passwordInvalid = !passwordRegex.test(this.manager.password);
              this.validatePasswordMatch(); // Appel de la validation de la correspondance des mots de passe
          }

           /* Valider la correspondance des mots de passe */
            validatePasswordMatch(): void {
              this.passwordsMatch = this.manager.password === this.confirmPassword;
            }

                    //Vérification de la validité du nom
                          validateLastName(): boolean {
                            return this.namePattern.test(this.manager.lastname);
                          }

                        //Vérification de la validité du prénom
                       validateFirstName(): boolean {
                            return this.namePattern.test(this.manager.firstname);
                       }

                       //Vérification de la validité de l'adresse mail
                           validateEmailAdress(): boolean {
                                 return this.emailPattern.test(this.manager.email);
                           }

                        //Vérification de la validité du numéro de téléphone
                           validatePhone(): boolean {
                                 return this.phonePattern.test(this.manager.phone);
                           }

                      validateAddressCharacters(): boolean {
                          return this.addressPattern.test(this.manager.address);
                      }

                isPasswordValid(): boolean {
                  if (this.manager.password && this.confirmPassword) {
                    const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                    return passwordRegex.test(this.manager.password) && this.manager.password === this.confirmPassword;
                  }
                  return false;
                }




}
