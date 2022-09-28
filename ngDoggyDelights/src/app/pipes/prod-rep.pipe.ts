import { ProductReport } from 'src/app/models/product-report';
import { Product } from 'src/app/models/product';
import { Inventory } from 'src/app/models/inventory';
import { Pipe, PipeTransform } from '@angular/core';
import { Store } from '../models/store';

@Pipe({
  name: 'prodRep'
})
export class ProdRepPipe implements PipeTransform {

  prodResults: Product[] = [];
  mostRecentProdUpdates: Inventory[] = [];
  mostRecentProdReports: ProductReport[] = [];
  mostRecentUpdate: Inventory = {} as Inventory;

  updatesByDate: any[] = [];

  transform(products: Product[]): any[]  {
    if (products.length > 0) {
    let counter: number = 0;

    for (let p of products) {
      if (p.inventoryItems !== null) {
        for (let i of p.inventoryItems) {
          if (counter === 0) {
            this.mostRecentUpdate = i;
          }
          else {
            if (i.dateCreated !== null && this.mostRecentUpdate.dateCreated !== null) {
              if (i.dateCreated > this.mostRecentUpdate.dateCreated) {
                this.mostRecentUpdate = i;
              }
            }
            else if (this.mostRecentUpdate.dateCreated === null) {
              this.mostRecentUpdate = i;
            }
          }
        }
      }
      counter += 1;
      this.mostRecentProdUpdates?.push(this.mostRecentUpdate);
    }


    counter = 0;
    let update = 0;



    for (let p of products) {
      if (p.reports !== null) {
        for (let r of p.reports) {
          for (let i of this.mostRecentProdUpdates) {
            if (i.product.id === r.product.id) {
              update +=1;
              if (r.createdOn !== null && i.dateCreated !== null) {
                if (r.createdOn > i.dateCreated) {
                  this.mostRecentProdReports.push(r);
                }
              }
            }
          }
          if (update === 0) {
            this.mostRecentProdReports.push(r);
          }
          update = 0;

        }
      }
    }
      this.updatesByDate =this.mostRecentProdUpdates;
      for (let e of this.mostRecentProdReports) {
        this.updatesByDate.push(e);
      }
      return this.updatesByDate;
    }
    return products;
}
}
