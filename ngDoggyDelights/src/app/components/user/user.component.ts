import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users : User [] | null = null;
  editUser : User | null = null;
  selectedUser : User | null = null;
  newAddress: Address | null = null;
  constructor(private authService : AuthService) { }

  ngOnInit(): void {
  this.usersLoad();

  }

  usersLoad(){
    this.authService.getAllUsers().subscribe({
      next: (result) => {
        this.users = result;
      },
      error: (problem) => {
        console.error(
          'userHttpComponent.usersLoad(): error loading user list'
        );
        console.error(problem);
      },
    });
  }

  toggleUser(user : User) {
    if (user.enabled) user.enabled = false;
    else {user.enabled = true;}
  }

  userSelectedToAction(user: User){
    this.selectedUser = user;
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.selectedUser);
    this.newAddress = this.editUser.address;
  }

  updateUser(user : User) {
    this.authService.updateUser(user).subscribe({
      next: (result) => {
        this.editUser = null;
        this.selectedUser = result;
        this.usersLoad();
      },
      error: (problem) => {
        console.error(
          'userHttpComponent.updateCredentials(): error updating user'
        );
        console.error(problem);
      },
    })
  }

}
