import { PetService } from 'src/app/services/pet.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Pet } from 'src/app/models/pet';
import { Address } from 'src/app/models/address';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  credentials: boolean = false;
  loggedInUser: User | null = null;
  editUser: User | null = null;
  newAddress: Address | null = {} as Address;
  pets: Pet[] = [];
  name = new FormControl('');
  constructor(private auth: AuthService,private route: ActivatedRoute, private router: Router, private petService: PetService) {

  }

  ngOnInit(): void {
    this.auth.getLoggedInUser().subscribe({
      next: (userData) => {
          this.loggedInUser = userData;
      },
      error: (err) => {
        console.error("Error retrieving user:");
        console.error(err);
        //if todo id doesn't exist, will direct user to notFound page
        this.router.navigateByUrl('nosuchuser');
      }
    });
    console.log(this.loggedInUser);
    // localStorage.setItem('userLoggedIn, )

    this.petService.index().subscribe(
      {
        next: (results) => {
          this.pets = results;
          console.log(this.pets);
        },
        error: (problem) => {
          console.error('PetsHttpComponent.getPets(): error loading pets:');
          console.error(problem);
        }
      }
    );
    }

    setNewAddress() {
      this.editUser = this.loggedInUser;
      if (this.editUser !== null && this.editUser !== undefined) {
        if (this.editUser.address !== undefined || this.editUser.address !== null) {
        this.newAddress = this.editUser.address;
      }
    }
    }

    startChat() {
        this.router.navigateByUrl('app/chat');
    }

    updateCredentials() {
      if (this.editUser !== null) {
        this.auth.updateCredentials(this.editUser).subscribe({
          next: (result) => {
            this.editUser = null;
            this.credentials=false;
            this.auth.logout();
            this.router.navigateByUrl('home');
          },
          error: (nojoy) => {
            console.error('UserHttpComponent.updateUser(): error updating credentials:' + nojoy);
          },
        });
      }
    }

    updateUser() {
      console.log(this.newAddress);
      if (this.editUser !== null) {
        this.editUser.address = this.newAddress;
      }
      if (this.editUser !== null) {
        console.log(this.editUser);
        this.auth.updateUser(this.editUser).subscribe({
          next: (result) => {
            this.editUser = null;
            this.newAddress = {} as Address;
          },
          error: (nojoy) => {
            console.error('UserHttpComponent.updateUser(): error updating user:' + nojoy);
          },
        });
      }
    }
}
