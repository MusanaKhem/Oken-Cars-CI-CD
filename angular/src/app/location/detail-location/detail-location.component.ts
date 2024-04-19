import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from "../models/location";
import { LocationService } from "../service/location.service";
import { Observable, EMPTY } from 'rxjs';


@Component({
    selector: 'app-detail-location',
    templateUrl: './detail-location.component.html',
    styleUrls: ['./detail-location.component.css']
  })
export class DetailLocationComponent implements OnInit {

  @Input() location!: Location;
  
    location$!: Observable<Location>;

  constructor
  ( private route: ActivatedRoute,
    private locationService: LocationService
  ) { }


  ngOnInit(): void {
    
    let id=this.route.snapshot.params['id'];
   
    this.location$=this.locationService.getLocation(id);
  }


}

