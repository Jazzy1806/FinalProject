import { ProductReport } from 'src/app/models/product-report';
import { Product } from 'src/app/models/product';
import { Inventory } from 'src/app/models/inventory';
import { Pipe, PipeTransform } from '@angular/core';
import { Store } from '../models/store';

@Pipe({
  name: 'prodRep'
})
export class ProdRepPipe implements PipeTransform {

  mostRecentProdUpdates: Inventory[] = [];
  mostRecentProdReports: ProductReport[] = [];

  mostRecentInv: Inventory = {} as Inventory;
  mostRecentPR: ProductReport = {} as ProductReport;

  updatesByDate: any[] = [];

  transform(products: Product[], storesWithProduct: Store[]): any[]  {
    const hasValues: any = (obj: any) => Object.values(obj).some(v => v !== null && typeof v !== "undefined");

    if (products.length > 0) {

    this.updatesByDate = [];
    this. mostRecentProdUpdates = [];
    this.mostRecentProdReports = [];
    this.mostRecentInv = {} as Inventory;
    this.mostRecentPR = {} as ProductReport;

    let counter: number = 0;
    let prUpdate: number = 0;


    for (let p of products) {
      for (let s of storesWithProduct) {
        console.log("Store: ", s);
        if (s.inventories !== null) {
          console.log("Store inventory: ", s.inventories);
        for (let i of s.inventories) {
          console.log("Inv Item in loop: ", i);
          if (p.id === i.product.id && i.createdOn !== null) {
            if (counter === 0) {
              console.log("Inv Item after ID match: ", i);
              this.mostRecentInv = i;
              console.log("Most recent first entry: ", this.mostRecentInv);
              counter += 1;
            } else {
              if (i.createdOn !== null && this.mostRecentInv.createdOn !== null) {
                if (i.createdOn > this.mostRecentInv.createdOn) {
                  this.mostRecentInv = i;
                  console.log("Most recent after date eval: ", this.mostRecentInv);
                  counter += 1;
                }
              }
            }
          }
        }

        counter = 0;

        if (this.mostRecentInv !== null && hasValues(this.mostRecentInv)) {
          if (this.mostRecentInv.product?.reports !== null && hasValues(this.mostRecentInv.product?.reports)) {
            for (let r of this.mostRecentInv.product.reports) {
              console.log("Product report inside date comp loop: ", r);
                if (r.createdOn !== null && this.mostRecentInv.createdOn !== null && r.createdOn !== undefined ) {
                  if (prUpdate === 1) {
                    if (r.createdOn !== null && this.mostRecentPR.createdOn !== null && this.mostRecentPR.createdOn !== undefined) {
                      if (r.createdOn > this.mostRecentPR.createdOn) {
                        this.mostRecentPR = r;
                        console.log("Most recent PR after date eval: ", this.mostRecentPR);
                      }
                    }
                  }
                  else if (prUpdate === 0) {
                    if (r.createdOn > this.mostRecentInv.createdOn) {
                      this.mostRecentPR = r;
                      console.log("Most recent PR after date eval: ", this.mostRecentPR);
                      prUpdate = 1;
                    }
                  }
                }
            }
          }
        }
          else { //if mostREcentIV is null/undefined- still run the loop
              console.log("Store: ", s);
              if (s.productReports !== null) {
                console.log("Store reports: ", s.productReports);
              for (let r of s.productReports) {
                console.log("Prod Rep in loop: ", r);
                if (r.product !== undefined && p.id === r.product.id && r.createdOn !== null) {
                  if (counter === 0) {
                    console.log("Prod Rep after ID match: ", r);
                    this.mostRecentPR = r;
                    console.log("Most recent first entry: ", this.mostRecentPR);
                    counter += 1;
                  }
                  else {
                    if (r.createdOn !== null && this.mostRecentPR.createdOn !== null && r.createdOn !== undefined && this.mostRecentPR.createdOn !== undefined) {
                      if (r.createdOn > this.mostRecentPR.createdOn) {
                        this.mostRecentPR = r;
                        console.log("Most recent after date eval: ", this.mostRecentPR);
                        counter += 1;
                      }
                    }
                  }
                }
              }
            }
          }
        if (prUpdate === 1) {
          this.mostRecentInv = {} as Inventory;
          if (this.mostRecentPR !== null && hasValues(this.mostRecentPR)) {
            this.mostRecentProdReports.push(this.mostRecentPR);
            this.mostRecentPR = {} as ProductReport;
          }
        }

        if (this.mostRecentInv !== null && hasValues(this.mostRecentInv)) {
          this.mostRecentProdUpdates.push(this.mostRecentInv);
          this.mostRecentInv = {} as Inventory;
        }
        }
      }

    }
  }

  if (this.mostRecentProdUpdates.length > 0) {
    this.updatesByDate.push(this.mostRecentProdUpdates);
  }
  if (this.mostRecentProdReports.length > 0) {
    this.updatesByDate.push(this.mostRecentProdReports);
  }
  return this.updatesByDate;
  }
}
