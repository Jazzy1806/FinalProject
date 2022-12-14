import { Product } from './../models/product';
import { Store } from './../models/store';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { StoreComment } from '../models/store-comment';
import { Inventory } from '../models/inventory';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  // private baseUrl = 'http://localhost:8090/';
  // private url = this.baseUrl + 'api/stores';
  private baseUrl = environment.baseUrl;
  private uri = 'api/stores';
  private url = this.baseUrl + this.uri;

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Store[]> {
    return this.http.get<Store[]>(this.url + '?sorted=true', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreService.index(): error retrieving store list: ' + err)
        );
      })

      );
    }

    // for home page
  indexHome(): Observable<Store[]> {
    return this.http.get<Store[]>(this.baseUrl + "home/stores" + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreService.indexHome(): error retrieving store list: ' + err)
        );
      })
      );
    }

  productsByStore(storeId: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.url + '/' + storeId + '/products', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'storeService.productsByStore(): error retrieving products by store: ' +
              err
            )
        );
      })
    );
  }

  commentsByStore(storeId: number): Observable<StoreComment[]> {
    return this.http.get<StoreComment[]>(this.url + '/' + storeId + '/comments', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'storeService.commentsByStore(): error retrieving comments by store: ' +
              err
            )
        );
      })
    );
  }

  createStoreComment(storeId: number, storeComment: StoreComment): Observable<StoreComment> {
    return this.http.post<StoreComment>(this.url + '/' + storeId + '/comments/comment', storeComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('storeService.createStoreComment(): error creating Store Comment: ' + err)
        );
      })
    );
  }

  destroyStoreComment(storeId: number, storeCommentId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + storeId + '/comments/' + storeCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('StoreService.destroyStoreComment(): error deleting Store Comment: ' + err)
        );
      })
    );
  }

  createStore(store: Store): Observable<Store> {
    return this.http.post<Store>(this.url, store, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('storeService.createStore(): error creating Store: ' + err)
        );
      })
    );
  }

  getInventoryByStore(store: Store, product: Product): Observable<Inventory> {
    return this.http.get<Inventory>(this.url + "/" + store.id + "/product/" + product.id + "/inventory", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('storeService.createStore(): error creating Store: ' + err)
        );
      })
    );
  }

  updateProdInventoryQuantity(store: Store, product: Product, inventory: Inventory): Observable<Inventory> {
    return this.http.put<Inventory>(this.url + "/" + store.id + "/product/" + product.id + "/inventory", inventory, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('storeService.createStore(): error creating Store: ' + err)
        );
      })
    );
  }

  searchStore(keyword: string): Observable<Store[]> {
    return this.http.get<Store[]>(this.url + '/search/' + keyword + '?sorted=true', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreService.searchStore(): error retrieving store list by keyword: ' + err)
        );
      })
    );
  }

  storesByProdKeyword(keyword: string): Observable<Store[]> {
    return this.http.get<Store[]>(this.url + "/search/products/" + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('ProductService.storesByProdKeyword(): error finding stores: ' + err)
        );
      })
    );
  }

  getStoreById(sid: number): Observable<Store> {
    return this.http.get<Store>(this.url + '/' + sid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreService.getStoreById(): error retrieving store by id: ' + err)
        );
      })
    );
  }

  addProductForStore(store : Store, inventory:Inventory): Observable<Inventory>{
    delete inventory.store?.owner;
   // delete inventory.product?.reports;
    delete inventory.store?.inventories;
    delete inventory.store?.productReports;
    return this.http.post<Inventory>(this.url + "/" + store.id + "/inventory",inventory, this.getHttpOptions() ).pipe(
      catchError((err: any) => {
        console.log(
          "url: "+ this.url + "/" + store.id + "/inventory"
        );

        return throwError(
          () => new Error('StoreService.addProductForStore(): error add product to store: ' + err)
        );
      })
    )
  }

}
