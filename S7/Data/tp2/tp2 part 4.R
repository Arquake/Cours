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

## labels Region compact cf  Feuille 1
RG <- factor(Region,labels=c("NC","NE","S","W")) 
table(RG, Region) # vérification


###################################################
##### 5. GRAPHIQUES SCATTERPLOTS - NUAGES DE POINTS

# qq methodes de plot() dépend de la classe de l'argument
par(mfrow=c(2,3))
plot(Revenu)             # X(i) contre indices i=1,...
plot(Revenu, type="l")  
plot(Revenu, type="b")  # type=both

plot(Apb,Meurtre) # nuage de points (scatterplot)
par(mfrow=c(1,1))
plot(states[,ind]) # method plot.data.frame sans les factors

# Quelques options
plot(Apb,Meurtre, pch=3,              # type de point
     xlab="Taux d'analphabétisme",   	# label axe x
     ylab="Taux de criminalité",      # label axe y
     main="nuage de points (scatterplot)")  # titre
title(sub="sous-titre")	

# 3. avec labels individus colored par Region
plot(Apb,Meurtre,type="n") # points "invisibles"
text(Apb,Meurtre,row.names(states), col=as.numeric(Region))  
legend("bottomright", legend=levels(Region), fill=1:4)




## (4) advanced: using ade4 R package
library(ade4)      # package D. Chessel Lyon I

par(mfrow=c(1,1)) # unsplit graphic window 
plot(Apb,Meurtre,type="n", xlab="Taux d'analphabétisme", 
     ylab="Taux de criminalité") # cadre
text(Apb,Meurtre,row.names(states), col=as.numeric(Region))
legend("bottomright", levels(Region), col=1:4, pch=15)
# ajout barycentres/classe et ellipse d'inertie
s.class(data.frame(Apb,Meurtre),Region, 
        add.plot=T,  	# ajoute au graphique existant
        col=1:4,	    # colorie barycentres/Region
        cstar=0,			# supprime les "étoiles"
        cpoint=0,			# supprime les symboles de point
        clabel=0.6,		# taille labels barycentres
        axesell=F)		# supprime les axes des ellipoides




### p > 2 variables quantitatives

# 6 (1) corrélations des var quantitatives
cor(states[,ind])

print(cor(states[,ind]) , 2) # better readability

cor(Meurtre, states[,ind]) # Meurtre vs the others

####### END ######