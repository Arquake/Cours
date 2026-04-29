import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Livre } from '../models/livre.model';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
  private apiUrl = 'http://localhost:3000/livres';
  constructor(private http: HttpClient) {}
  getLivres(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiUrl);
  }
  getLivreById(id: number): Observable<Livre> {
    return this.http.get<Livre>(`${this.apiUrl}/${id}`);
  }
  ajouterLivre(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(this.apiUrl, livre);
  }
  modifierLivre(id: number, livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(`${this.apiUrl}/${id}`, livre);
  }
  supprimerLivre(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
