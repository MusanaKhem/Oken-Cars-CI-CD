import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/customer';
import { CustomerService } from '../service/customer.service';


@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {
       customers: Customer[] | any;
       searchText: string = '';

        constructor(private customerService: CustomerService){}

        ngOnInit(){
              this.customerService.getCustomerList().subscribe((response:any)=>{
              this.customers=response;
               console.log(this.customers);
               
             });
        }

        reloadPage() {
          window.location.reload();
        }
        
        deleteCustomer(id: number){
          this.customerService.deleteCustomer(id).subscribe((response:any)=>{
            console.log(response);
            this.customers = this.customers.filter((customer:any)=>{
              return id! = customer.id;
            })
          })

         this.reloadPage();
       
        }
}
