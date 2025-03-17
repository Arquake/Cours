import { Component } from '@angular/core';
import { BokkService } from '../../bokk.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from '../../../entity/book';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthorSummary } from '../../../entity/AuthorSummary';
import { AuthorService } from '../../author.service';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-edit-book',
  imports: [ReactiveFormsModule, NgIf, NgFor, AsyncPipe],
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

  $authors: Observable<AuthorSummary[]>;
  
  authors: any[] = [];

  constructor(private bokkService: BokkService, private route: ActivatedRoute, private router: Router, private authorService: AuthorService) {
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
    this.authors = this.authors.map((data)=>{return {...data, "checked": /* si l'autheur n'est pas dans la liste du livre */}}) // Add new controls for each author
  }
  
  submit = () => {
    let selectedAuthors = this.authors.filter((v)=>v.checked).map((data)=>`/api/authors/${data.id}`)
    if (this.bookForm.valid) {
      
      if (this.bookForm.valid) {
        let formValues = this.bookForm.getRawValue()
        
        this.bokkService.bookPatch(
          this.bookId,
          formValues.title!,
          formValues.publisher!,
          Number(formValues.year!),
          formValues.backcover!,
          formValues.isbn!,
          selectedAuthors
        ).subscribe((data:any)=> {
          this.router.navigate([`/book/`, data.id])
        })
      }
      
    }
  }
}
