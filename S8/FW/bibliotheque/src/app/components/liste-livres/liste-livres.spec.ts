import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeLivres } from './liste-livres';

describe('ListeLivres', () => {
  let component: ListeLivres;
  let fixture: ComponentFixture<ListeLivres>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeLivres],
    }).compileComponents();

    fixture = TestBed.createComponent(ListeLivres);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
