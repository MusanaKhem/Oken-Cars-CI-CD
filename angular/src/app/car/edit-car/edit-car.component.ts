import { Component } from '@angular/core';
import { Car } from '../model/car';
import { CarService } from '../service/car.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.scss']
})
export class EditCarComponent {
     car: Car = new Car();
     cars$!: Observable<Car>;

     constructor(private carService: CarService,
                                        private router: ActivatedRoute,
                                        private route: Router
                                        ) { }

        ngOnInit(): void {
                const iD = +this.router.snapshot.params['id'];  //Récupération des informations relatives au manager
                this.carService.getCarById(iD).subscribe((data) => {
                this.car = data;
              });
        }


        updateCarInfo(): void {
              if (this.car.id !== undefined) {
                this.carService.updateCar(this.car.id, this.car).subscribe(() => {
                  // Logique de mise à jour réussie
                  this.route.navigateByUrl(`/car/${this.car.id}`);
                });
              } else {
                // Gérer le cas où this.car.id est undefined
                console.error("ID de la voiture non défini.");
              }

        }


}
