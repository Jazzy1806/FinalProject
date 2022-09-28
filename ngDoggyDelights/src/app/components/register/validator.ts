import { AuthService } from 'src/app/services/auth.service';
import {
  AbstractControl,
  AsyncValidatorFn,
  FormControl,
  FormGroup,
} from '@angular/forms';
import { User } from 'src/app/models/user';
import { map } from 'rxjs/operators';

export function zipcodeValidator(control: AbstractControl) {
  if (control && (control.value !== null || control.value !== undefined)) {
    const regex = new RegExp('^[0-9]{6}$');

    if (!regex.test(control.value)) {
      return {
        isError: true,
      };
    }
  }

  return null;
}

export function passValidator(control: AbstractControl) {
  if (control && (control.value !== null || control.value !== undefined)) {
    const cnfpassValue = control.value;

    const passControl = control.root.get('password'); // magic is this
    if (passControl) {
      const passValue = passControl.value;
      if (passValue !== cnfpassValue || passValue === '') {
        return {
          isError: true,
        };
      }
    }
  }

  return null;
}

export function uniqueUsernameValidator(
  control: AbstractControl,
  users: User[]
) {
  if (control && (control.value !== null || control.value !== undefined)) {
    const username = control.value;
    for (let user of users) {
      if (user.username === username) {
        return {
          isError: true,
        };
      }
    }
  }
  return null;
}

// export function emailDomainValidator(control: FormControl) {
//   let email = control.value;
//   if (email && email.indexOf("@") != -1) {
//     let [_, domain] = email.split("@");
//     if (domain !== "codecraft.tv") {
//       return {
//         emailDomain: {
//           parsedDomain: domain
//         }
//       }
//     }
//   }
//   return null;
// }

export function uniqueUsernameUpdateValidator(
  userService: AuthService
): AsyncValidatorFn {
  return (c: AbstractControl): { [key: string]: boolean } | any => {
    //var currentUsername = JSON.parse(sessionStorage.getItem("currentUser"))["username"];
    var currentUsername = '';
    userService.getLoggedInUser().subscribe({
      next: (user) => {
        currentUsername = user.username;
      },
      error: (problem) => {
        console.error(
          'StoreListHttpComponent.collectLoggedInUser(): error loading user logged in'
        );
        console.error(problem);
      },
    });
    var username = c.value;
    var userBasedOnUsernameFilledIn: User | null = null;
    userService.getAllUsers().subscribe({
      next: (users) => {
        for (let user of users) {
          if (user.username === username) {
            userBasedOnUsernameFilledIn = user;
          } else {
            userBasedOnUsernameFilledIn = null;
          }
        }
      },
      error: (problem) => {
        console.error(
          'StoreListHttpComponent.reload(): error loading store list'
        );
        console.error(problem);
      },
    });
    if (userBasedOnUsernameFilledIn) {
      return { uniqueUsernameUpdate: true };
    } else {
      // if (userBasedOnUsernameFilledIn.username != currentUsername) {
      //   return { uniqueUsernameUpdate: true };
      // } else {
      //   return null;
      // }
    }
  };
}
// return userService.getUserByUsername(c.value)
// .pipe(
// map(user => {
// if(user.length > 0){
// var username = user[0]["username"];
// if(username != currentUsername){
// return {'uniqueUsernameUpdate': true}
// }else{
// return null;
// }
// }else{
// return null;
// }
// })
// )
// }
