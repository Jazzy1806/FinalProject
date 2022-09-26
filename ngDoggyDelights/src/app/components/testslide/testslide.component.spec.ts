import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestslideComponent } from './testslide.component';

describe('TestslideComponent', () => {
  let component: TestslideComponent;
  let fixture: ComponentFixture<TestslideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestslideComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestslideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
