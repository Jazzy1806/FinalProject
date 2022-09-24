import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Address } from 'src/app/models/address';
import { Pet } from 'src/app/models/pet';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = {} as User;
  newAddress: Address = {} as Address;
  newPet: Pet = {} as Pet;

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register(newUser: User): void {
    newUser.address = this.newAddress;
    console.log(newUser);
    this.auth.register(newUser).subscribe({
      next: (registeredUser) => {
        this.auth.login(newUser.username, newUser.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('home');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }

}
