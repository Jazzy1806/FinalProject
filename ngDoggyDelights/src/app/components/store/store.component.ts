import { Address } from './../../models/address';
import { StoreComment } from './../../models/store-comment';
import { StoreService } from './../../services/store.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Store } from 'src/app/models/store';
import { Product } from 'src/app/models/product';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
  selected : Store | null = null;
  // @Output() stores = new EventEmitter<Store []>() ;
  stores : Store [] | null = null;
  activeStores: Store [] | null = null;
  selectedStore = {} as Store;
  storeName: string | null = '';
  products: Product [] | null = null;
  newComment = {} as StoreComment;
  storeComments: StoreComment [] | null = null;
  loggedInUser : any;
  selectedProductPrice = 0;
  isCollapsed = true;
  newStore = {} as Store;
  newAddress = {} as Address;
  quantity = 0;

  constructor(private storeService : StoreService , private authService: AuthService) {}

  images: string[] = [];


  ngOnInit(): void {
    this.reload();
  this.collectLoggedInUser();

  }
  collectLoggedInUser() {
    this.authService.getLoggedInUser().subscribe(
      {
        next: (user) => {
          this.loggedInUser = user;
          this.newComment.user = this.loggedInUser;
          console.log("user logged in " + user.username);

        },
        error: (problem) => {
          console.error('StoreListHttpComponent.collectLoggedInUser(): error loading user logged in');
          console.error(problem);
        }
      }
    )
  }
  reload() {
    this.storeService.index().subscribe(
      {
        next: (stores) => {
          console.log("inside reload method");
          console.log("storecomments is null" + this.storeComments == null);


          this.stores = stores;
          for (let store of stores) {
            console.log("store" + store.name);
            this.productsByStore(store);
            console.log("products " + this.products);
            console.log("enable status in reload" + store.enabled)
            if (store.enabled) {
              this.activeStores?.push(store);
              console.log("size of active stores: " + this.activeStores?.length);

            }
          }
        },
        error: (problem) => {
          console.error('StoreListHttpComponent.reload(): error loading store list');
          console.error(problem);
        }
      }
    );
  }
  deactivateStore(store: Store) {
    store.enabled = false;
  }

  productsByStore(store : Store){
    this.storeService.productsByStore(store.id).subscribe({
      next: (products) => {
        store.products = products;
        this.products = products;
        for(let product of this.products) {
        console.log("inventory length " + product.inventoryItems?.length);
        console.log("inventory " + product.name);
      }

        console.log(store.products);
      },
      error: (problem) => {
        console.error('ProductListHttpComponent.loadProductsByStore(): error loading stock list');
        console.error(problem);
      }
  })
  }

  commentsByStore(store: Store) {
    this.storeService.commentsByStore(store.id).subscribe({
      next: (comments) => {
        console.log(comments);
         this.storeComments = comments;
        this.selectedStore = store;
        console.log("selected Store " + this.selectedStore.name);

         this.storeName = store.name;
         for(let comment of comments){
         }


      },
      error: (problem) => {
        console.error('ProductListHttpComponent.loadProductsByStore(): error loading stock list');
        console.error(problem);
      }
  })
  }

  createStoreComment(store: Store, storeComment: StoreComment): void {
    this.storeService.createStoreComment(store.id, storeComment).subscribe({
      next: (result) => {
        this.newComment = {} as StoreComment;
        this.commentsByStore(store);
        console.log("inside createStoreComment component ts");

      },
      error: (nojoy) => {
        console.error('StoreHttpComponent.createStoreComment(): error creating Store comment:');
        console.error(nojoy);
      },
    });
  }

  deleteStoreComment(store: Store, storeCommentId: number){
    this.storeService.destroyStoreComment(store.id, storeCommentId).subscribe({
      next: () => {
        console.log("comment being deleted " + storeCommentId);

        this.commentsByStore(store);
      },
      error: (nojoy) => {
        console.error('StoreHttpComponent.deleteStoreComment(): error deleting store comment:');
        console.error(nojoy);
      },
    });
  }
  createStore(store: Store): void {
    store.address = this.newAddress;
    store.enabled = true;
    this.storeService.createStore(store).subscribe({
      next: (result) => {
        console.log("inside createStorecomponent ts");

      },
      error: (nojoy) => {
        console.error('StoreHttpComponent.createStore(): error creating store:');
        console.error(nojoy);
      },
    });
    this.isCollapsed = true;
    this.reload();
  }

  updateProdInventoryByStore(store: Store, prod: Product, form : NgForm): void {
    this.storeService.updateProdInventoryQuantity(store, prod, form.value.quantity).subscribe({
      next: (result) => {
        this.productsByStore(store);
        this.commentsByStore(store);
        console.log("inside updateProdInventoryByStore component ts");

      },
      error: (nojoy) => {
        console.error('StoreHttpComponent.updateProdInventoryByStoret(): error updating inventory:');
        console.error(nojoy);
      },
    });
  }
}
