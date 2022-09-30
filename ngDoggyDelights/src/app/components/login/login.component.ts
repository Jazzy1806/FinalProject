import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
declare var window:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formModal:any;
  loginUser: User = {} as User;
  isErrorInLogged = false;

  constructor(public activeModal: NgbActiveModal, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.formModal = new window.bootstrap.Modal(

      document.getElementById("exampleModal")
      );
  }

  openModal(){
    this.formModal.show();
  }
  closeModal(){
    this.formModal.hide();
  }
  // login(){
  //   this.formModal.hide();
  // }
  login(loginUser: User) {
    console.log('Logging in user:');
    console.log(loginUser);
      this.authService.login(loginUser.username, loginUser.password).subscribe({
        next: (loggedInUser) => {
          this.formModal.hide();
          this.router.navigateByUrl('profile');
        },
        error: (problem) => {
          console.error('RegisterComponent.register(): Error logging in user:');
          console.error(problem);
          this.isErrorInLogged = true;
        }
      });
      error: (fail: any) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
  }
}
