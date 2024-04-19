import { Component, OnInit } from '@angular/core';
import { Location } from '../models/location';
import { LocationService } from '../service/location.service';


@Component({
  selector: 'app-location-list',
  templateUrl: './location-list.component.html',
  styleUrls: ['./location-list.component.scss']
})
export class LocationListComponent implements OnInit {
       locations: Location[] | any;
       searchText: string = '';

        constructor(private locationService: LocationService){}

        ngOnInit(){
              this.locationService.getLocationList().subscribe((response:any)=>{
              this.locations=response;
               console.log(this.locations);
               
             });
        }

        reloadPage() {
          window.location.reload();
        }
        
        deleteLocation(id: number){
          this.locationService.deleteLocation(id).subscribe((response:any)=>{
            console.log(response);
            this.locations = this.locations.filter((location:any)=>{
              return id! = location.id;
            })
          })

         this.reloadPage();
       
        }
}
