import { Component } from '@angular/core';
import { BokkService } from '../bokk.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from '../../entity/book';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-book',
  imports: [ReactiveFormsModule],
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.css'
})
export class EditBookComponent {
  book$: Observable<Book>;
  bookId = -1
  bookForm = new FormGroup({
    title: new FormControl('', Validators.required),
    publisher: new FormControl('', Validators.required),
    year: new FormControl(2000, Validators.required),
    backcover: new FormControl('', Validators.required),
    isbn: new FormControl('', Validators.required),
  })

  constructor(private bokkService: BokkService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe((params)=>{
      this.bookId = params['id']
    })
    this.book$ = this.bokkService.bookGet(this.bookId);
    this.book$.subscribe((data)=>{
      this.bookForm.setValue({
        title: data.title,
        publisher: data.publisher,
        year: data.year,
        isbn: data.isbn,
        backcover: data.backcover
      })
    })
  }
  
  submit = () => {
    if (this.bookForm.valid) {
      let formValues = this.bookForm.getRawValue()
      this.bokkService.bookPatch(
        this.bookId,
        formValues.title!,
        formValues.publisher!,
        formValues.year!,
        formValues.backcover!,
        formValues.isbn!
      ).subscribe((data:any)=> {
        this.router.navigate([`/book/`, data.id])
      })
    }
  }
}
