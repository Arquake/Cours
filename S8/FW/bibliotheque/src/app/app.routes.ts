import { Routes } from '@angular/router';
import { ListeLivres } from './components/liste-livres/liste-livres';
import { DetailLivre } from './components/detail-livre/detail-livre';

export const routes: Routes = [
  { path: '', redirectTo: 'livres', pathMatch: 'full' },
  { path: 'livres', component: ListeLivres },
  { path: 'livres/:id', component: DetailLivre },
  {
  path: 'ajouter',
  loadComponent: () =>
  import('./components/formulaire-livre/formulaire-livre')
  .then(m => m.FormulaireLivre) // Lazy Loading
  },
  {
  path: 'modifier/:id',
  loadComponent: () =>
  import('./components/formulaire-livre/formulaire-livre')
  .then(m => m.FormulaireLivre) // Lazy Loading
  },
  { path: '**', redirectTo: 'livres' }
];
