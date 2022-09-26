import { Product } from 'src/app/models/product';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { ProductReport } from '../models/product-report';

@Injectable({
  providedIn: 'root',
})
export class ProductReportService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/products';

  constructor(
    private http: HttpClient,
    // private datePipe: DatePipe,
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

  index(): Observable<ProductReport[]> {
    return this.http.get<ProductReport[]>(this.url + '/1/reports', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () =>
            new Error(
              'ProductReportService.index(): error retrieving productReports ' + err
            )
        );
      })
    );
  }

  getReports(pid: number): Observable<ProductReport[]> {
    return this.http.get<ProductReport[]>(this.url + '/1/reports', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () =>
            new Error(
              'ProductReportService.getReports(): error retrieving producReports ' + err
            )
        );
      })
    );
  }
}
