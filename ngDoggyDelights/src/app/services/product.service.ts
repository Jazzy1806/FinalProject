import { catchError, throwError, Observable } from 'rxjs';
// import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { AuthService } from './auth.service';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/products/';

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () =>
            new Error(
              'ProductService.index(): error retrieving products ' + err
            )
        );
      })
    );
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.url, product, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('ProductService.create(): error creating product: ' + err)
        );
      })
    );
  }

  update(product: Product) {
    // product.dateUpdated = this.datePipe.transform(Date.now(), 'shortDate');
    return this.http.put<Product>(this.url + product.id, product,
      this.getHttpOptions()).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError(
          () => new Error('ProductService.update(): error updating product: ' + err)
          );
      })
    );
  }
  findByKeyword(keyword: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.url + "keyword/" + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('ProductService.findByKeyword(): error finding product: ' + err)
        );
      })
    );
  }

  // destroy(pid: number) {
  //   return this.http.delete<void>(this.url + pid,
  //     this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       // console.log(err);
  //       return throwError(
  //         () => new Error('ProductService.destroy(): error deleting product: ' + err)
  //       );
  //     })
  //   );
  // }
}
