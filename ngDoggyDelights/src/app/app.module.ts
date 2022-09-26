import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthService } from './services/auth.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';


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
    ProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [NgbActiveModal, AuthService, NgbAccordionConfig, EnabledPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
