import { Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomeComponent } from './home/home.component';
import { ListBookComponent } from './list-book/list-book.component';
import { ShowBookComponent } from './show-book/show-book.component';

export const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "about", component: AboutComponent},
    {path: "welcome", component: WelcomeComponent},
    {path: "books", component: ListBookComponent},
    {path: "book/:id", component: ShowBookComponent}
];
