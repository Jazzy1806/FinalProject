import { StoreComment } from './../../models/store-comment';
import { StoreService } from './../../services/store.service';
import { Component, OnInit } from '@angular/core';
import { Store } from 'src/app/models/store';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
  selected : Store | null = null;
  stores: Store [] | null = null;
  products: Product [] | null = null;
  storeComments: StoreComment [] | null = null;
  constructor(private storeService : StoreService ) {}

  ngOnInit(): void {
    this.reload();

  }
  reload() {
    this.storeService.index().subscribe(
      {
        next: (stores) => {
          this.stores = stores;
          for (let store of stores) {
            console.log("store" + store.name);
            this.productsByStore(store);
            console.log("products " + this.products);

          }
        },
        error: (problem) => {
          console.error('StoreListHttpComponent.reload(): error loading store list');
          console.error(problem);
        }
      }
    );
  }

  productsByStore(store : Store){
    this.storeService.productsByStore(store.id).subscribe({
      next: (products) => {
        store.products = products

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
       this.storeComments = comments
        console.log(this.storeComments);
      },
      error: (problem) => {
        console.error('ProductListHttpComponent.loadProductsByStore(): error loading stock list');
        console.error(problem);
      }
  })
  }
}
