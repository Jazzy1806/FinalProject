import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'enabled'
})
export class EnabledPipe implements PipeTransform {

  transform(listElements: any[], showAll: boolean): any[] {
    if (showAll) {
      return listElements;
    }
    else {
    let results: any[] = [];
    for (let e of listElements) {
      if (e.enabled) {
        results.push(e);
      }
    }
    return results;
  }
}
}
