import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '../models/location';
import { LocationService } from '../service/location.service';


@Component({
  selector: 'app-register-location',
  templateUrl: './register-location.component.html',
  styleUrls: ['./register-location.component.css']
})
export class RegisterLocationComponent implements OnInit {

    //location = new Location();
    location: Location = new Location();
    confirmPassword: string = ''; // Track password confirmation
    passwordInvalid = false; // Property to track password validity
    passwordsMatch = true; // Property to track password matching

  constructor(private router: Router, private locationService: LocationService){}

  ngOnInit(){

  }

      saveLocation(){
              if (this.location && !this.passwordInvalid && this.passwordsMatch) {
                   this.locationService.createLocation(this.location).subscribe((createdLocation: Location) => {
                        this.router.navigateByUrl(`/location/${createdLocation.id}`);
                   });
              }
      }


}
