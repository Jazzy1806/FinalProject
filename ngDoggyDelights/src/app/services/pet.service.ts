import { EnabledPipe } from './../pipes/enabled.pipe';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Pet } from '../models/pet';
import { catchError, Observable, throwError } from 'rxjs';
import { Breed } from '../models/breed';
import { Diet } from '../models/diet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/pets';

  constructor(private http: HttpClient, private enabledPipe: EnabledPipe, private authService: AuthService) { }



  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  index(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('PetService.index(): error retrieving pets: ' + err)
        );
      })
    );
  }

  create(pet: Pet): Observable<Pet> {
    console.log("In service.create " + pet.name);
    return this.http.post<Pet>(this.url, pet, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.create(): error creating pet: ' + err)
        );
      })
    );
  }

  updatePet(pet: Pet) {
    return this.http.put<Pet>(this.url + "/" + pet.id, pet, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.update(): error updating pet: ' + err)
        );
      })
    );
  }

  // destroy(id: number) {
  //   return this.http.delete(this.url + "/" + id, this.getHttpOptions()).pipe(
  //     catchError((err:any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('PetService.delete(): erorr deleting pets: ' + err)
  //       );
  //     })
  //   );
  // }

  getBreeds(): Observable<Breed[]> {
    console.log("In service.getBreeds");
    return this.http.get<Breed[]>(this.url + "/breeds", this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.getBreeds(): error retreiving breeds: ' + err)
        );
      })
    );
  }

  getDiets(): Observable<Diet[]> {
    console.log("In service.getDiets");
    return this.http.get<Diet[]>(this.url + "/diets", this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.getDiets(): error retreiving diets: ' + err)
        );
      })
    );
  }
}
