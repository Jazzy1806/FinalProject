import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'topRatedList'
})
export class TopRatedListPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
