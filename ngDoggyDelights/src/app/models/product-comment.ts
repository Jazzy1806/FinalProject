import { Product } from 'src/app/models/product';
export class ProductComment {
  id: number = 0;
  title: string = '';
  description: string = '';
  rating: number = 0;
  dateCreated: Date = new Date();
  product: Product = new Product();

  constructor(
    id: number = 0,
    title: string = '',
    description: string = '',
    rating: number = 1,
    dateCreated: Date = new Date(),
    product: Product = new Product()
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.dateCreated = dateCreated;
    this.product = product;
  }
}
