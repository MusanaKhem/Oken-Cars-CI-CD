import { Pipe, PipeTransform } from '@angular/core';
import { Driver } from '../models/driver';


@Pipe({
  name: 'FilterPipe'
})
export class DriverFilterPipe implements PipeTransform {

  transform(list: Driver[], searchText: string): any {
    if(!list){
      return [];
    }
    if(!searchText){
      return list;
    }
    searchText = searchText.toLocaleLowerCase();
    list=list.filter(driver=> {
      return driver.firstname.toLocaleLowerCase().includes(searchText)||
             driver.lastname.toLocaleLowerCase().includes(searchText);
    });
    return list;
  }


}
