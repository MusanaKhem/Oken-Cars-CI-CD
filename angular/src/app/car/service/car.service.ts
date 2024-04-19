import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environments';
import { HttpClient } from '@angular/common/http';
import { Car } from '../model/car';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

      baseUrl = environment.API_BASE_URL;

      constructor(private http: HttpClient) { }

       //Récupération de la liste de toutes les voitures
       getAllCars(): Observable<Car[]>{
            return this.http.get<Car[]>('/cars');
       }

       //Accéder à une voiture en fonction de son ID
       getCarById(id: number): Observable<Car>  {
             return this.http.get<Car>(`/cars/` +id);
       }

       //Supprimer une voiture en fonction de ID
       deleteCarById(id: number): Observable<Car> {
             return this.http.delete<Car>(`/cars/delete/` +id);
       }

       // Mettre à jour les informations d'un manager
       updateCar(id: number, car: Car): Observable<Car> {
              return this.http.put<Car>(`/cars/update/` +id, car);
       }

       // Mettre à jour les informations d'un manager
       newCar(car: Car): Observable<Car> {
              return this.http.post<Car>(`/cars/addCar`, car);
       }


}
