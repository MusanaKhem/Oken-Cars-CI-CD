import { Injectable } from '@angular/core';
import { Manager } from '../models/manager';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environments';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
   baseUrl = environment.API_BASE_URL;

  constructor(private http: HttpClient) { }

   //Récupération de la liste de tous les managers
          getAllManagers(): Observable<Manager[]>{
              return this.http.get<Manager[]>('/managers');
          }

          //Accéder à un manager en fonction de son ID
          getManagerById(id: number): Observable<Manager>  {
               return this.http.get<Manager>(`/managers/` +id);
          }

          //Supprimer un manager en fonction de ID
          deleteManagerById(id: number): Observable<Manager> {
              return this.http.delete<Manager>(`/managers/delete/` +id);
          }

          // Mettre à jour les informations d'un manager
          updateManager(id: number, manager: Manager): Observable<Manager> {
              return this.http.put<Manager>(`/managers/update/` +id, manager);
          }

          // Mettre à jour les informations d'un manager
          newManager(manager: Manager): Observable<Manager> {
               return this.http.post<Manager>(`/managers/addManager`, manager);
          }

          loginManager( email:string ,password: string){

                  let ConnectionManagerAttempt = {
                       email:email,
                       password: password
                  }
                  console.log(ConnectionManagerAttempt);
                  return this.http.post<any>(`/managers/authenticate`,ConnectionManagerAttempt);
          }


}
