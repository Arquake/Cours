import { Component, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthorService } from '../../author.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgFor } from '@angular/common';
import { Author } from "../../../entity/author"

@Component({
  selector: 'app-list-author',
  imports: [NgFor, RouterLink, RouterLinkActive],
  templateUrl: './list-author.component.html',
  styleUrl: './list-author.component.css'
})
export class ListAuthorComponent {
  authorList$: Observable<Author[]>;

  authorList = signal<Author[]>([]);

  aList: Author[] = [];

  constructor(private authorService: AuthorService) {
    this.authorList$ = this.authorService.getAll();
    this.authorList$.subscribe((data: any)=>{
      this.authorList.set(data.member); 
      this.aList = data.member;
    });
  }
}
