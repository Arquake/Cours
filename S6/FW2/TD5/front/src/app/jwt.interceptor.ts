import { Injectable } from "@angular/core";
import { AuthenticationService } from "./authentication.service";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private auth: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): 
    Observable<HttpEvent<any>> 
  {
    if (this.auth.isAuthentified)
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.token}`
        }
      })
    return next.handle(request);
  }
}