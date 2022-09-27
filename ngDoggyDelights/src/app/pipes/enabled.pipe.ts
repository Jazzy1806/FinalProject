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
//grab all stores that carry that product, then populate product reports by store, send that through pipe
//Product report Pipe(Store *will have access to inv and PR[]*, prodID): filter list (find product report by product id) then
//filter by most recent date, populating single PR with most recent report
//compare to inventory item date to see which is most recent
}
