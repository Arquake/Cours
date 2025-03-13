import { Component, Signal, signal } from '@angular/core';
import { BokkService } from '../../bokk.service';
import { catchError, Observable, of } from 'rxjs';
import { Book } from '../../../entity/book';
import { AsyncPipe, CommonModule, NgIf } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-show-book',
  imports: [NgIf, CommonModule, AsyncPipe, RouterLink],
  templateUrl: './show-book.component.html',
  styleUrl: './show-book.component.css'
})
export class ShowBookComponent {
  book$: Observable<Book>;

  bookId = -1

  constructor(private bokkService: BokkService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe((params)=>{
      this.bookId = params['id']
    })
    this.book$ = this.bokkService.bookGet(this.bookId);
  }

  modify() {
    this.router.navigate(["/book", this.bookId, "edit"])
  }

  delete() {
    this.bokkService.bookDelete(this.bookId).subscribe(()=>{this.router.navigate(["/books"])})
  }
    
}
