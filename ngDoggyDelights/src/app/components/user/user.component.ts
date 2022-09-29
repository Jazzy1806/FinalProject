import { Component, OnInit } from '@angular/core';
import { Observable, of, delay } from 'rxjs';
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
  takenUsernames : string[] = [];


  constructor(private authService : AuthService) { }
  roles = ['standard', 'admin'];
  ngOnInit(): void {
  this.usersLoad();

  }

  usersLoad(){
    this.authService.getAllUsers().subscribe({
      next: (result) => {
        this.users = result;
        for (let user of this.users) {
          this.takenUsernames.push(user.username);
        }
      },
      error: (problem) => {
        console.error(
          'userHttpComponent.usersLoad(): error loading user list'
        );
        console.error(problem);
      },
    });
  }
  checkIfUsernameExists(username: string): Observable<boolean> {
    return of(this.takenUsernames.includes(username)).pipe(delay(1000));
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
        console.log("role" + this.selectedUser.role);

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
