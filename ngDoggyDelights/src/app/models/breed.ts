export class Breed {
  id: number;
  name: string | null;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}
