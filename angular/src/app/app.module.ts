import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DriverFilterPipe } from './driver/filter/driver-filter.pipe';
import { DriverListComponent } from './driver/driver-list/driver-list.component';
import { EditDriverComponent } from './driver/edit-driver/edit-driver.component';
import { RegisterDriverComponent } from './driver/register-driver/register-driver.component';
import { DetailDriverComponent } from './driver/detail-driver/detail-driver.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ManagerListComponent } from './manager/manager-list/manager-list.component';
import { DetailManagerComponent } from './manager/detail-manager/detail-manager.component';
import { EditManagerComponent } from './manager/edit-manager/edit-manager.component';
import { RegisterManagerComponent } from './manager/register-manager/register-manager.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { HeaderDriverComponent } from './driver/header-driver/header-driver.component';
import { HeaderManagerComponent } from './manager/header-manager/header-manger.component';
import { NgModule } from '@angular/core';
import { RegisterOwnerComponent } from './owner/register-owner/register-owner.component';
import { OwnerListComponent } from './owner/owner-list/owner-list.component';
import { HeaderOwnerComponent } from './owner/header-owner/header-owner.component';
import { OwnerFilterPipe } from './owner/filter/owner-filter.pipe';
import { DetailOwnerComponent } from './owner/detail-owner/detail-owner.component';
import { EditOwnerComponent } from './owner/edit-owner/edit-owner.component';
import { ManagerFilterPipe } from './manager/filter/manager-filter.pipe';
import { LoginManagerComponent } from './manager/login-manager/login-manager.component';
import { LoginDriverComponent } from './driver/login-driver/login-driver.component';
import { LoginOwnerComponent } from './owner/login-owner/login-owner.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { CustomerFilterPipe } from './customer/filter/customer-filter.pipe';
import { HeaderCustomerComponent } from './customer/header-customer/header-customer.component';
import { DetailCustomerComponent } from './customer/detail-customer/detail-customer.component';
import { RegisterCustomerComponent } from './customer/register-customer/register-customer.component';
import { EditCustomerComponent } from './customer/edit-customer/edit-customer.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginCustomerComponent } from './customer/login-customer/login-customer.component';
import { NewCarComponent } from './car/new-car/new-car.component';
import { CarListComponent } from './car/car-list/car-list.component';
import { DetailCarComponent } from './car/detail-car/detail-car.component';
import { EditCarComponent } from './car/edit-car/edit-car.component';
import { HeaderCarComponent } from './car/header-car/header-car.component';
import { DetailLocationComponent } from './location/detail-location/detail-location.component';
import { LocationListComponent } from './location/location-list/location-list.component';
import { RegisterLocationComponent } from './location/register-location/register-location.component';
import { EditLocationComponent } from './location/edit-location/edit-location.component';
import { HeaderLocationComponent } from './location/header-location/header-location.component';


@NgModule({
  declarations: [
    AppComponent,
    DriverListComponent,
    DriverFilterPipe,
    EditDriverComponent,
    RegisterDriverComponent,
    HomeComponent,
    DetailDriverComponent,
    NotFoundComponent,
    ManagerListComponent,
    DetailManagerComponent,
    EditManagerComponent,
    RegisterManagerComponent,
    HeaderDriverComponent,
    HeaderManagerComponent,
    RegisterOwnerComponent,
    OwnerListComponent,
    HeaderOwnerComponent,
    OwnerFilterPipe,
    DetailOwnerComponent,
    EditOwnerComponent,
    ManagerFilterPipe,
    LoginManagerComponent,
    LoginDriverComponent,
    LoginOwnerComponent,

    DetailCustomerComponent,
    CustomerListComponent,
    RegisterCustomerComponent,
    EditCustomerComponent,
    CustomerFilterPipe,
    HeaderCustomerComponent,
    LoginCustomerComponent,
    NewCarComponent,
    CarListComponent,
    DetailCarComponent,
    EditCarComponent,
    HeaderCarComponent,

    DetailLocationComponent,
    LocationListComponent,
    RegisterLocationComponent,
    EditLocationComponent,
    HeaderLocationComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,

  ],

  providers: [],

  bootstrap: [AppComponent]
})


export class AppModule { }
