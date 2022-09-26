import { Product } from './../models/product';
import { Store } from './../models/store';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { StoreComment } from '../models/store-comment';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/stores';

  constructor(private http: HttpClient,  private auth: AuthService) { }

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

    productsByStore(storeId :number): Observable<Product[]>{
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

    commentsByStore(storeId: number): Observable<StoreComment[]>{
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

    createStoreComment(storeId: number, storeComment : StoreComment): Observable<StoreComment> {
      return this.http.post<StoreComment>(this.url + '/' + storeId + '/comments/comment',storeComment, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'storeService.createStoreComment(): error creating Store Comment: ' + err )
          );
        })
      );
    }

    destroyStoreComment(storeId: number, storeCommentId: number): Observable<void> {
      return this.http.delete<void>(this.url +'/'+ storeId + '/comments/' + storeCommentId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'StoreService.destroyStoreComment(): error deleting Store Comment: ' + err )
          );
        })
      );
    }

    createStore(store : Store): Observable<Store> {
      return this.http.post<Store>(this.url ,store, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'storeService.createStore(): error creating Store: ' + err )
          );
        })
      );
    }

     updateProdInventoryQuantity(store: Store, product : Product, quantity: number): Observable<Inventory[]> {
      return this.http.post<Inventory[]>(this.url + "/" + store.id + "/product/" + product.id +"/inventory/" + quantity, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
             () => new Error( 'storeService.createStore(): error creating Store: ' + err )
          );
        })
      );
     }

}
