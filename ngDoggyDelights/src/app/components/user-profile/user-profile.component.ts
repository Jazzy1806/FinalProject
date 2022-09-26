import { PetService } from 'src/app/services/pet.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Pet } from 'src/app/models/pet';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  loggedInUser: User | null = null;
  editUser: User | null = null;
  pets: Pet[] = [];

  constructor(private auth: AuthService,private route: ActivatedRoute, private router: Router, private petService: PetService) {

  }

  ngOnInit(): void {
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
    });

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


}
