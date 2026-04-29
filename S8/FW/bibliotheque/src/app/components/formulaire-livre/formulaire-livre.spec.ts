import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireLivre } from './formulaire-livre';

describe('FormulaireLivre', () => {
  let component: FormulaireLivre;
  let fixture: ComponentFixture<FormulaireLivre>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireLivre],
    }).compileComponents();

    fixture = TestBed.createComponent(FormulaireLivre);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
