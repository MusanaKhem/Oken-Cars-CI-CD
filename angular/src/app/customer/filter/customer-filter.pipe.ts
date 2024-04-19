import { Pipe, PipeTransform } from '@angular/core';
import { Customer } from '../models/customer';


@Pipe({
  name: 'FilterPipe'
})
export class CustomerFilterPipe implements PipeTransform {

  transform(list: Customer[], searchText: string): any {
    if(!list){
      return [];
    }
    if(!searchText){
      return list;
    }
    searchText = searchText.toLocaleLowerCase();
    list=list.filter(customer=> {
      return customer.firstname.toLocaleLowerCase().includes(searchText)||
             customer.lastname.toLocaleLowerCase().includes(searchText);
    });
    return list;
  }


}
