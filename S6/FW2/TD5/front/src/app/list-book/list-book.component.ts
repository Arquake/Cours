import { Component, NgModule, OnInit } from '@angular/core';
import { Book } from '../book';
import { BokkService } from '../bokk.service';
import { catchError, of } from 'rxjs';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-list-book',
  imports: [NgFor],
  templateUrl: './list-book.component.html',
  styleUrl: './list-book.component.css'
})
export class ListBookComponent implements OnInit {
  bookList: Book[] = [];

  constructor(private bokkService: BokkService) {}

  ngOnInit() {
    this.bokkService.bookGetALl().pipe(
      catchError((error) => {
        return of([]);
      })
    ).subscribe((res: any) => {
      this.bookList = res.member;
    });
  }
}
