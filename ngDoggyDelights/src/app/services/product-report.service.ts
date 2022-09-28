import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { ProductReport } from '../models/product-report';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class ProductReportService {
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/products';

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

  create(report: ProductReport): Observable<ProductReport> {
    // report.createdOn = this.datePipe.transform(Date.now(), 'shortDate')!;
    console.log(report);

    return this.http.post<ProductReport>(this.url + '/1/stores/1/reports', report, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          // console.log(err);
          return throwError(
            () => new Error('ProductReportService.create(): error creating report: ' + err)
          );
        })
      );
  }

  update(report: ProductReport) {
    report.updatedOn = this.datePipe.transform(Date.now(), 'shortDate')!;
    return this.http.put<ProductReport>(this.baseUrl + report.id, report,
      this.getHttpOptions()).pipe(
        catchError((err: any) => {
          // console.log(err);
          return throwError(
            () => new Error('ProductReportService.update(): error updating report: ' + err)
          );
        })
      );
  }

  getReport(pid: number, ): Observable<ProductReport> {
    return this.http.get<ProductReport>(this.url + '/1/reports/', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () =>
            new Error(
              'ProductReportService.getReport(): error retrieving reports ' + err
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
              'ProductReportService.getReports(): error retrieving reports ' + err
            )
        );
      })
    );
  }

}
