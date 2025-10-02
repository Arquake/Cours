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

# recrée FD cf TP Feuille 1 
FD <- cut(Diplome, breaks = c(min(Diplome),47,57, max(Diplome)),
          labels = c("a","b","c"), include.lowest = TRUE)  
table(FD) 

#########################################
##### 3. VARIABLES QUANTITATIVES

## Analyses préliminaires
## (1) NA?
ind <- c(2:9) # indices des var quantitatives
summary(states[,ind]) ## min, max, NA's...

## ou direct
sum(is.na(states)) # global
apply(is.na(states), 2, sum) # NA par col


## (2) ajouter un NA et le retrouver
states[12,2] <- NA

summary(states[,ind]) ## => see NA's...
apply(is.na(states), 2, sum) # par col
which(is.na(states), arr.ind = TRUE) # which row ?


states[is.na(Pop),] # none?
detach(states)
attach(states)
states[is.na(Pop),] # effect of attach...


## RESTORE data.frame
detach(states)
load("States.Rdata")
attach(states)



# (3) Histogramme = loi empirique approche la densité
par(mfrow=c(1,2))
hist(Meurtre, col=8)
hist(Apb, col=8)

hist(Meurtre, col=8, freq=F) # normalisé sur la densité
hist(Meurtre, col=2, freq=F, # comparé avec autre algo
     breaks="Scott")          # de calcul nb de classes

# avec apply: pb titre pas adapté
colnames(states) # numeric?
ind <- c(2:9)
par(mfrow=c(2,4))  #  subplots
apply(states[,ind], 2, hist, col=8)



# (4) Exemple de boucle for () {...} en R
par(mfrow=c(2,4))  # 
for (j in ind) {
  hist(states[,j], xlab="",
       main=colnames(states)[j])
}


head(states)
summary(Revenu)
## (5) recherche d'un extrême = tri dans un data.frame
# tri de la table par Revenu
o <- order(Revenu)
states[o,][40:50,] 	# les 10 individus  correspondants aux
# plus grandes valeurs de Revenu
#

colnames(states) # numeric?
ind <- c(2:9)
par(mfrow=c(2,4))
for (j in ind) {
  boxplot(states[,j], main=colnames(states)[j])
}
