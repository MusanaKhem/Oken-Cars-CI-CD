import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocationService } from '../service/location.service';
import { Location } from '../models/location';


@Component({
  selector: 'app-edit-location',
  templateUrl: './edit-location.component.html',
  styleUrls: ['./edit-location.component.css']
})

export class EditLocationComponent implements OnInit {
  constructor(
     private route: ActivatedRoute,
     private router: Router,
     private locationService: LocationService){}
  id: number | any;
  location =  new Location();

  ngOnInit(){

    this.id = this.route.snapshot.paramMap.get('id');
 
    this.locationService.getLocation(this.id).subscribe((response:any)=>{

     this.location = response;
    // console.log(response);
    })

  }

  goEditLocation(){

    return this.router.navigateByUrl("/location/"+ this.id); 

  }

  editLocation(){

    this.locationService.updateLocation(this.id, this.location).subscribe(()=>{

    this.location = new Location();

    this.goEditLocation();

    })
   
    }


}

