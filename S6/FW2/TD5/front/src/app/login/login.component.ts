import { Component } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  registerForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  })

  get isValid() { return this.registerForm.valid }
  submit() {
    let v: any = this.registerForm.getRawValue
    console.log(v)
    this.auth.login(v['email'], v['password'])
  }
  
  
  constructor(public auth: AuthenticationService) { }
}
