import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../../../entity/author';
import { AuthorService } from '../../author.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AsyncPipe, CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-show-author',
  imports: [NgIf, AsyncPipe, CommonModule, RouterLink],
  templateUrl: './show-author.component.html',
  styleUrl: './show-author.component.css'
})
export class ShowAuthorComponent {
    author$: Observable<Author>;
  
    authorId = -1
  
    constructor(private authorService: AuthorService, private route: ActivatedRoute, private router: Router) {
      this.route.params.subscribe((params)=>{
        this.authorId = params['id']
      })
      this.author$ = this.authorService.get(this.authorId);
    }
  
    modify() {
      this.router.navigate(["/author", this.authorId, "edit"])
    }
  
    delete() {
      this.authorService.delete(this.authorId).subscribe(()=>{this.router.navigate(["/authors"])})
    }
}
