import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutBookComponent } from './ajout-book.component';

describe('AjoutBookComponent', () => {
  let component: AjoutBookComponent;
  let fixture: ComponentFixture<AjoutBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjoutBookComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
