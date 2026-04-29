import { ChangeDetectorRef, Component } from '@angular/core';
import { Livre } from '../../models/livre.model';
import { RouterLink } from '@angular/router';
import { LivreService } from '../../services/livre';

@Component({
  selector: 'app-liste-livres',
  imports: [RouterLink],
  templateUrl: './liste-livres.html',
  styleUrl: './liste-livres.css',
})
export class ListeLivres {
  livres: Livre[] = [];
  isLoading: boolean = true;
  erreur: string = '';
  constructor(private livreService: LivreService, private cdr: ChangeDetectorRef) {}
  ngOnInit(): void {
    this.livreService.getLivres().subscribe({
      next: (data) => {
        console.log(data);
        this.livres = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.erreur = 'Impossible de charger les livres. Vérifiez que json-server est démarré.';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  supprimerLivre(id: number): void {
    if (confirm('Supprimer ce livre définitivement ?')) {
      this.livreService.supprimerLivre(id).subscribe(() => {
      this.livres = this.livres.filter(l => l.id !== id);
      this.cdr.detectChanges();
    });
    }
  }
}
