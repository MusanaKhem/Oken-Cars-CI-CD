import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../models/customer';
import { CustomerService } from '../service/customer.service';


@Component({
  selector: 'app-register-customer',
  templateUrl: './register-customer.component.html',
  styleUrls: ['./register-customer.component.css']
})
export class RegisterCustomerComponent implements OnInit {

    //Customer = new Customer();
    customer: Customer = new Customer();
    confirmPassword: string = ''; // Track password confirmation
    passwordInvalid = false; // Property to track password validity
    passwordsMatch = true; // Property to track password matching

    namePattern = /^[\p{L} -]+$/u; // Format Nom
    emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
    phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
    addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

  constructor(private router: Router, private customerService: CustomerService){}

  ngOnInit(){

  }

      saveCustomer(){
              if (this.customer &&
                  !this.passwordInvalid &&
                  this.passwordsMatch &&
                  this.validateLastName() &&
                                    this.validateFirstName() &&
                                    this.validateEmailAdress() &&
                                    this.validatePhone()) {
                   this.customerService.createCustomer(this.customer).subscribe((createdCustomer: Customer) => {
                        this.router.navigateByUrl(`/customer/${createdCustomer.id}`);
                   });
              }
      }

            /* Password validation logic (S'assurer que le mot de passe contient
                   une minuscule, majuscule , caractère spécial et un chiffre)*/
                  validatePassword(): void {
                      const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                      this.passwordInvalid = !passwordRegex.test(this.customer.password);
                      this.validatePasswordMatch(); // Appel de la validation de la correspondance des mots de passe
                  }

                   /* Valider la correspondance des mots de passe */
                    validatePasswordMatch(): void {
                      this.passwordsMatch = this.customer.password === this.confirmPassword;
                    }

                //Vérification de la validité du nom
                              validateLastName(): boolean {
                                return this.namePattern.test(this.customer.lastname);
                              }

                            //Vérification de la validité du prénom
                           validateFirstName(): boolean {
                                return this.namePattern.test(this.customer.firstname);
                           }

                           //Vérification de la validité de l'adresse mail
                               validateEmailAdress(): boolean {
                                     return this.emailPattern.test(this.customer.email);
                               }

                            //Vérification de la validité du numéro de téléphone
                               validatePhone(): boolean {
                                     return this.phonePattern.test(this.customer.phone);
                               }

                          validateAddressCharacters(): boolean {
                              return this.addressPattern.test(this.customer.address);
                          }

           isPasswordValid(): boolean {
                            if (this.customer.password && this.confirmPassword) {
                              const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*\s).{10,}$/;
                              return passwordRegex.test(this.customer.password) && this.customer.password === this.confirmPassword;
                            }
                            return false;
                          }





}
