import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Address } from 'src/app/models/address';
import { Pet } from 'src/app/models/pet';
import { Form, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { passValidator, zipcodeValidator } from './validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  users : User [] = [] ;
  newUser: User = {} as User;
  newAddress: Address = {} as Address;
  newPet: Pet = {} as Pet;
  name = new FormControl('');
  usernameTaken = false;
  isUsernameUnique = true;


  // this.registerForm = new FormGroup({
  //   username: new FormControl(this.)
  // })
  form : FormGroup | any;
  constructor(private auth: AuthService, private router: Router, private fb : FormBuilder) {
    this.form = this.fb.group (
      {
        // username: ["", uniqueUsernameUpdateValidator],
        firstName:'',
        lastName:'',
        username: '',
        password: '',
        address:'',
        city: '',
        state: '',
        postalCode: '',
        email: ['',[Validators.pattern("[^ @]*@[^ @]*")]],
      cnfpass: ['', passValidator],
      zip: ['', zipcodeValidator]
      }
    );
    this.form.controls.password.valueChanges
    .subscribe(() => this.form.controls.cnfpass.updateValueAndValidity()
    )


   }

  ngOnInit(): void {


  }
  onSubmit(){
    // console.log(this.form.controls.zip);
    this.form.markAsTouched();
  }

  register(newUser: User): void {
    newUser.address = this.newAddress;
    console.log(newUser);
    this.auth.register(newUser).subscribe({
      next: (registeredUser) => {
        this.auth.login(newUser.username, newUser.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('profile');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        if (fail === '409') {
          this.isUsernameUnique = false;
        }
        console.error('RegisterComponent.register(): Error registering account');
        console.error("fail " + fail);
        console.error(fail === '409');
      }
    });
  }

  checkUsernameNotTaken(username : string) {
    return this.auth.getAllUsers().subscribe ( {
      next: (results) => {
        this.users = results;
        for (let user of this.users) {
          if (user.username === username) {
              this.usernameTaken = false;
          };
        }
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });

   }

   validateUsernameNotTaken(){
    return this.usernameTaken;
   }



}
