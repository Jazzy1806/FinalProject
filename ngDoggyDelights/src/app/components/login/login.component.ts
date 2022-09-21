import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
declare var window:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formModal:any;
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.formModal = new window.bootstrap.Modal(

      document.getElementById("exampleModal")
      );
  }

  openModal(){
    this.formModal.show();
  }
  login(){
    this.formModal.hide();
  }
}
