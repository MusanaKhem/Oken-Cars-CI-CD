import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../service/customer.service';
import { Customer } from '../models/customer';


@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})

export class EditCustomerComponent implements OnInit {
             id: number | any;
               customer =  new Customer();

              namePattern = /^[A-Za-z- ]+$/;//Format Nom
              emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
              phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
              addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

              constructor(
                 private route: ActivatedRoute,
                 private router: Router,
                 private customerService: CustomerService){}


              ngOnInit(){

                      this.id = this.route.snapshot.paramMap.get('id');

                      this.customerService.getCustomer(this.id).subscribe((response:any)=>{

                       this.customer = response;
                      // console.log(response);
                      })

              }



              editCustomer(){
                       if ( this.customer &&
                            this.validateLastName() &&
                            this.validateFirstName() &&
                            this.validateEmailAdress() &&
                            this.validatePhone() &&
                            this.validateAddressCharacters()){
                                 this.customerService.updateCustomer(this.id, this.customer).subscribe(()=>{
                                    this.router.navigateByUrl("/customer/"+ this.id);
                                 })
                            }
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




}

