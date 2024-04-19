import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Owner } from "../models/owner";
import { OwnerService } from "../service/owner.service";
import { Observable, EMPTY } from 'rxjs';


@Component({
    selector: 'app-detail-owner',
    templateUrl: './detail-owner.component.html',
    styleUrls: ['./detail-owner.component.css']
  })
export class DetailOwnerComponent implements OnInit {

  @Input() owner!: Owner;
  
    owner$!: Observable<Owner>;

  constructor
  ( private route: ActivatedRoute,
    private ownerService: OwnerService
  ) { }


  ngOnInit(): void {
    
    let id=this.route.snapshot.params['id'];
   
    this.owner$=this.ownerService.getOwner(id);
  }


}

