import { Component ,OnInit, Input} from '@angular/core';
import { Manager } from '../models/manager';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ManagerService } from '../service/manager.service';



@Component({
  selector: 'app-detail-manager',
  templateUrl: './detail-manager.component.html',
  styleUrls: ['./detail-manager.component.scss']
})
export class DetailManagerComponent {

    @Input() manager!: Manager;
           manager$!: Observable<Manager>;

           constructor(private managerService: ManagerService,
                           private route: ActivatedRoute,
                           private router: Router
                           ) { }

             ngOnInit(): void {
                          const iD = +this.route.snapshot.params['id'];//Récupération des informations du manager
                          this.manager$ = this.managerService.getManagerById(iD);
                   }

}
