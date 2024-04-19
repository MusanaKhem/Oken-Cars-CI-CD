import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwnerService } from '../service/owner.service';
import { Owner } from '../models/owner';


@Component({
  selector: 'app-edit-owner',
  templateUrl: './edit-owner.component.html',
  styleUrls: ['./edit-owner.component.css']
})

export class EditOwnerComponent implements OnInit {

        id: number | any;
        owner =  new Owner();

        namePattern = /^[A-Za-z- ]+$/;//Format Nom
        emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
        phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
        addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse


              constructor(
                 private route: ActivatedRoute,
                 private router: Router,
                 private ownerService: OwnerService){}


              ngOnInit(){

                      this.id = this.route.snapshot.paramMap.get('id');

                      this.ownerService.getOwner(this.id).subscribe((response:any)=>{

                       this.owner = response;
                      // console.log(response);
                      })

              }

              editOwner(){

                    if ( this.owner &&
                         this.validateLastName() &&
                         this.validateFirstName() &&
                         this.validateEmailAdress() &&
                         this.validatePhone() &&
                         this.validateAddressCharacters()){
                             this.ownerService.updateOwner(this.id, this.owner).subscribe(()=>{
                                this.router.navigateByUrl("/owner/"+ this.id);
                             })
                         }
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




}

