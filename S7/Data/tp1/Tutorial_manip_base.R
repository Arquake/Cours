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

# Usage des  ...
m <- matrix(rnorm(60), nrow=20)
apply(m, 2, median)
apply(m, 2, quantile)
apply(m, 2, quantile, probs=c(1/3, 2/3))
apply(M, 1, sd)







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


