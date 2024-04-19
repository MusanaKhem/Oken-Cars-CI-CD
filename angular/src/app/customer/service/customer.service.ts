import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environments';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl = environment.API_BASE_URL

  constructor(private http: HttpClient) { }

        createCustomer(customer: Customer):Observable<Customer>{

        return this.http.post<Customer>("/customers/addCustomer", customer)

        }

       updateCustomer(id: number, customer: Customer) {

         return this.http.put("/customers/update/"+ id, customer);

        }
        getCustomerList() {

          return this.http.get("/customers");

        }

        getCustomer(id: number): Observable<Customer> {

          return this.http.get<Customer>("/customers/edit/" + id);

        }

        deleteCustomer(id: number) {


          return this.http.delete("/customers/delete/" + id);
        }
}
