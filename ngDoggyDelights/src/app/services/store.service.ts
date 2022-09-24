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

}
