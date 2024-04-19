import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Owner } from '../models/owner';
import { OwnerService } from '../service/owner.service';


@Component({
  selector: 'app-register-owner',
  templateUrl: './register-owner.component.html',
  styleUrls: ['./register-owner.component.css']
})
export class RegisterOwnerComponent implements OnInit {

    //owner = new Owner();
    owner: Owner = new Owner();
    confirmPassword: string = ''; // Track password confirmation
    passwordInvalid = false; // Property to track password validity
    passwordsMatch = true; // Property to track password matching

   // namePattern = /^[A-Za-z- ]+$/;//Format Nom
    namePattern = /^[\p{L} -]+$/u; // Format Nom
    emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
    phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
    addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

  constructor(private router: Router, private ownerService: OwnerService){}

  ngOnInit(){

  }

      saveOwner(){
              if (this.owner &&
                  !this.passwordInvalid &&
                  this.passwordsMatch &&
                  this.validateLastName() &&
                  this.validateFirstName() &&
                  this.validateEmailAdress() &&
                  this.validatePhone()) {
                   this.ownerService.createOwner(this.owner).subscribe((createdOwner: Owner) => {
                        this.router.navigateByUrl(`/owner/${createdOwner.id}`);
                   });
              }
      }

            /* Password validation logic (S'assurer que le mot de passe contient
                   une minuscule, majuscule , caractère spécial et un chiffre)*/
                  validatePassword(): void {
                      const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                      this.passwordInvalid = !passwordRegex.test(this.owner.password);
                      this.validatePasswordMatch(); // Appel de la validation de la correspondance des mots de passe
                  }

                   /* Valider la correspondance des mots de passe */
                    validatePasswordMatch(): void {
                      this.passwordsMatch = this.owner.password === this.confirmPassword;
                    }
                    //Vérification de la validité du nom
                          validateLastName(): boolean {
                            return this.namePattern.test(this.owner.lastname);
                          }

                        //Vérification de la validité du prénom
                       validateFirstName(): boolean {
                            return this.namePattern.test(this.owner.firstname);
                       }

                       //Vérification de la validité de l'adresse mail
                           validateEmailAdress(): boolean {
                                 return this.emailPattern.test(this.owner.email);
                           }

                        //Vérification de la validité du numéro de téléphone
                           validatePhone(): boolean {
                                 return this.phonePattern.test(this.owner.phone);
                           }

                      validateAddressCharacters(): boolean {
                          return this.addressPattern.test(this.owner.address);
                      }

           isPasswordValid(): boolean {
                            if (this.owner.password && this.confirmPassword) {
                              const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                              return passwordRegex.test(this.owner.password) && this.owner.password === this.confirmPassword;
                            }
                            return false;
                          }



}
