import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from "../models/customer";
import { CustomerService } from "../service/customer.service";
import { Observable, EMPTY } from 'rxjs';


@Component({
    selector: 'app-detail-customer',
    templateUrl: './detail-customer.component.html',
    styleUrls: ['./detail-customer.component.css']
  })
export class DetailCustomerComponent implements OnInit {

  @Input() customer!: Customer;
  
    customer$!: Observable<Customer>;

  constructor
  ( private route: ActivatedRoute,
    private customerService: CustomerService
  ) { }


  ngOnInit(): void {
    
    let id=this.route.snapshot.params['id'];
   
    this.customer$=this.customerService.getCustomer(id);
  }


}

