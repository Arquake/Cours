import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
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

  bookForm: FormGroup;

  $authors: Observable<AuthorSummary[]>;

  authors: any[] = [];

  submit = () => {
    let selectedAuthors = this.authors.filter((v)=>v.checked).map((data)=>`/api/authors/${data.id}`)
    if (this.bookForm.valid) {
      let formValues = this.bookForm.getRawValue()
      
      this.bookService.bookSetter(
        formValues.title!,
        formValues.publisher!,
        formValues.year!,
        formValues.backcover!,
        formValues.isbn!,
        selectedAuthors
      ).subscribe((data:any)=> {
        console.log(data)
        this.router.navigate([`/book/`, data.id])
      })
    }
  }

  

  constructor(private bookService: BokkService, private router: Router, private authorService: AuthorService, private fb: FormBuilder) {
    this.bookForm = this.fb.group({
      title: ['', Validators.required],
      publisher: ['', Validators.required],
      year: [2000, Validators.required],
      backcover: ['', Validators.required],
      isbn: ['', Validators.required]
    });

    this.$authors = this.authorService.getAll();
    this.$authors.subscribe((data: any)=>{
      this.authors = data.member;
      this.populateAuthors();
    })
  }

  updateAuthors(i: number) {
    this.authors[i].checked = !this.authors[i].checked
  }

  populateAuthors() {
    this.authors = this.authors.map((data)=>{return {...data, "checked": false}}) // Add new controls for each author
  }
  
}
