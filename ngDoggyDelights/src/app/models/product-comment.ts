export class ProductComment {
  id: number;
  title: string = '';
  description: string = '';
  rating: number | null;
  dateCreated: Date | null;

  constructor(id: number, title: string, description: string, rating: number, dateCreated: Date) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.dateCreated = dateCreated;
  }
}
