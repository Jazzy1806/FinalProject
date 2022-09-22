export class Store {
  id: number;
  name: string | null;
  description: string | null;
  websiteUrl: string | null;
  logoUrl: string | null;

  constructor(  id: number, name: string, description: string, websiteUrl: string, logoUrl: string) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.websiteUrl = websiteUrl;
    this.logoUrl = logoUrl;

  }
}
