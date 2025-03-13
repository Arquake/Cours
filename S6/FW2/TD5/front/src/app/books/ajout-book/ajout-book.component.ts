import { Component } from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { BokkService } from '../../bokk.service';
import { Router } from '@angular/router';
import { AuthorService } from '../../author.service';
import { AuthorSummary } from '../../../entity/AuthorSummary';
import { Observable } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-ajout-book',
  imports: [ReactiveFormsModule, NgFor, NgIf, AsyncPipe],
  templateUrl: './ajout-book.component.html',
  styleUrl: './ajout-book.component.css'
})
export class AjoutBookComponent {

  bookForm = new FormGroup({
    title: new FormControl('', Validators.required),
    publisher: new FormControl('', Validators.required),
    year: new FormControl(2000, Validators.required),
    backcover: new FormControl('', Validators.required),
    isbn: new FormControl('', Validators.required),
    authors: new FormArray([], Validators.required)
  })

  $authors: Observable<AuthorSummary[]>;

  authors: AuthorSummary[] = [];

  submit = () => {
    console.log(this.bookForm.getRawValue())
    if (this.bookForm.valid) {
      let formValues = this.bookForm.getRawValue()
      
      this.bookService.bookSetter(
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

  constructor(private bookService: BokkService, private router: Router, private authorService: AuthorService) {
    this.$authors = authorService.getAll();
    this.$authors.subscribe((data: any)=>{
      this.authors = data.member
    })
  }
}
