##################################
## Tutorial R ##
## M1 Miage
## Manipulation de base et langage
##################################

3+3
2+2 # calculette...

methods(summary)
?mean 
example(mean)

?"+"
?citation
citation()

install.packages("ade4")
## Charger un package peut definir de nouvelles methods
methods(plot)
library(ade4)
methods(plot)

example(dudi.pca)

?plot
# example(plot)




# gestion du workspace
objects() # liste des objects crees (who de matlab)
ls() # equivalent
ls(pattern="a") # objets contenant "a"
rm(list=ls()) # efface tous les objets crees


a <- c(1,2,4,6,7)
length(a)

c <- c("hyfh","ksdfhgks") # "c" fuction and R object

a <- rep(1,10)
b <- rep(c(10,12), 5)


1:10
seq(1,10)
seq(1,10, length=20)





####################################
## MANIPULATIONS DE VECTEURS
x <- vector(mode = "integer", length = 5)
is.vector(x)

e <- seq(1,20,by=2)
is.vector(e)

e[3]
e[c(3,8,1)]
e[c(1,3,7,8)][c(1,3)]

a <- c(rep(T,4),rep(F,2))

e>5
e[e>5]
e[e != 11]
e[e == 11]




########## Graphiques qq exemples
par(mfrom=c(1,1))
z <- seq(-4,4,len=200)
plot(z, dnorm(z), type="l", lwd=3, col="steelblue")
abline(h=0, col=8)
abline(v=0)
lines(z, dnorm(z, mean=2), lty=2)
lines(z, dnorm(z, sd=0.5), lty=3, col=2, lwd=2)
lines(z, dnorm(z, sd=0.1), lty=3, col=3, lwd=3)
title("densites de la loi normale")
legend("topleft",
       c("N(0,1)","N(2,1)","N(0,0.5)","N(0,0.1)"),
       lty=c(1,2,3,3),
       col=c("steelblue",1,2,3),
       lwd=c(3,1,2,3))




###############################
## Manipulation de MATRICES
a=1:10               # on reprend
?matrix              # methode par defaut?
m <- matrix(a,nrow=4) # avec recycling (warning)
dim(m)
m[,3]                # 3eme colonne de m
m[2,3]               # element m(2,3)
class(m)             # different de mode(m)
is.matrix(m)

p = matrix(a,ncol=2)  # par defaut byrow = FALSE
q = matrix(a,nrow=2)
q %*% p               # produit de matrices
q %*% m               # pb dimensions
diag(m)               # diagonale, meme si pas carree
t(m)                  # transpose
apply(m,1,sum)        # operation (sum) par ligne (1)




##################################
# exercice Tuto R sur les matrices
a <- sample(0:9, size=50, rep=TRUE)
M <- matrix(a, nrow=10, ncol=5); M
apply(M,2,mean)
apply(M,1,sd)


plot(M[,1], type="b") # valeurs de la colonne vs index
# 

par(mfrow=c(2,3))
apply(M, 2, plot, type="b") 
# usage de ... dans apply

# Usage des  fonctions dans apply
m <- matrix(rnorm(60), nrow=20)
apply(m, 2, median)
apply(m, 2, quantile)
apply(m, 2, quantile, probs=c(1/3, 2/3))
apply(M, 1, sd)



z <- seq(-4,4,len=200)
plot(z, dnorm(z), type="l", lwd=3, col="steelblue")



#############################
## MANIPULATION DE LISTES ##

ls <- list(1:5, "toto", c(T,F), M) # sans nom
ln <- list(x=1:5,nom="toto",z=c(T,F), M=M) # named

summary(ln) # default method
ls[[1]]     # extraction element 1 de la liste
ln$x        # idem lorsque le nom est connu

ls[[1]][2:3]
ln$M[1:2,2:4]

names(ln)
#




################
## DATA FRAME ##
?data.frame
a=sample(1:10); b=rep(c(1,2),5)
c <- c(rep("M",6),rep("F",4)) # facteur qualitatif
c <- factor(c)
x <- data.frame(a,b,c) # creation de la structure

x
colnames(x)
row.names(x)[1] <- "toto"


x$a
x[[3]]
class(x$a)


x$fumeur <- as.factor(x$fumeur)


class(x)                     # = data.frame
class(c)
class(x$c)         # conversion par defaut suivant version R
names(x)     # noms des colonnes = variables
row.names(x) # noms des lignes = individus
x; x$a       # affichage de x et de la variable a
x[[1]]       # idem car c'est une liste
x$a[2]       # extraction = x[[1]][2] = x[1,2] 

y <- data.frame(a,b,c, row.names=1)
y












####################################
## FONCTIONS, MeTHODES ET CLASSES ##

class(a); summary(a) # a est "numeric" 




class(c); summary(c) # c est "character" 
summary(x)           # stat descriptives elementaires
methods(summary)     # methodes associees

plot(a)              # basic: serie des obs
plot(a, type="b")

# nuage de points
x <- rnorm(100); y <- x + rnorm(100)
plot(x,y, pch=20)





## Exemple objet cree par un modele
reg <- lm(y ~ x)
summary(reg)
abline(reg) # method pour la classe "lm"
plot(reg)



### Grand 2 

## 1


## 2

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

# fichier dans le working dir de RStudio
states = read.table("C:/Users/Nicolas/OneDrive/Documents/Cours/S7/Data/tp1/StateFacts.txt", header=T, row.names = 1)
# labels col. 1

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














