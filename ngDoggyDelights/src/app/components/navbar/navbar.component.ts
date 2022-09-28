import { Component, NgModule, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

// const MaterialComponents = [
//   MatIconModule
// ];

// // @NgModule({
// //   imports : [MaterialComponents],
// //   exports: [MaterialComponents]
// // })


export class NavbarComponent implements OnInit {
  loggedInUser : User | null = null;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    if (this.loggedInUser) {
    this.collectLoggedInUser();
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  collectLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        console.log('user logged in ' + user.username);
      },
      error: (problem) => {
        console.error(
          'NavbarHttpComponent.collectLoggedInUser(): error loading user logged in'
        );
        console.error(problem);
      },
    });
  }

}
