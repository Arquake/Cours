import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Livre } from '../../models/livre.model';
import { RouterLink } from '@angular/router';
import { LivreService } from '../../services/livre';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-liste-livres',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './liste-livres.html',
  styleUrl: './liste-livres.css',
})
export class ListeLivres implements OnInit {
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

  recherche: string = '';
  get livresFiltres(): Livre[] {
    const terme = this.recherche.toLowerCase();
    return this.livres.filter(l =>
      l.titre.toLowerCase().includes(terme) ||
      l.auteur.toLowerCase().includes(terme)
    );
  }
}
