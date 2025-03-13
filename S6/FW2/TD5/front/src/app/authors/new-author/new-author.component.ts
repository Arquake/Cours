import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthorService } from '../../author.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-author',
  imports: [ReactiveFormsModule],
  templateUrl: './new-author.component.html',
  styleUrl: './new-author.component.css'
})
export class NewAuthorComponent {
  authorForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
    })
  
    submit = () => {
      if (this.authorForm.valid) {
        let formValues = this.authorForm.getRawValue()
        this.authorService.setter(
          formValues.firstname!,
          formValues.lastname!,
        ).subscribe((data:any)=> {
          this.router.navigate([`/author/`, data.id])
        })
      }
    }
  
    constructor(private authorService: AuthorService, private router: Router) {}
}
