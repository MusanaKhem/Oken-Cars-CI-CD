import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Driver } from '../models/driver';
import { DriverService } from '../service/driver.service';





@Component({
  selector: 'app-edit-driver',
  templateUrl: './edit-driver.component.html',
  styleUrls: ['./edit-driver.component.css']
})
export class EditDriverComponent implements OnInit {
         id: number | any;
           driver =  new Driver();

           namePattern = /^[A-Za-z- ]+$/;//Format Nom
           emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
           phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
           addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse

           constructor(
              private route: ActivatedRoute,
              private router: Router,
              private driverService: DriverService){}


           ngOnInit(){

             this.id = this.route.snapshot.paramMap.get('id');

             this.driverService.getDriver(this.id).subscribe((response:any)=>{

              this.driver = response;
             // console.log(response);
             })

           }

           goDriverEdit(){

             return this.router.navigateByUrl("/driver/"+ this.id);

           }

           editDriver(){
                  if ( this.driver &&
                       this.validateLastName() &&
                       this.validateFirstName() &&
                       this.validateEmailAdress() &&
                       this.validatePhone() &&
                       this.validateAddressCharacters()){
                               this.driverService.updateDriver(this.id, this.driver).subscribe(()=>{
                                      this.router.navigateByUrl("/driver/"+ this.id);
                               })
                  }

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

}

