import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../../../entity/author';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthorService } from '../../author.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-author',
  imports: [ReactiveFormsModule],
  templateUrl: './edit-author.component.html',
  styleUrl: './edit-author.component.css'
})
export class EditAuthorComponent {
    author$: Observable<Author>;
    authorId = -1
    authorForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required)
    })
  
    constructor(private authorService: AuthorService, private route: ActivatedRoute, private router: Router) {
      this.route.params.subscribe((params)=>{
        this.authorId = params['id']
      })
      this.author$ = this.authorService.get(this.authorId);
      this.author$.subscribe((data)=>{
        this.authorForm.setValue({
          firstname: data.firstname,
          lastname: data.lastname,
        })
      })
    }
    
    submit = () => {
      if (this.authorForm.valid) {
        
        if (this.authorForm.valid) {
          let formValues = this.authorForm.getRawValue()
          
          this.authorService.patch(
            this.authorId,
            formValues.firstname!,
            formValues.lastname!
          ).subscribe((data:any)=> {
            this.router.navigate([`/author/`, data.id])
          })
        }
        
      }
    }
}
