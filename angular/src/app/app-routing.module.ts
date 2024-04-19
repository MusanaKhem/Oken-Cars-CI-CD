import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ManagerListComponent } from './manager/manager-list/manager-list.component';
import { EditManagerComponent } from './manager/edit-manager/edit-manager.component';
import { RegisterManagerComponent } from './manager/register-manager/register-manager.component';
import { DriverListComponent } from './driver/driver-list/driver-list.component';
import { RegisterDriverComponent } from './driver/register-driver/register-driver.component';
import { DetailDriverComponent } from './driver/detail-driver/detail-driver.component';
import { EditDriverComponent } from './driver/edit-driver/edit-driver.component';
import { DetailManagerComponent } from './manager/detail-manager/detail-manager.component';
import { OwnerListComponent } from './owner/owner-list/owner-list.component';
import { RegisterOwnerComponent } from './owner/register-owner/register-owner.component';
import { DetailOwnerComponent } from './owner/detail-owner/detail-owner.component';
import { EditOwnerComponent } from './owner/edit-owner/edit-owner.component';
import { LoginManagerComponent } from './manager/login-manager/login-manager.component';
import { LoginDriverComponent } from './driver/login-driver/login-driver.component';
import { LoginOwnerComponent } from './owner/login-owner/login-owner.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { RegisterCustomerComponent } from './customer/register-customer/register-customer.component';
import { DetailCustomerComponent } from './customer/detail-customer/detail-customer.component';
import { EditCustomerComponent } from './customer/edit-customer/edit-customer.component';
import { LoginCustomerComponent } from './customer/login-customer/login-customer.component';

import { NewCarComponent } from './car/new-car/new-car.component';
import { CarListComponent } from './car/car-list/car-list.component';
import { DetailCarComponent } from './car/detail-car/detail-car.component';
import { EditCarComponent } from './car/edit-car/edit-car.component';

import { DetailLocationComponent } from './location/detail-location/detail-location.component';
import { LocationListComponent } from './location/location-list/location-list.component';
import { RegisterLocationComponent } from './location/register-location/register-location.component';
import { EditLocationComponent } from './location/edit-location/edit-location.component';

const routes: Routes = [
    {path:'',component:HomeComponent },
    {path:'home',component:HomeComponent },

    {path: "driver/:id",component: DetailDriverComponent },
    {path: "driver-list", component: DriverListComponent },
    {path: "register-driver", component: RegisterDriverComponent },
    {path: "edit-driver/:id", component: EditDriverComponent },
    {path: "sign-in-driver", component: LoginDriverComponent },


    {path: 'manager-list', component: ManagerListComponent },
    {path: 'manager/:id', component: DetailManagerComponent },
    {path: 'edit-manager/:id', component: EditManagerComponent },
    {path: 'sign-up', component: RegisterManagerComponent },
    {path: 'sign-in-manager', component: LoginManagerComponent },

    {path: "owner/:id",component: DetailOwnerComponent },
    {path: "owner-list", component: OwnerListComponent },
    {path: "register-owner", component: RegisterOwnerComponent },
    {path: "edit-owner/:id", component: EditOwnerComponent },
    {path: "sign-in-owner", component: LoginOwnerComponent },

    {path: "customer/:id",component: DetailCustomerComponent },
    {path: "customer-list", component: CustomerListComponent },
    {path: "register-customer", component: RegisterCustomerComponent },
    {path: "edit-customer/:id", component: EditCustomerComponent },
    {path: "sign-in-customer", component: LoginCustomerComponent },


    {path: "new-car", component: NewCarComponent },
    {path: "car-list", component: CarListComponent },
    {path: "car/:id", component: DetailCarComponent },
    {path: "edit-car/:id", component: EditCarComponent },

    {path: "location/:id",component: DetailLocationComponent },
    {path: "location-list", component: LocationListComponent },
    {path: "register-location", component: RegisterLocationComponent },
    {path: "edit-location/:id", component: EditLocationComponent },



    /*
    {
      path:'**',component:NotFoundComponent
    },
  */

  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
