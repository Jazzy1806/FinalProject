import { ProductComponent } from './components/product/product.component';
import { PetComponent } from './components/pet/pet.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { StoreComponent } from './components/store/store.component';
import { ProductReportComponent } from './components/product-report/product-report.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'store', component: StoreComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'pet', component: PetComponent },
  { path: 'product', component: ProductComponent},
  { path: 'reports', component: ProductReportComponent},

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
