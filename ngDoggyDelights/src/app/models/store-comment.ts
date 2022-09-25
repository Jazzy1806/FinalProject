export class StoreComment {
  id: number;
  title: string = '';
  description: string = '';
  rating: number | null;
  createdOn: Date;

  constructor(id: number, title: string, description: string, rating: number, createdOn: Date) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.createdOn = createdOn;
  }
}
