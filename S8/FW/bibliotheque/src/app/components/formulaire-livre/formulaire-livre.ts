import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from
'@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { LivreService } from '../../services/livre';
import { Livre } from '../../models/livre.model';

@Component({
  selector: 'app-formulaire-livre',
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './formulaire-livre.html',
  styleUrl: './formulaire-livre.css',
})
export class FormulaireLivre implements OnInit {
  livreForm!: FormGroup;
  isModification: boolean = false;
  livreId: number | null = null;
  constructor(
    private fb: FormBuilder,
    private livreService: LivreService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.livreForm = this.fb.group({
      titre: ['', [Validators.required, Validators.minLength(2)]],
      auteur: ['', [Validators.required, Validators.minLength(3)]],
      annee: ['', [Validators.required, Validators.min(1800),
      Validators.max(2030)]],
      disponible: [true]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isModification = true;
      this.livreId = Number(id);
      this.livreService.getLivreById(this.livreId).subscribe(livre => {
        this.livreForm.patchValue(livre);
      });
    }
  }

  onSubmit(): void {
    if (this.livreForm.invalid) return;
      const livre: Livre = this.livreForm.value;
    if (this.isModification && this.livreId) {
      this.livreService.modifierLivre(this.livreId, livre).subscribe(() => {
        this.router.navigate(['/livres']);
      });
      } else {
        this.livreService.ajouterLivre(livre).subscribe(() => {
        this.router.navigate(['/livres']);
      });
    }
  }
}
