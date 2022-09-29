import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductComment } from '../models/product-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductCommentService {
  private baseUrl = environment.baseUrl;
  private uri = 'api/products';
  private url = this.baseUrl + this.uri;

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  indexByProduct(pid: number): Observable<ProductComment[]> {
    return this.http.get<ProductComment[]>(this.url + '/' + pid + '/comments', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () =>
            new Error(
              'ProductCommentService.indexByProduct(): error retrieving productComments ' + err
            )
        );
      })
    );
  }

  create(pid: number, sid: number, comment: ProductComment): Observable<ProductComment> {
    comment.dateCreated = new Date(this.datePipe.transform(Date.now(), 'ShortDate')!);
    return this.http.post<ProductComment>(this.url + '/' + pid + '/stores/' + sid + '/comments', comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('ProductCommentService.create(): error creating comment: ' + err)
        );
      })
    );
  }
}
