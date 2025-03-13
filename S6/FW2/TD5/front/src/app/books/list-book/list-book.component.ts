import { Component, NgModule, OnInit, Signal, signal } from '@angular/core';
import { Book } from '../../../entity/book';
import { BokkService } from '../../bokk.service';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-list-book',
  imports: [NgFor, RouterLink, RouterLinkActive],
  templateUrl: './list-book.component.html',
  styleUrl: './list-book.component.css'
})
export class ListBookComponent {
  bookList$: Observable<Book[]>;

  bookList = signal<Book[]>([]);

  bokkList: Book[] = [];

  constructor(private bokkService: BokkService) {
    this.bookList$ = this.bokkService.bookGetAll();
    this.bookList$.subscribe((data: any)=>{
      this.bookList.set(data.member); 
      this.bokkList = data.member;
    });
  }
}
