import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environments';
import { Driver } from '../models/driver';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  baseUrl = environment.API_BASE_URL

  constructor(private http: HttpClient) { }

        createDriver(driver: Driver):Observable<Driver>{

            return this.http.post<Driver>("/drivers/addDriver", driver);

        }

       updateDriver(id: number, driver: Driver) {

         return this.http.put("/drivers/update/"+ id, driver);

        }
        getDriverList() {

          return this.http.get("/drivers");

        }

        getDriver(id: number): Observable<Driver> {

          return this.http.get<Driver>("/drivers/edit/" + id);

        }

        deleteDriver(id: number) {


          return this.http.delete("/drivers/delete/" + id);
        }
}
