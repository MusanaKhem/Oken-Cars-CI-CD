import { Pipe, PipeTransform } from '@angular/core';
import { Owner } from '../models/owner';


@Pipe({
  name: 'FilterPipe'
})
export class OwnerFilterPipe implements PipeTransform {

  transform(list: Owner[], searchText: string): any {
    if(!list){
      return [];
    }
    if(!searchText){
      return list;
    }
    searchText = searchText.toLocaleLowerCase();
    list=list.filter(owner=> {
      return owner.firstname.toLocaleLowerCase().includes(searchText)||
             owner.lastname.toLocaleLowerCase().includes(searchText);
    });
    return list;
  }


}
