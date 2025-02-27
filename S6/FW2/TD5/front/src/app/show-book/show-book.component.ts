import { Component } from '@angular/core';
import { BokkService } from '../bokk.service';
import { catchError, of } from 'rxjs';
import { Book } from '../book';
import { NgIf } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-book',
  imports: [NgIf],
  templateUrl: './show-book.component.html',
  styleUrl: './show-book.component.css'
})
export class ShowBookComponent {
  book: Book | null;

  constructor(private bokkService: BokkService, private route: ActivatedRoute) { this.book = null }

  ngOnInit() {
    this.route.params.subscribe((params)=>{
      let bookId = params['id']
      this.bokkService.bookGet(bookId).pipe(
        catchError((error) => {
          return of({});
        })
      ).subscribe((res: any) => {
        this.book = res;
        console.log(this.book)
      });
    })
  }
    
}
