export class Pet {
  id: number;
  name: string | null;
  weight: number | null;
  gender: string | null;
  image: string | null;
  birthDate: Date | null;

  constructor(  id: number, name: string, weight: number, gender: string, image: string, birthDate: Date) {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.gender = gender;
    this.image = image;
    this.birthDate = birthDate;
  }
}
