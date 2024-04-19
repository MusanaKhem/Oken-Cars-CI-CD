import { Component } from '@angular/core';
import { CarService } from '../service/car.service';
import { Router } from '@angular/router';
import { Car } from '../model/car';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.scss']
})
export class CarListComponent {

       cars: Car[] | any;

       constructor(private carService: CarService,
                       private router: Router){}

         ngOnInit(){
              this.carService.getAllCars().subscribe((response:any)=>{
              this.cars=response;
              });
         }

          onViewCar(carId: number) {
                            this.router.navigateByUrl(`/car/${carId}`);
                         }

          onCarUpdate(carId: number) {
                              this.router.navigateByUrl(`/edit-car/${carId}`);
                          }

            onDeleteCar(carId: number) {
                             console.log("suppression de la voiture ID=" + carId);
                             this.carService.deleteCarById(carId).subscribe(
                               () => {
                                 console.log("voiture supprimée avec succès.");
                                   window.location.reload(); //Rechargement de la page html
                               },
                               (error) => {
                                 console.error("Erreur lors de la suppression du manager : ", error);
                               }
                             );
                         }

}
