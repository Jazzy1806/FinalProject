import { Inventory } from './inventory';
import { ProductComment } from './product-comment';
import { ProductReport } from './product-report';
import { Ingredient } from "./ingredient";

export class Product {
  id: number;
  name: string = '';
  brand: string = '';
  description: string = '';
  image: string = '';
  ingredients: Ingredient[] | null;
  reports: ProductReport[] | null;
  comments: ProductComment[] | null;
  inventoryItems: Inventory[] | null;
  dateCreated: Date | null;
  dateUpdated: Date | null;

  constructor(  id: number, name: string, brand: string, description: string, image: string,
                ingredients: Ingredient[], reports: ProductReport[], comments: ProductComment[],
                inventoryItems: Inventory[], dateCreated: Date, dateUpdated: Date) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.image = image;
    this.ingredients = ingredients;
    this.reports = reports;
    this.comments = comments;
    this.inventoryItems = inventoryItems;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
