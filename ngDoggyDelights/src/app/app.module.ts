import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthService } from './services/auth.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GoogleMapsModule } from '@angular/google-maps';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbActiveModal, NgbModule, NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LogoutComponent } from './components/logout/logout.component';
import { StoreComponent } from './components/store/store.component';
import { PetComponent } from './components/pet/pet.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ProductComponent } from './components/product/product.component';
import { ProductReportComponent } from './components/product-report/product-report.component';
import { TestslideComponent } from './components/testslide/testslide.component';
import { NextDirective } from './next.directive';
import { PrevDirective } from './prev.directive';
import { EnabledPipe } from './pipes/enabled.pipe';

import { TopRatedListPipe } from './pipes/top-rated-list.pipe';

import { DatePipe } from '@angular/common';
import { MapComponent } from './components/map/map.component';
import { UserComponent } from './components/user/user.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';

import { ProdRepPipe } from './pipes/prod-rep.pipe';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    NavbarComponent,
    RegisterComponent,
    NotFoundComponent,
    StoreComponent,
    PetComponent,
    UserProfileComponent,
    ProductComponent,
    ProductReportComponent,
    TestslideComponent,
    NextDirective,
    PrevDirective,
    EnabledPipe,
    ProductComponent,
    MapComponent,
    TopRatedListPipe,
    UserComponent,
    ProdRepPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule,
    GoogleMapsModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule
  ],
  providers: [NgbActiveModal, AuthService, NgbAccordionConfig, EnabledPipe, DatePipe, ProdRepPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
