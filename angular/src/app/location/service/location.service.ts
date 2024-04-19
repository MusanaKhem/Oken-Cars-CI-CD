import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environments';
import { Location } from '../models/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  baseUrl = environment.API_BASE_URL

  constructor(private http: HttpClient) { }

        createLocation(location: Location):Observable<Location>{

        return this.http.post<Location>("/locations/addLocation", location)

        }

       updateLocation(id: number, location: Location) {

         return this.http.put("/locations/update/"+ id, Location);

        }
        getLocationList() {

          return this.http.get("/locations");

        }

        getLocation(id: number): Observable<Location> {

          return this.http.get<Location>("/locations/edit/" + id);

        }

        deleteLocation(id: number) {


          return this.http.delete("/locations/delete/" + id);
        }
}
