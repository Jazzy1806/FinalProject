import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/stores';

//   constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) { }

}
