import { Component, OnInit ,Input} from '@angular/core';
import { Manager } from '../models/manager';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { ManagerService } from '../service/manager.service';


@Component({
  selector: 'app-manager-list',
  templateUrl: './manager-list.component.html',
  styleUrls: ['./manager-list.component.scss']
})
export class ManagerListComponent {

       //  managers$! : Observable<Manager[]>;
         managers: Manager[] | any;
          searchText: string="";

                constructor(private managerService: ManagerService,
                private router: Router){}

                ngOnInit(){
                     //Afficher la liste des managers dans l'ordre croissant des ID avec les opérateurs 'sort' et 'map'
                      this.managerService.getAllManagers().subscribe((response:any)=>{
                      this.managers=response;

                     });
                }

                //Fonction du boutton qui permet d'afficher les détails du manager en fonction de son id
                onViewManager(managerId: number) {
                   this.router.navigateByUrl(`/manager/${managerId}`);
                }

                //Boutton qui mène à la page de mise à jour des informations du manager
                onManagerUpdate(managerId: number) {
                    this.router.navigateByUrl(`/edit-manager/${managerId}`);
                }

/*
               //Fonction du boutton de suppression du manager
               onDeleteManager(managerId: number) {
                   console.log("suppression du manager ID=" + managerId);
                   this.managerService.deleteManagerById(managerId).subscribe(
                     () => {
                       console.log("Manager supprimé avec succès.");
                         window.location.reload(); //Rechargement de la page html
                     },
                     (error) => {
                       console.error("Erreur lors de la suppression du manager : ", error);
                     }
                   );
               }

*/
               onDeleteManager(managerId: number){
                    this.managerService.deleteManagerById(managerId).subscribe((response:any)=>{
                      this.managers = this.managers.filter((manager:any)=>{
                        return managerId! = manager.managerId;
                      })
                    });
                    window.location.reload();

              }

}
