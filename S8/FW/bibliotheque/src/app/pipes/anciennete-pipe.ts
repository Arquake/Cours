import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'anciennete',
  standalone: true
})
export class AnciennetePipe implements PipeTransform {
  transform(anneePublication: number | string): string {
    if (!anneePublication) return '';

    const anneeActuelle = new Date().getFullYear();
    const difference = anneeActuelle - Number(anneePublication);

    if (difference <= 0) {
      return "Publié cette année";
    } else if (difference === 1) {
      return "Publié il y a 1 an";
    } else {
      return `Publié il y a ${difference} ans`;
    }
  }
}
