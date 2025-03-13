import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAuthorComponent } from './show-author.component';

describe('ShowAuthorComponent', () => {
  let component: ShowAuthorComponent;
  let fixture: ComponentFixture<ShowAuthorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAuthorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
