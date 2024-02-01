# coding: utf-8

from GUI import GUI
import json
import random


#def creer_grille(l,c):
#	""" Cette fonction crée une grille aléatoire de taille l par c
#	contenant des murs placés aléatoirement
#	"""
#	with open("grille.json", "r", encoding="utf-8") as fichier:
#		(grille, p) = json.load(fichier)
#	return grille, (p[0],p[1])


def creer_grille(l,c):
	""" Cette fonction crée une grille aléatoire de taille l par c
	contenant des murs placés aléatoirement
	"""
	grille = []
	p = (0,0)
	cookieIsSet = False

	for i in range( l ) :
		grille += [[]]
		for j in range( c ):
			if i == 0 or j == 0 or i == l-1 or j == c-1 : grille[i] += ['wall']
			else : 
				rand = random.randint(1,5)
				if rand <= 4 : grille[i] += ['grass']
				else : grille[i] += ['wall']


	p=(0,0)
	return grille, (p[0],p[1])


def voisins(G, pos):
	listPossiblePos = []
	for i in [-1,1] :
		if G[pos[0]+i][pos[1]] != 'wall' :
			listPossiblePos += [(pos[0]+i,pos[1])]
		if G[pos[0]][pos[1]+i] != 'wall' :
			listPossiblePos += [(pos[0],pos[1]+i)]
	return listPossiblePos



def ParcoursRobot(G, depart, gui):
	chemin = [depart]
	explored = [depart]


	while chemin != [] :
		hasFound = False
		print(chemin)
		for element in voisins(G, chemin[-1]):
			if element not in explored :
				explored += [element]
				GUI.deplacer_oscar(GUI, chemin, [element])
				if G[element[0]][element[1]] == 'cookie' : 
					chemin += [element]
					return chemin
				hasFound = True
				chemin += [element]
				break
		if not hasFound :
			chemin.pop()
		

		


if __name__ == '__main__':
	grille, pos_robot = creer_grille(12,20)
	GUI(grille, pos_robot, ParcoursRobot)
