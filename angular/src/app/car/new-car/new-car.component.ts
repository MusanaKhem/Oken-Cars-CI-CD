import { Component } from '@angular/core';
import { CarService } from '../service/car.service';
import { Router } from '@angular/router';
import { Car } from '../model/car';

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.scss']
})
export class NewCarComponent {

    car: Car = new Car();

    constructor(private carService: CarService,public router:Router) { }


              addCar(): void {
                  if (this.car) {
                        this.carService.newCar(this.car).subscribe((createdCar: Car) => {
                            console.log("voiture crée");
                            this.router.navigateByUrl(`/car/${createdCar.id}`);
                        });
                  }

              }


}
