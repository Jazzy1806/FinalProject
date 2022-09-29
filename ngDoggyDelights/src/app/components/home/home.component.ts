import { ProductService } from './../../services/product.service';
import { LoginComponent } from './../login/login.component';
import { Component, Input, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { StoreService } from 'src/app/services/store.service';
import { Store } from 'src/app/models/store';
import { Product } from 'src/app/models/product';
declare var window:any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  closeResult: string = '';
  formModal:any;
  stores : Store [] | null = null;
  products: Product[] = [];

  constructor(private modalService: NgbModal, private storeService : StoreService, private prodService: ProductService ) { }

  ngOnInit(): void {
    this.storeLoad();
    this.productLoad();
  }

  openModal() {
    const modalRef = this.modalService.open(LoginComponent);
  }

  /**
   * Write code on Method
   *
   * @return response()
   */
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  storeLoad() {
    this.storeService.indexHome().subscribe(
      {
        next: (stores) => {
          this.stores = stores;
        },
        error: (problem) => {
          console.error('StoreListHttpComponent.reload(): error loading store list');
          console.error(problem);
        }
      }
    );
  }

  productLoad(){
    this.prodService.indexHome().subscribe({
      next: (data) => {
        this.products = data;
        console.log("list of products "+ this.products);

      },
      error: (err) => {
        console.error(
          'ProductComponent.reload(): error loading products: ' + err
        );
      },
    });
  }

}
