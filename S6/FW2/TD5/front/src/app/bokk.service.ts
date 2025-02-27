import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from './book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BokkService {

  constructor(private http: HttpClient) {  }

  bookGetALl(): Observable<Book[]> {
    return this.http.get<Book[]>("http://localhost:8000/api/books")
  }

  bookSetter = (title: string, publisher: string, year: number, backcover: string, isbn: string) => {
    this.http.post<Book>("0.0.0.0:8000/api/books",
      {
        "title": title,
        "publisher": publisher,
        "year": year,
        "backcover": backcover,
        "isbn": isbn
      }
    )
  }

  bookDelete = (id: string | number) => {
    this.http.delete(`0.0.0.0:8000/api/books/${id}`)
  }

  bookGet(id: string | number): Observable<Book> {
    return this.http.get<Book>(`http://localhost:8000/api/books/${id}`)
  }
}
