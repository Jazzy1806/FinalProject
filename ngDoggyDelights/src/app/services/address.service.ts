import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

    private baseUrl = 'http://localhost:8090/';
    private url = this.baseUrl + 'api/addresses';

  //   constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) { }


  //   getHttpOptions() {
  //     let options = {
  //       headers: {
  //         Authorization: 'Basic ' + this.authService.getCredentials(),
  //         'X-Requested-With': 'XMLHttpRequest',
  //       },
  //     };
  //     return options;
  //   }


  //   index(): Observable<Todo[]> {
  //     return this.http.get<Todo[]>(this.url, this.getHttpOptions()).pipe(
  //       catchError((err:any) => {
  //         console.log(err);
  //         return throwError(
  //           () => new Error('TodoService.index(): erorr retrieving todos: ' + err)
  //         );
  //       })
  //     );
  //   }

  //   create(todo: Todo): Observable<Todo> {
  //     todo.completed = false;
  //     console.log("In service.create " + todo.task);
  //     return this.http.post<Todo>(this.url, todo, this.getHttpOptions()).pipe(
  //       catchError((err:any) => {
  //         console.error(err);
  //         return throwError(
  //           () => new Error('TodoService.create(): error creating todo: ' + err)
  //         );
  //       })
  //     );
  //   }

  //   show(id: number) {
  //     console.log("Todo id: " + id);
  //     return this.http.get<Todo>(this.url + "/" + id, this.getHttpOptions()).pipe(
  //       catchError((err:any) => {
  //         console.error(err);
  //         return throwError(
  //           () => new Error('TodoService.show(): error finding todo: ' + err)
  //         );
  //       })
  //     );
  //   }

  //   updateTodo(todo: Todo) {
  //     if (todo.completed) {
  //       todo.completeDate = this.datePipe.transform(Date.now(), 'shortDate'); //applies pipe (2nd argument) to value in 1st argument
  //     }
  //     else {
  //       todo.completeDate = "";
  //     }
  //     return this.http.put<Todo>(this.url + "/" + todo.id, todo, this.getHttpOptions()).pipe(
  //       catchError((err:any) => {
  //         console.error(err);
  //         return throwError(
  //           () => new Error('TodoService.update(): error updating todo: ' + err)
  //         );
  //       })
  //     );
  //   }

  //   destroy(id: number) {
  //     return this.http.delete(this.url + "/" + id, this.getHttpOptions()).pipe(
  //       catchError((err:any) => {
  //         console.log(err);
  //         return throwError(
  //           () => new Error('TodoService.delete(): erorr deleting todos: ' + err)
  //         );
  //       })
  //     );
  //   }

  // }

}
