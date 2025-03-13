import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../entity/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  apiLink = 'http://127.0.0.1:8000/api'
  
    constructor(private http: HttpClient) {  }
  
    getAll = (): Observable<Author[]> => {
      return this.http.get<Author[]>(this.apiLink + "/authors")
    }
  
    setter = (firstname: string, lastname: string): Observable<Author> => {
      return this.http.post<Author>(this.apiLink + "/authors",
        {
          "firstname": firstname,
          "lastname": lastname,
        },
        {
          headers: new HttpHeaders({'Content-Type': 'application/ld+json'})
        }
      )
    }
  
    delete = (id: string | number): Observable<any> => {
      return this.http.delete(this.apiLink+`/authors/${id}`)
    }
  
    get = (id: string | number): Observable<Author> => {
      return this.http.get<Author>(this.apiLink+`/authors/${id}`)
    }
  
    patch = (id: number, firstname: string, lastname: string): Observable<Author> => {
      return this.http.patch<Author>(this.apiLink + "/authors/"+ id,
        {
          "firstname": firstname,
          "lastname": lastname,
        },
        {
          headers: new HttpHeaders({'Content-Type': 'application/merge-patch+json'})
        }
      )
    }
}
