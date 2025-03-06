import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../entity/book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BokkService {

  apiLink = 'http://127.0.0.1:8000/api'

  constructor(private http: HttpClient) {  }

  bookGetAll = (): Observable<Book[]> => {
    return this.http.get<Book[]>(this.apiLink + "/books")
  }

  bookSetter = (title: string, publisher: string, year: number, backcover: string, isbn: string): Observable<Book> => {
    return this.http.post<Book>(this.apiLink + "/books",
      {
        "title": title,
        "publisher": publisher,
        "year": year,
        "backcover": backcover,
        "isbn": isbn
      },
      {
        headers: new HttpHeaders({'Content-Type': 'application/ld+json'})
      }
    )
  }

  bookDelete = (id: string | number): Observable<any> => {
    return this.http.delete(this.apiLink+`/books/${id}`)
  }

  bookGet = (id: string | number): Observable<Book> => {
    return this.http.get<Book>(this.apiLink+`/books/${id}`)
  }

  bookPatch = (id:number, title: string, publisher: string, year: number, backcover: string, isbn: string): Observable<Book> => {
    return this.http.patch<Book>(this.apiLink + "/books/"+ id,
      {
        "title": title,
        "publisher": publisher,
        "year": year,
        "backcover": backcover,
        "isbn": isbn
      },
      {
        headers: new HttpHeaders({'Content-Type': 'application/merge-patch+json'})
      }
    )
  }
}
