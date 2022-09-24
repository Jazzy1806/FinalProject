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
  constructor(private storeService : StoreService ) {}

  ngOnInit(): void {
    this.reload();
  }
  reload() {
    this.storeService.index().subscribe(
      {
        next: (stores) => {
          this.stores = stores;
          console.log(stores);

        },
        error: (problem) => {
          console.error('StoreListHttpComponent.reload(): error loading store list');
          console.error(problem);
        }
      }
    );
  }

 // productsByStore()
}
