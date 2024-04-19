import { Pipe, PipeTransform } from '@angular/core';
import { Manager } from '../models/manager';
@Pipe({
  name: 'FilterPipe'
})
export class ManagerFilterPipe implements PipeTransform {

  transform(list: Manager[], searchText: string): any {
    if(!list){
      return [];
    }
    if(!searchText){
      return list;
    }
    searchText = searchText.toLocaleLowerCase();
    list=list.filter(manager=> {
      //Search by name and firstname
      return manager.firstname.toLocaleLowerCase().includes(searchText)||
             manager.lastname.toLocaleLowerCase().includes(searchText);
    });
    return list;
  }


}
