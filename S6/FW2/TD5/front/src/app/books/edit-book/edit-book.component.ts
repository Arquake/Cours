import { Component, Signal } from '@angular/core';
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

  authorPopulated: boolean = false

  constructor(private bokkService: BokkService, private route: ActivatedRoute, private router: Router, private authorService: AuthorService) {
    this.route.params.subscribe((params)=>{
      this.bookId = params['id']
    })
    this.book$ = this.bokkService.bookGet(this.bookId)
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
    this.$authors.subscribe((authorsData: any)=>{
      this.authors = authorsData.member;
      this.populateAuthors();
    })

    
  }

  updateAuthors(i: number) {
    this.authors[i].checked = !this.authors[i].checked
  }

  populateAuthors() {
    let authorsInBook: AuthorSummary[];
    this.book$.subscribe((data)=> {
      authorsInBook = data.author
      let authorsInBookIds = data.author.map((data)=>data.id)
      this.authors = [...this.authors.map((data)=>{return {...data, "checked": authorsInBookIds.includes(data.id)}})]
      this.authorPopulated = true
    })
    }
  
  submit = () => {
    let selectedAuthors = this.authors.filter((v)=>v.checked).map((data)=>`/api/authors/${data.id}`)
    console.log(selectedAuthors)
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

  trackByAuthor(index: number, author: any): number {
    return author.id;
  }
}
