
## TPs R sur données State Facts
## M1 MIAGE - D. Chauveau
#############################################
####  STATISTIQUES DESCRIPTIVES AVEC R   ####
#############################################

#####################################
############### TP F1 ############### 
#####################################
# Importation sous forme de data.frame
# Win: Fichier -> Changer le répertoire courant
# -> path vers le fichier texte
getwd()
setwd('C:/Users/Nicolas/OneDrive/Documents/Cours/S7/Data/tp1')
# fichier dans le working dir de RStudio
states <- read.table("StateFacts.txt", 
                     header=T, row.names=1) # labels col. 1

# ou chemin complet vers le fichier
# states <- read.table(".../StateFacts_tuto.txt", 
#          header=T, row.names=1) # labels col. 1


head(states)
row.names(states)
colnames(states)
dim(states)  


summary(states) # Region is char by default
states$Region <- as.factor(states$Region)
summary(states) # Region is now factor: ok


# selection d'objets (comme une liste)
states[[1]]    # = var de rang 1 (Pop)
states$Pop     # idem car named list
states[[1]][1] # car states[[1]] vector
states[2,3]    # car hérite aussi de la class "matrix"




## 2.5 - comprendre attach(), detach()
states$Pop
Pop
attach(states)   # rend visible les variables de states
Pop
detach(states)
Pop


# 2.6 sauvegarde au format interne R
save(states, file="States.Rdata")


summary(states$Region) # method summary.factor
summary(states$Apb)    # method summary.numeric
summary(states)        # summary.data.frame
summary(states$Etat)   # why??



## 3) sur stat élémentaires de states 
attach(states)
mean(states$Revenu)
mean(Revenu)
sd(Meurtre)
sum(Revenu>5000) # nb d'états de revenu>5000
apply(states[,-c(1, 10)], 2, mean)
colnames(states)[4] <- "Apb"
sum(states$Pop^2)
summary(states) # vérif; method summary.data.frame

detach(states)

states$Region <- factor(states$Region)

# sauvegarde au format interne R (écrase la précédente)
save(states, file="States.Rdata")



### Utiliser en début de session (workspace vide)
load("States.Rdata")
attach(states)


# (2.8)
tapply(Meurtre, Region, mean)
# Est-ce significatif?... 
MbyR = tapply(Meurtre, Region, mean)
sdbyR = tapply(Meurtre, Region, sd)

## (6)
states[1:10,]
states[(nrow(states)-4):nrow(states),]
states[1:5, c(1, 6,10)]   # avec num col
states[1:5, c("Etat", "Meurtre", "Region")] # avec nom var
# d) selection d'obs et de var
states[Region == "South" & Revenu > 4500, c("Etat", "Pop")]




## (7) Création ou modification de variables
RevenuE <- Revenu * 1.1 # constante arbitraire ici
summary(RevenuE)


## copie de Region en labels conçis: R - like: pas de tests if... then
RG <- factor(Region,labels=c("NC","NE","S","W")) # 

## vérification (table see later)
table(RG)
table(Region)
table(RG, Region) # table de contingence de 2 faceurs identiques

## (7 c) Facteur par recodage d'une var quantitative 
?cut
cut(Diplome, breaks = 3) # auto 

par(mfrow=c(1,2)) # comparaison avec histo
hist(Diplome, col=8)
plot(cut(Diplome, 3)) # découpage auto en 3 classe de même taille

## classe imposées
FD <- cut(Diplome, breaks = c(min(Diplome),47,57, max(Diplome)),
          labels = c("a","b","c")) # specified
summary(FD) # why 1 NA ?
table(FD)   # 1 missing

FD <- cut(Diplome, breaks = c(min(Diplome),47,57, max(Diplome)),
          labels = c("a","b","c"), include.lowest = TRUE)  
summary(FD) # OK

# (7 d)
statesd <- data.frame(RevenuE, Meurtre, RG, FD, 
                      row.names = row.names(states))

summary(statesd)


## (8)
states8a <- states[FD == "b" & Region == "South",]
states8a  # data.frame?
class(states8a)

states8b <- states[Region == "North_Central" & Revenu > 4000,]
states8b

# ou comme RG est dans le workspace
states8b <- states[RG == "NC" & Revenu > 4000,]
states8b


# data frame pour Feuille 2
# avec les variables créées
statesF2 <- data.frame(states, FD, RG)
summary(statesF2)

save(statesF2, file = "StatesF2.Rdata")
