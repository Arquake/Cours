# -*- coding: utf-8 -*- 

from utils import connectSocket, closeSocket, sendcmd, recvcmd
from utils import getWeight, getNeighbors, getAVertex, Cookie, SpanningTreeWeight


# Pour la file de priorité de Prim
from priority_dict import priority_dict

# The union-find data-structure is used by Kruskal algorithm
from UnionFind import UnionFind




#---------------------------------------------------------------------------------------------------
def ACM_Prim():
	""" calcule un ACM du graphe stocké sur le serveur en utilisant l'algorithme de Prim """
	F = {}

	v = getAVertex
	n = getNeighbors[v]
	explored = [v]
	toExplore = [n]
	F[v] = n
	while toExplore != [] :
		print(toExplore,'\n',explored)
		v = toExplore.pop(0)
		n = getNeighbors(n)
		explored += [v]
		for i in n :
			if i not in toExplore and i not in explored :
				toExplore += [i]
	print(explored)



#---------------------------------------------------------------------------------------------------





#---------------------------------------------------------------------------------------------------
def ACM_Kruskal(G):
	""" calcule un ACM du graphe G en utilisant l'algorithme de Kruskal"""
	pass

#---------------------------------------------------------------------------------------------------




if __name__ == "__main__":
	""" Il n'y a rien à modifier ci-dessous, excepté l'appel à ACM_Prim() ou à
	ACM_Kruskal(G) en définissant le graphe au préalable """
	
	# connexion au serveur
	#connectSocket("192.168.47.162", 6667)
	#connectSocket("uomobile.univ-orleans.fr", 6667)
	connectSocket("distrigraphes.info-orleans.fr", 8080)

	ACM_Prim() # peut être mis en commentaire si nécessaire
	
	sendcmd('stop','')
	closeSocket()
	
	G = {} # graphe vide
	ACM_Kruskal(G)
	