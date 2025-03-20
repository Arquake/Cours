import { Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomeComponent } from './home/home.component';
import { ListBookComponent } from './books/list-book/list-book.component';
import { ShowBookComponent } from './books/show-book/show-book.component';
import { AjoutBookComponent } from './books/ajout-book/ajout-book.component';
import { EditBookComponent } from './books/edit-book/edit-book.component';
import { ListAuthorComponent } from './authors/list-author/list-author.component';
import { ShowAuthorComponent } from './authors/show-author/show-author.component';
import { NewAuthorComponent } from './authors/new-author/new-author.component';
import { EditAuthorComponent } from './authors/edit-author/edit-author.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "about", component: AboutComponent},
    {path: "welcome", component: WelcomeComponent},

    {path: "books", component: ListBookComponent},
    {path: "book/:id", component: ShowBookComponent},
    {path: "books/new", component: AjoutBookComponent},
    {path: "book/:id/edit", component: EditBookComponent},

    {path: "authors", component: ListAuthorComponent},
    {path: "author/:id", component: ShowAuthorComponent},
    {path: "authors/new", component: NewAuthorComponent},
    {path: "author/:id/edit", component: EditAuthorComponent},

    {path: "register", component: RegisterComponent},
    {path: "login", component: LoginComponent}
];
