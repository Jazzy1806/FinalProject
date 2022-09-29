import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, throwError, Observable, map } from 'rxjs';
import { Buffer } from 'buffer';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Set port number to server's port
  // private baseUrl = 'http://localhost:8090/';
  private baseUrl = environment.baseUrl;

  private url = this.baseUrl;
  loggedInUser = {} as User;

  constructor(private http: HttpClient) {}

  register(user: User): Observable<User> {
    if (user.image === '' || user.image === null) {
      user.image = "https://www.kindpng.com/picc/m/79-793845_meet-greet-person-and-dog-icon-hd-png.png";
    }
    // Create POST request to register a new account
    return this.http.post<User>(this.url + 'register', user).pipe(
      catchError((err: any) => {
        if (err.status === 409) {
          return throwError(
            () => '409'
          );
        }
        console.log(err.status);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
  }

  updateCredentials(user: User) {
    return this.http
      .put<User>(
        this.url + 'api/users/credentials/' + user.id,
        user,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'AuthService.updateUser(): error updating credentials: ' + err
              )
          );
        })
      );
  }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  updateUser(user: User) {
    return this.http
      .put<User>(this.url + 'api/users/' + user.id, user, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error('AuthService.updateUser(): error updating user: ' + err)
          );
        })
      );
  }

  login(username: string, password: string): Observable<User> {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // Create GET request to authenticate credentials
    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      tap((newUser) => {
        // While credentials are stored in browser localStorage, we consider
        // ourselves logged in.
        localStorage.setItem('credentials', credentials);
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.login(): error logging in user.')
        );
      })
    );
  }

  logout(): void {
    localStorage.removeItem('credentials');
  }

  getLoggedInUser(): Observable<User> {
    if (!this.checkLogin()) {
      return throwError(() => {
        new Error('Not logged in.');
      });
    }
    let httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-with': 'XMLHttpRequest',
      },
    };
    return this.http.get<User>(this.baseUrl + 'authenticate', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'AuthService.getUserById(): error retrieving user: ' + err
            )
        );
      })
    );
  }

  checkLogin(): boolean {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string): string {
    return Buffer.from(`${username}:${password}`).toString('base64');
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }

  getAllUsers(): Observable<User[]> {
    return this.http
      .get<User[]>(this.url + 'api/users', this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'AuthService.getAllUsers(): error getting all users: ' + err
              )
          );
        })
      );
  }

  // getUserByUsername(username: string) {
  //   this.getAllUsers().subscribe({
  //     next: (users) => {
  //       for (let user of users) {
  //         if (user.username === username) {
  //           this.loggedInUser = user;
  //         }
  //       }
  //     },

  //     error: (problem) => {
  //       console.error(
  //         'StoreListHttpComponent.reload(): error loading store list'
  //       );
  //       console.error(problem);
  //     },
  //   });
  // }
}
