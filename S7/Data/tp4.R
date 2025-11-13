#################################################
## TP R M1 MIAGE - Didier Chauveau
#################################################
## TP F4 - AFC - code de base corrigé
## Exemple data Cours: Couleurs Yeux-Cheveux
#################################################

## (0) Nettoyage de l'environnement
rm(list = ls())

#################################################
## (1) Import des données + conversion en "table"
#################################################

## On lit la table de contingence :
## - header = TRUE : première ligne = noms de colonnes (couleur des cheveux)
## - row.names = 1 : première colonne = noms des lignes (couleur des yeux)
setwd("C:\\Users\\Nicolas\\OneDrive\\Documents\\Cours\\S7\\Data")
dfYC <- read.table("couleursYeuxCheveux.txt",
                   header = TRUE,
                   row.names = 1)

class(dfYC)   # data.frame
dfYC
str(dfYC)     # colonnes doivent être "numeric"

## Conversion en objet "table" (2 facteurs croisés)
tYC <- as.table(as.matrix(dfYC))
class(tYC)    # "table"
tYC

#################################################
## (2) Profils ligne / colonne, histogrammes,
##     test d’indépendance
#################################################

# Nombre total d'individus
n <- sum(tYC)

# Profils ligne : P(Cheveux | Yeux) (proportions conditionnelles par ligne)
profL <- prop.table(tYC, 1)  # margin = 1 -> lignes
profL

# Profils colonne : P(Yeux | Cheveux) (proportions conditionnelles par colonne)
profC <- prop.table(tYC, 2)  # margin = 2 -> colonnes
profC

# Marges (distributions simples)
margeYeux    <- margin.table(tYC, 1) / n
margeCheveux <- margin.table(tYC, 2) / n
margeYeux
margeCheveux

# Histogrammes (barplots) des distributions marginales
par(mfrow = c(1, 2))
barplot(margeYeux,
        main = "Distribution des couleurs d'yeux",
        ylab = "Proportion", xlab = "Yeux")
barplot(margeCheveux,
        main = "Distribution des couleurs de cheveux",
        ylab = "Proportion", xlab = "Cheveux")
par(mfrow = c(1, 1))

# Test du χ² d'indépendance
test.chi <- chisq.test(tYC)
test.chi
test.chi$statistic  # valeur de la statistique χ²
test.chi$p.value    # p-valeur

## Interprétation (à compléter dans ton compte-rendu) :
## - p-valeur très petite -> rejet de l'hypothèse d'indépendance
## - les couleurs des yeux et des cheveux sont associées

#################################################
## (3) AFC avec ade4 + décomposition de l’inertie
#################################################

library(ade4)
?dudi.coa

## On peut passer directement la table de contingence sous forme data.frame
coaYC <- dudi.coa(dfYC, scannf = FALSE, nf = 4)  # nf = nb d’axes conservés
coaYC
summary(coaYC)   # contient déjà la décomposition de l'inertie

## Décomposition détaillée de l’inertie
inertYC <- inertia.dudi(coaYC)
inertYC

## inertYC$tab : valeurs propres
## inertYC$tot.inertia : inertie totale
## inertYC$prop : proportions d'inertie par axe, etc.

#################################################
## (4) Vérification : Itot = χ² / n
#################################################

# 1) Statistique χ² du test d’indépendance
chi2 <- as.numeric(test.chi$statistic)

# 2) Inertie totale du nuage en AFC = somme des valeurs propres
Itot <- sum(coaYC$eig)

# 3) Comparaison avec χ² / n
chi2_over_n <- chi2 / n

chi2
Itot
chi2_over_n
all.equal(Itot, chi2_over_n)  # doit renvoyer TRUE (ou quasi)

#################################################
## (5) scatter.coa : quelles méthodes ?
#################################################

## Tu peux lancer les trois pour voir :
# scatter.coa(coaYC, method = 1)
# scatter.coa(coaYC, method = 2)
# scatter.coa(coaYC, method = 3)

## Rappel théorique (à noter dans ton compte-rendu) :
## - method = 1 : carte symétrique (lignes et colonnes en coordonnées principales)
## - method = 2 : colonnes en variance 1 (coord. principales), lignes comme barycentres
## - method = 3 : lignes en variance 1 (coord. principales), colonnes comme barycentres

#################################################
## (6) Représentation simultanée perso
##     - Lignes / colonnes colorées
##     - Tailles proportionnelles aux cos²
#################################################

plot_afc_cos2 <- function(coa.obj, axes = c(1, 2)) {
  ax1 <- axes[1]
  ax2 <- axes[2]
  
  ## Coordonnées factorielles
  li <- coa.obj$li[, c(ax1, ax2), drop = FALSE]  # lignes (yeux)
  co <- coa.obj$co[, c(ax1, ax2), drop = FALSE]  # colonnes (cheveux)
  
  ## Inerties + cos² (qualité de représentation sur chaque axe)
  inert <- inertia.dudi(coa.obj,
                        row.inertia = TRUE,
                        col.inertia = TRUE)
  
  ## row.rel / col.rel : cos² par axe (rel. contributions)
  row_cos2_axes <- inert$row.rel[, c(ax1, ax2), drop = FALSE]
  col_cos2_axes <- inert$col.rel[, c(ax1, ax2), drop = FALSE]
  
  ## cos² sur le plan (somme des cos² sur les 2 axes)
  row_cos2_plane <- rowSums(row_cos2_axes)
  col_cos2_plane <- colSums(col_cos2_axes)
  
  ## Échelles du graphique
  xrange <- range(li[, 1], co[, 1])
  yrange <- range(li[, 2], co[, 2])
  
  ## Taille des points proportionnelle au cos² (normalisée)
  cex_row <- 0.8 + 3 * row_cos2_plane / max(row_cos2_plane)
  cex_col <- 0.8 + 3 * col_cos2_plane / max(col_cos2_plane)
  
  ## Pourcentage d'inertie expliqué par chaque axe
  perc1 <- round(100 * coa.obj$eig[ax1] / sum(coa.obj$eig), 1)
  perc2 <- round(100 * coa.obj$eig[ax2] / sum(coa.obj$eig), 1)
  
  ## Plot vide
  plot(xrange, yrange, type = "n",
       xlab = paste0("Axe ", ax1, " (", perc1, " % d'inertie)"),
       ylab = paste0("Axe ", ax2, " (", perc2, " % d'inertie)"),
       main = "AFC Yeux/Cheveux – points ∝ cos²")
  abline(h = 0, v = 0, lty = 3)
  
  ## Lignes (yeux) en bleu
  points(li[, 1], li[, 2],
         pch = 19, col = "steelblue", cex = cex_row)
  text(li[, 1], li[, 2],
       labels = rownames(li),
       pos = 3, col = "steelblue4")
  
  ## Colonnes (cheveux) en rouge
  points(co[, 1], co[, 2],
         pch = 17, col = "tomato", cex = cex_col)
  text(co[, 1], co[, 2],
       labels = rownames(co),
       pos = 3, col = "tomato4")
  
  legend("topright",
         legend = c("Yeux (lignes)", "Cheveux (colonnes)"),
         pch = c(19, 17),
         col  = c("steelblue", "tomato"),
         bty  = "n")
}

## Appel de la fonction pour tracer la représentation simultanée
plot_afc_cos2(coaYC, axes = c(1, 2))

#################################################
## Fin du TP
#################################################

