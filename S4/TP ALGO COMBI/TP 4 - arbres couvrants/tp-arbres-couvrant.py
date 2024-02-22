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
	F = priority_dict()
	pi = {}
	cle = {}

	v = getAVertex()
	toExplore = [v]

	while toExplore != [] :
		for element in getNeighbors(toExplore[0]) :
			if element not in F and element not in toExplore  :
				toExplore += [element]
		F[toExplore[0]] = float('inf')
		cle[toExplore[0]] = float('inf')
		toExplore.pop(0)
	
	F[v] = 0
	pi[v] = None

	while F != {} :
		u = F.pop_smallest()
		for v in getNeighbors(u) : 
			weigth = getWeight(u,v)
			if v in F and ( F[v] == float('inf') or weigth < cle[v] ) :
				pi[v] = u
				cle[v] = weigth
		print(F)

	print(cle,pi)


#---------------------------------------------------------------------------------------------------





#---------------------------------------------------------------------------------------------------
def ACM_Kruskal(G):
	""" calcule un ACM du graphe G en utilisant l'algorithme de Kruskal"""
	A = UnionFind()
	
	for element in G.keys() :
		A[element]
	G['E'].sort()
	for element in getWeight(u,v) in G['E'] :
		if A[u] != A[v] :
			A = A.union(u,v)
			union(u,v)
	return A

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
	