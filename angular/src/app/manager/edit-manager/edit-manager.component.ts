import { Component ,OnInit, Input,ViewChild} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { NgForm } from '@angular/forms';
import { ManagerService } from '../service/manager.service';
import { Manager } from '../models/manager';

@Component({
  selector: 'app-edit-manager',
  templateUrl: './edit-manager.component.html',
  styleUrls: ['./edit-manager.component.scss']
})
export class EditManagerComponent {
  manager: Manager = new Manager();
     manager$!: Observable<Manager>;

      namePattern = /^[A-Za-z- ]+$/;//Format Nom
      emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//Format Adresse mail
      phonePattern = /^\+\d{1,4}\s?\d{6,14}$/; //Format Numéro de téléphone
      addressPattern = /^[a-zA-Z0-9\s,.-]+$/;//Format Adresse


             constructor(private managerService: ManagerService,
                                   private router: ActivatedRoute,
                                   private route: Router
                                   ) { }

             ngOnInit(): void {
                               //   const iD = +this.router.snapshot.params['id'];
                               //   this.manager$ = this.managerService.getManagerById(iD);
                                 const iD = +this.router.snapshot.params['id'];  //Récupération des informations relatives au manager
                                   this.managerService.getManagerById(iD).subscribe((data) => {
                                     this.manager = data;
                                   });
                           }

               //Fonction de modifications des informations d'un manager
               updateManager(): void {
                   if ( this.manager &&
                        this.validateLastName() &&
                        this.validateFirstName() &&
                        this.validateEmailAdress() &&
                        this.validatePhone() &&
                        this.validateAddressCharacters()) {
                     this.managerService.updateManager(this.manager.id, this.manager).subscribe(() => {
                       this.route.navigateByUrl(`/manager/${this.manager.id}`);
                     });
                   }
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




}
