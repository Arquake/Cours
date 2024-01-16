# coding: utf-8

from GUI import GUI
import json
import random


def creer_grille(l,c):
	""" Cette fonction crée une grille aléatoire de taille l par c
	contenant des murs placés aléatoirement
	"""
	with open("grille.json", "r", encoding="utf-8") as fichier:
		(grille, p) = json.load(fichier)
	return grille, (p[0],p[1])


def voisins(G, pos):
	pass


def ParcoursRobot(G, depart, gui):
	pass


if __name__ == '__main__':
	grille, pos_robot = creer_grille(12,20)
	GUI(grille, pos_robot, ParcoursRobot)
