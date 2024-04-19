import { Component,Input } from '@angular/core';
import { CarService } from '../service/car.service';
import { Router } from '@angular/router';
import { Car } from '../model/car';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-detail-car',
  templateUrl: './detail-car.component.html',
  styleUrls: ['./detail-car.component.scss']
})
export class DetailCarComponent {

        @Input() car!: Car;
        car$!: Observable<Car>;

          constructor(private carService: CarService,
                                   private route: ActivatedRoute,
                                   private router: Router
                                   ) { }

            ngOnInit(): void {
                  const iD = +this.route.snapshot.params['id'];//Récupération des informations du manager
                  this.car$ = this.carService.getCarById(iD);
            }

}
