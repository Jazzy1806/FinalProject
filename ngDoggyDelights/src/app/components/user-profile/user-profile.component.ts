import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pet } from 'src/app/models/pet';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  loggedInUser: User | null = null;
  editUser: User | null = null;

  pets: Pet[] = [];

  constructor(private auth: AuthService,private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    // let idStr = this.route.snapshot.paramMap.get('id');
    // if (idStr) {
    //   let userId = Number.parseInt(idStr);
    //   if (!isNaN(userId)) {
        this.auth.getLoggedInUser().subscribe({
          next: (userData) => {
            this.loggedInUser = userData;
          },
          error: (err) => {
            console.error("Error retrieving todo:");
            console.error(err);
            //if todo id doesn't exist, will direct user to notFound page
            this.router.navigateByUrl('noSuchTodo');
          }
        })
      // }
      // else {
      //   console.error("No such user: " + idStr);
      // }
    }
  // }

}
