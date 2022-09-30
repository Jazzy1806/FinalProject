import { EnabledPipe } from './../../pipes/enabled.pipe';
import { NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';
import { PetService } from 'src/app/services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/pet';
import { Diet } from 'src/app/models/diet';
import { Breed } from 'src/app/models/breed';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrls: ['./pet.component.css'],
  providers: [NgbAccordionConfig]
})
export class PetComponent implements OnInit {

  regForm: boolean = false;
  selected: Pet | null = null;
  newPet: Pet = {} as Pet;
  editPet: Pet | null = null;
  petEnable: Pet | null = null;
  pets: Pet[] = [];

  breed1: any = '';
  breed2: any = '';
  addBreeds: Breed[] = [];


  diet1: any = '';
  diet2: any = '';
  diet3: any = '';
  addDiets: Diet[] = [];

  breeds: Breed[] = [];
  diets: Diet[] = [];

  constructor(private petService: PetService, config: NgbAccordionConfig, private enabled: EnabledPipe) {
    config.closeOthers = true;
    config.type = 'info';
  }

  ngOnInit(): void {
    this.getPets();
    this.getBreeds();
    this.getDiets();
    }

  getPets() {
    this.petService.index().subscribe(
      {
        next: (results) => {
          this.pets = results;
          this.pets = this.enabled.transform(this.pets, false);
          if (this.pets.length === 0) {
            this.regForm = true;
          }
        },
        error: (problem) => {
          console.error('PetsHttpComponent.getPets(): error loading pets:');
          console.error(problem);
        }
      }
    );
  }

  getBreeds() {
    this.petService.getBreeds().subscribe(
      {
        next: (results) => {
          this.breeds = results;
        },
        error: (problem) => {
          console.error('PetsHttpComponent.getPets(): error loading pets:');
          console.error(problem);
        }
      }
    );
  }

  getDiets() {
    this.petService.getDiets().subscribe(
      {
        next: (results) => {
          this.diets = results;
        },
        error: (problem) => {
          console.error('PetsHttpComponent.getPets(): error loading pets:');
          console.error(problem);
        }
      }
    );
  }

  setEnabled() {
    if (this.petEnable) {
    this.petEnable.enabled = false;
    this.petService.setEnabled(this.petEnable).subscribe({
      next: (result) => {
        this.petEnable = null;
        this.getPets();
        this.regForm = false;
      },
      error: (nojoy) => {
        console.error('PetssHttpComponent.addPet(): error creating pets:' + nojoy);
      },
    });
    }
  }

  addPet() {
    if (this.breed1 !== '') {
      console.log(this.breed1.id + " " + this.breed1.name);
      this.addBreeds.push(this.breed1);
    }

    if (this.breed2 !== '') {
      console.log(this.breed2.id + " " + this.breed2.name);
      this.addBreeds.push(this.breed2);
    }

    if (this.diet1 !== '') {
      console.log(this.diet1.id + " " + this.diet1.name);
      this.addDiets.push(this.diet1);
    }

    if (this.diet2 !== '') {
      console.log(this.diet2.id + " " + this.diet2.name);
      this.addDiets.push(this.diet2);
    }

    if (this.diet3 !== '') {
      console.log(this.diet3.id + " " + this.diet3.name);
      this.addDiets.push(this.diet3);
    }
    console.log(this.addBreeds);
    this.newPet.breeds = this.addBreeds;
    console.log(this.addDiets);
    this.newPet.dietNeeds = this.addDiets;
     this.petService.create(this.newPet).subscribe({
      next: (result) => {
        this.selected = Object.assign({}, result);
        this.newPet = {} as Pet;
        this.breed1 = {} as Breed;
        this.breed2 = {} as Breed;
        this.diet1 = {} as Diet;
        this.diet2 = {} as Diet;
        this.diet3 = {} as Diet;
        this.addBreeds = [];
        this.addDiets = [];
        this.getPets();
        this.regForm = false;
      },
      error: (nojoy) => {
        console.error('PetssHttpComponent.addPet(): error creating pets:' + nojoy);
      },
    });
  }

  updatePet() {
    if (this.breed1 !== '') {
      console.log(this.breed1.id + " " + this.breed1.name);
      this.addBreeds.push(this.breed1);
    }

    if (this.breed2 !== '') {
      console.log(this.breed2.id + " " + this.breed2.name);
      this.addBreeds.push(this.breed2);
    }

    if (this.diet1 !== '') {
      console.log(this.diet1.id + " " + this.diet1.name);
      this.addDiets.push(this.diet1);
    }

    if (this.diet2 !== '') {
      console.log(this.diet2.id + " " + this.diet2.name);
      this.addDiets.push(this.diet2);
    }

    if (this.diet3 !== '') {
      console.log(this.diet3.id + " " + this.diet3.name);
      this.addDiets.push(this.diet3);
    }
    if (this.editPet != null) {
      console.log(this.addBreeds);
      this.editPet.breeds = this.addBreeds;
          this.editPet.dietNeeds = this.addDiets;
      console.log(this.addDiets);
      console.log(this.editPet);
      this.petService.updatePet(this.editPet).subscribe({
        next: (result) => {
          this.editPet = null;
          this.selected = Object.assign({}, result);
          this.breed1 = {} as Breed;
          this.breed2 = {} as Breed;
          this.diet1 = {} as Diet;
          this.diet2 = {} as Diet;
          this.diet3 = {} as Diet;
          this.addBreeds = [];
          this.addDiets = [];
          this.getPets();
        },
        error: (nojoy) => {
          console.error('PetsHttpComponent.updatePet(): error updating pets:' + nojoy);
        },
      });
    }
  }

}
