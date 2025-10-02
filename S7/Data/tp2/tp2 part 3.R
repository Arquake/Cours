## TPs R sur données State Facts
## M1 MIAGE - D. Chauveau
#############################################
####  STATISTIQUES DESCRIPTIVES AVEC R   ####
#############################################

#####################################
############### TP F2 ############### 
#####################################

### DEBUT DE SEANCE: RELOAD
load("States.Rdata")
attach(states)
summary(states) # Etat doit etre char et Region "factor"

#############################################
##### 4. CROISEMENT QUALITATIF - QUANTITATIF

## (1) barplot avec stat d'une autre variable

## avec labels Region compact cf  Feuille 1
RG <- factor(Region,labels=c("NC","NE","S","W")) 
table(RG, Region) # vérification


par(mfrow=c(2,2)) # all in one
dbar <- tapply(Diplome, RG, mean) # aplliquer la fonction sur Diplome selon les Regions
barplot(dbar, names=levels(RG), main = "Moyenne de Diplome/Region")
dsd <- tapply(Diplome, Region, sd)
barplot(dsd, main = "sd de Diplome/Region")

cbar <- tapply(Meurtre, RG, mean)
barplot(cbar, main = "Moyenne de Crime/Region")
csd <- tapply(Meurtre, Region, sd)
barplot(dsd, main = "sd de Crime/Region")

mean(states[RG=="NC", 7 ])


# Boxplots par modalités d'un facteur
## (4)  toutes les numériques
par(mfrow=c(2,4))  
for (j in ind) {
  boxplot(states[,j] ~ RG, xlab="",
          main=colnames(states)[j])
}


# avec plusieur facteurs:
# recrée FD cf TP Feuille 1 
# recodage en quantiles
# avec labels numériques par défaut
# x = variable quantitative à recoder
#	k = nb de classes, 2 par défaut
#	labels = noms des modalités
recodeQ <- function(x, k = 2, labels = 1:k) {
  q <- quantile(x, probs = seq(0,1,1/k))
  f <- cut(x, breaks = q, labels = labels, include = TRUE)
  return(f)
}

FD <- recodeQ(Diplome, 2, labels=c("faible","fort"))
table(FD) 

boxplot(Meurtre ~ RG+FD)
## interpréter...

