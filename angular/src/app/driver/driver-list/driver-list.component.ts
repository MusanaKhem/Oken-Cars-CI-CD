import { Component, OnInit } from '@angular/core';
import { Driver } from '../models/driver';
import { DriverService } from '../service/driver.service';


@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styleUrls: ['./driver-list.component.scss']
})
export class DriverListComponent implements OnInit {
       drivers: Driver[] | any;
       searchText: string = '';

        constructor(private driverService: DriverService){}

        ngOnInit(){
              this.driverService.getDriverList().subscribe((response:any)=>{
              this.drivers=response;        
               
             });
        }

        reloadPage() {
          window.location.reload();
        }
        
        deleteDriver(id: number){
          this.driverService.deleteDriver(id).subscribe((response:any)=>{
            this.drivers = this.drivers.filter((driver:any)=>{
              return id! = driver.id;
            })
          })

         this.reloadPage();
       
        }
}
