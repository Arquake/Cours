import { Component } from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-ajout-book',
  imports: [],
  templateUrl: './ajout-book.component.html',
  styleUrl: './ajout-book.component.css'
})
export class AjoutBookComponent {
  bookForm = new FormControl()
}
