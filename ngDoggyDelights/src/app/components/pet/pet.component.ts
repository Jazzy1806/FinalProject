import { PetService } from 'src/app/services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/pet';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrls: ['./pet.component.css']
})
export class PetComponent implements OnInit {

  selected: Pet | null = null;
  newPet: Pet = {} as Pet;
  editPet: Pet | null = null;
  pets: Pet[] = [];

  constructor(private petService: PetService) { }

  ngOnInit(): void {
    this.getPets();

  }

  getPets() {
    this.petService.index().subscribe(
      {
        next: (results) => {
          this.pets = results;
        },
        error: (problem) => {
          console.error('PetsHttpComponent.getPets(): error loading pets:');
          console.error(problem);
        }
      }
    );
  }

  addPet() {
    this.petService.create(this.newPet).subscribe({
      next: (result) => {
        this.selected = Object.assign({}, result);
        this.newPet = {} as Pet;
        this.getPets();
      },
      error: (nojoy) => {
        console.error('PetssHttpComponent.addPet(): error creating pets:' + nojoy);
      },
    });
  }

}
