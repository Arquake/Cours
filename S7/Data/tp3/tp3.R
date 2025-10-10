## TPs R M1 MIAGE - Didier Chauveau
#############################################
## TP F3 - Analyse en Composantes Principales
#############################################
## ACP donnees Menages

# (1) DONNEES
setwd("C:/Users/Nicolas/OneDrive/Documents/Cours/S7/Data/tp3")
men <- read.table("stats.txt", header=TRUE, row.names = 1)

head(men)

# separation des labels en CSP et nbEnfants (NE) 
CSP <- factor(substr(row.names(men), start = 1, stop = 2))
## creation facteur NE
NE <- factor(substr(row.names(men), start = 3, stop = 3))

table(CSP)
table(NE)
table(CSP, NE) # 1 de chaque, plan d'experience...

men2 <- data.frame(CSP, NE, men)
head(men2) # ok, then save
save(men2, file = "Menages.Rdata")



###########################
# (2) ETUDE   PRELIMINAIRE
# toujours commencer par
summary(men2) # vérifier classes, min , max, NA's...

# cf TP F2 
par(mfrow=c(2,4))  
for (j in 3:9) {boxplot(men2[,j], col=8, xlab="", main=colnames(men2)[j])
}

x11()
# mieux ici car même unité = comparable
par(mfrow = c(1,1))
boxplot(men2[, -(1:2)])


par(mfrow=c(2,4))  
for (j in 3:9) {
  hist(men2[,j], xlab="",
       main=colnames(men2)[j])
}
## => INTERPRETER (mais ici n petit)





## (3) pour l'ACP: Choix de la métrique?
print(apply(men2[, 3:9], 2, var), 3)
print(apply(men2[, 3:9], 2, sd), 3)
# ici tout en Franc, et écart ~ 5x, on peut essayer les 2 métriques 

sdmen <- apply(men2[, 3:9], 2, sd)
max(sdmen)/min(sdmen)


print( cor(men2[, 3:9]), 3)
# cor maximale (Viande, Volaille) = +98%
## => INTERPRETER



###########################
## (4) ACP avec package ade4
library(ade4)
?dudi.pca # check Value = object returned
# scale = TRUE : ACP normée (default)
# scale = FALSE: ACP non normée = distance Euclidienne usuelle
acp <- dudi.pca(men2[,3:9], scale=FALSE, scannf=F, nf=3) # ACP non normée

# On commence par l'ACP avec la distance usuelle : on garde l'unité (Franc)

class(acp)
names(acp)
head(acp$li) # Principal Components's, 3 premiers axes
acp$eig

## (5)
?inertia.dudi
Imen <- inertia.dudi(acp, col.inertia=F, row.inertia=T)
names(Imen)
Imen$tot.inertia # val propres et % inertie cumulés (ratio)
# => cf Cours, sorties standard d'une ACP

# NB: version < 1.7-16 de ade4 l'inertie s'obtient par $TOT


par(mfrow=c(1,2)) 
# eigen bargraph Eboulis des valeurs propres
scatterutil.eigen(acp$eig,nf=3,box=T,sub="")
# ou direct
barplot(acp$eig, names.arg = 1:7) # p =  7 ici

## => INTERPRETER




### (6) CERCLES DE CORRELATIONS
?s.corcircle # see example code
s.corcircle(acp$co) # ATTENTION: faux dans le cas ACP NON NORME
# dans le cas ACP non normee, $co = covariances

## (6) donc calcul des correlations anciens (X), nouveaux (Psi):
?cor
cc <- cor(men2[,3:9], acp$li)
cc

par(mfrow=c(1,2)) # avoir les 2 premiers  cercle sur le même plot
## Cercle principal (adapter clabel taille des noms de var)
s.corcircle(cc, clabel=0.6, sub="Cercle de corrélations") 
title("ACP non normée: cercle 1-2")
# next... si besoin
s.corcircle(cc, yax = 3, clabel=0.6) 
title("ACP non normée: cercle 1-3")
## => INTERPRETER



### (7) PLANS FACTORIELS INDIVIDUS
# plan principal avec CSP en facteur supplémentaire
#    labels individus colorés par CSP
#    coloration par CSP
#    barycentres/classes avec s.class
# cf TP2  
# la fonction s.class ne donne pas les labels individus
par(mfrow=c(1,1))
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, row.names(men2), 
     col = as.numeric(CSP))
abline(h=0); abline(v=0) # barycentre du nuage
## barycentres/calsses avec s.class
colCSP <- seq(1,length(levels(CSP)))
s.class(acp$li,fac=CSP, cstar=0,cpoint=0,clabel=1.4,
        col=colCSP, axesell=FALSE, add.plot=TRUE)
title("Plan principal ACP non normée")

## => INTERPRETER


## analyse par NE - ajout sur le même plot des barycentres/NE
s.class(acp$li,fac=NE, cstar=0,cpoint=0,clabel=1.4,
        col=c(1,1,1,1), axesell=FALSE, add.plot=TRUE)

## => INTERPRETER


###########################
## (8) Usage des cos^2 et représentation

## Cos^2 ET CUMULES
Imen$row.cum # cos2 cumulés (%)
# dans le Plan Principal: colonne Axis1:2
cos2 <- Imen$row.cum[,2] # cos2 cumulés plan princ. en % (ade4 last versions)
cos2lab <- 1.*cos2/100 # label sizes, tune here!

### PLOTS ###
par(mfrow=c(2,2)) # avoir les 4 plots utiles sur le même grahique
barplot(acp$eig, names.arg = 1:7) # p =  7 ici
title("ACP non normée")
s.corcircle(cc, clabel=0.8) 
title("Cercle de corrélations 1-2")
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, cex=cos2lab,
     labels=row.names(men2), col=as.numeric(CSP))
s.class(acp$li, CSP, cstar=0, cpoint=0, clabel=1.5,
        col=colCSP, axesell=F, add.plot=T)
abline(h=0, col=8); abline(v=0, col=8)
title("Plan principal + CSP avec label prop. aux cos2")

plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, cex=cos2lab,
     labels=row.names(men2), col=as.numeric(CSP))
colNE <- seq(1,length(levels(NE)))
s.class(acp$li, NE, cstar=0, cpoint=0, clabel=1.5,
        col=colNE, axesell=F, add.plot=T)
abline(h=0, col=8); abline(v=0, col=8)
title("Plan principal + NE avec label prop. aux cos2")

## => INTERPRETER


### (9) Affichage des individus les moins bien représentés
o <- order(cos2)
mencos2 <- data.frame(CSP, NE, cos2)
row.names(mencos2) <- row.names(men2)
mencos2[o,] [1:5,] # les 5 individus moins bien représentés









##################### 
#### Essai ACP Normée : scale=TRUE
acp <- dudi.pca(men2[,3:9], scale=TRUE, scannf=F, nf=3) # ACP normée

Imen <- inertia.dudi(acp, col.inertia=F, row.inertia=TRUE)
round(Imen$tot.inertia,2)  # val propres et % inertie cumulés (ratio)

par(mfrow=c(2,2)) # 4 graphiques sur le même plot
# eigen bargraph Eboulis des valeurs propres
scatterutil.eigen(acp$eig,nf=3,box=T,sub="ACP Normée")

## (6) calcul des correlations anciens (X), nouveaux (Psi):
s.corcircle(acp$co) #
title("ACP normée")
# plan principal avec CSP en facteur supplémentaire
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, row.names(men2), 
     col = as.numeric(CSP))
abline(h=0); abline(v=0)
## barycentres/classes avec s.class
colCSP <- seq(1,length(levels(CSP)))
s.class(acp$li,fac=CSP, cstar=0,cpoint=0,clabel=1.4,
        col=colCSP, axesell=FALSE, add.plot=TRUE)

## analyse par NE - ajout sur le même plot des barycentres/NE
colNE <- seq(1,length(levels(NE)))
# s.class(acpmen$li, nE,cstar=0,cpoint=1,clabel=0.8,col=colnE,axesell=FALSE)
# barycentres NE superposés sur le plan principal (add.plot=T)
s.class(acp$li, fac=NE, cstar=0, cpoint=0,clabel=1.7,
        cellipse=0, col=colNE, axesell=FALSE, add.plot=TRUE)

## => INTERPRETER ET VOIR LA DIFFERENCE AVEC ACP NON NORMEE ICI

#### END ####

