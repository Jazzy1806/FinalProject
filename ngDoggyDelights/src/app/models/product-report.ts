export class ProductReport {
  id: number;
  createdOn: Date;
  updatedOn: Date | null;
  userQuantity: number | null; //do we want this to be low-normal-high???
  price: number | null;
  isInStock: boolean | null;
  remark: string | null;

  constructor(id: number, createdOn: Date, updatedOn: Date, userQuantity: number, price: number, isInStock: boolean, remark: string; ) {
    this.id = id;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
    this.userQuantity = userQuantity;
    this.price = price;
    this.isInStock = isInStock;
    this.remark = remark;
  }
}
