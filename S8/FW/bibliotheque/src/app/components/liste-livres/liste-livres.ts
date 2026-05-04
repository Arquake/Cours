import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Livre } from '../../models/livre.model';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
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
  livresToShow: Livre[] = []
  currentPage: number = 1;
  isLoading: boolean = true;
  erreur: string = '';
  constructor(private livreService: LivreService, private cdr: ChangeDetectorRef, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.currentPage = Number(this.route.snapshot.paramMap.get('page') || "1");
    this.livreService.getLivres().subscribe({
      next: (data) => {
        this.livres = data;
        this.isLoading = false;
        if (this.livres.length / 5 <= this.currentPage) {
          this.currentPage = Math.trunc(this.currentPage / 5 + 1);
        }
        this.router.navigate([], {
          relativeTo: this.route,
          queryParams: { page: this.currentPage },
          queryParamsHandling: 'merge'
        });
        this.setLivresToShow();
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.erreur = 'Impossible de charger les livres. Vérifiez que json-server est démarré.';
        this.isLoading = false;
        this.setLivresToShow();
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

  nextPage() {
    if (this.currentPage >= this.livres.length % 5 + 1 ) return
    this.currentPage = this.currentPage + 1;
    this.setLivresToShow();
  }

  previousPage() {
    if (this.currentPage === 1) return
    this.currentPage = this.currentPage - 1;
    this.setLivresToShow();
  }

  setLivresToShow() {
    this.livresToShow = this.livres.filter((_,index)=>index>=(this.currentPage-1)*5 && this.currentPage*5 > index)
  }
}
