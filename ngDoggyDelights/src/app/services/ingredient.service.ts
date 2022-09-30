import { Ingredient } from '../models/ingredient';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductReport } from '../models/product-report';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  private baseUrl = environment.baseUrl;
  private uri = 'api/ingredients';
  private url = this.baseUrl + this.uri;

  constructor(
    private http: HttpClient,
    // private datePipe: DatePipe,
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

  index(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>

            new Error('IngredientService.index(): error retrieving ingredients' + err)
        );
      })
    );
  }
}
