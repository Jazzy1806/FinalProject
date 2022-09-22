export class Breed {
  id: number;
  name: string | null;
  color: string | null;

  constructor(id: number, name: string, color: string) {
    this.id = id;
    this.name = name;
    this.color = color;
  }
}
