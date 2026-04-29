import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailLivre } from './detail-livre';

describe('DetailLivre', () => {
  let component: DetailLivre;
  let fixture: ComponentFixture<DetailLivre>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailLivre],
    }).compileComponents();

    fixture = TestBed.createComponent(DetailLivre);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
