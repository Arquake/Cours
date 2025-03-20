import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private server = 'http://localhost:8000/api'
  private _token?: string = undefined
  private _username?: string = undefined
  private _error: boolean = false

  public reset_error() { this._error = false }
  public get error() { return this._error }

  public get isAuthentified(): boolean {
    return this._token !== undefined
  }
  public get token(): string {
    return this._token ? this._token : ''
  }
  public get username(): string {
    return this._username ? this._username : ''
  }
  constructor(private http: HttpClient) { }

  public login(username: string, password: string): void {
    this.http.post(this.server + '/login_check', { username, password })
      .pipe(tap(console.log),
      ).subscribe({
        next: response => {
          if (response) {
            this._token = response['token']
            this._username = username
          } else this._error = true
        },
        error: error => { this._error = true }
      })
  }

  public register(username: string, password: string) {
    this.http.post(this.server + '/register', { username, password })
      .pipe(tap(console.log),
      ).subscribe({
        next: response => {
          if (response) {
            this._token = response['token']
            this._username = username
          } else this._error = true
        },
        error: error => { this._error = true }
      })
  }
}
