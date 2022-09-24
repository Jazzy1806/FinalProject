import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/products';

//   constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) { }

}
