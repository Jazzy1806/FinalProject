export class Product {
  id: number;
  name: string | null;
  brand: string | null;
  description: string | null;
  image: string | null;
  dateCreated: Date;
  dateUpdated: Date | null;

  constructor(  id: number, name: string, brand: string, description: string, image: string, dateCreated: Date, dateUpdated: Date;) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.image = image;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
