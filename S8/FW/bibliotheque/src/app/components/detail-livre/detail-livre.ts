import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Livre } from '../../models/livre.model';
import { LivreService } from '../../services/livre';

@Component({
  selector: 'app-detail-livre',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './detail-livre.html',
  styleUrl: './detail-livre.css',
})
export class DetailLivre {
  livre: Livre | null = null;
  constructor(private route: ActivatedRoute, private livreService: LivreService, private cdr: ChangeDetectorRef) {}
  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.livreService.getLivreById(id).subscribe({
      next: (data) => {this.livre = data; this.cdr.detectChanges()},
      error: () => {this.livre = null; this.cdr.detectChanges();}
    });
  }
}
