import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environments';
import { Owner } from '../models/owner';
import { Car } from '../../car/model/car';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  baseUrl = environment.API_BASE_URL

  constructor(private http: HttpClient) { }

        createOwner(owner: Owner):Observable<Owner>{

        return this.http.post<Owner>("/owners/addOwner", owner)

        }

       updateOwner(id: number, owner: Owner) {

         return this.http.put("/owners/update/"+ id, owner);

        }
        getOwnerList() {

          return this.http.get("/owners");

        }

        getOwner(id: number): Observable<Owner> {

          return this.http.get<Owner>("/owners/edit/" + id);

        }

        deleteOwner(id: number) {


          return this.http.delete("/owners/delete/" + id);
        }

        //Propriétaire qui ajoute sa voiture dans l'appli
        AddMyCar(id: number,car: Car): Observable<Car> {
             return this.http.post<Car>("/owners/addCar/"+id, car);
        }

        //Afficher la ou les voiture(s) du propriétaire
         getCarsByOwnerId(id: number): Observable<Car>  {
                     return this.http.get<Car>(`/owners/all-cars/` +id);
         }

}
