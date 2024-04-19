import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, EMPTY } from 'rxjs';
import { Driver } from '../models/driver';
import { DriverService } from '../service/driver.service';

@Component({
  selector: 'app-detail-driver',
  templateUrl: './detail-driver.component.html',
  styleUrls: ['./detail-driver.component.css']
})
export class DetailDriverComponent implements OnInit {

  @Input() driver!: Driver;
  
    driver$!: Observable<Driver>;

  constructor(private route: ActivatedRoute, private driverService:DriverService) { }


  ngOnInit(): void {
    
    let id=this.route.snapshot.params['id'];
   
    this.driver$=this.driverService.getDriver(id);
  }


}
