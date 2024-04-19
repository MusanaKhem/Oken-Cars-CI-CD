import { Component, OnInit } from '@angular/core';
import { Owner } from '../models/owner';
import { OwnerService } from '../service/owner.service';


@Component({
  selector: 'app-owner-list',
  templateUrl: './owner-list.component.html',
  styleUrls: ['./owner-list.component.scss']
})
export class OwnerListComponent implements OnInit {
       owners: Owner[] | any;
       searchText: string = '';

        constructor(private ownerService: OwnerService){}

        ngOnInit(){
              this.ownerService.getOwnerList().subscribe((response:any)=>{
              this.owners=response;
               console.log(this.owners);
               
             });
        }

        reloadPage() {
          window.location.reload();
        }
        
        deleteOwner(id: number){
          this.ownerService.deleteOwner(id).subscribe((response:any)=>{
            console.log(response);
            this.owners = this.owners.filter((owner:any)=>{
              return id! = owner.id;
            })
          })

         this.reloadPage();
       
        }
}
