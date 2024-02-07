# coding: utf-8

import tkinter as tk
from threading import Event, Thread
from GUI import MotionPlanning
from priority_dict import priority_dict
import pickle


def creerObstaclesEtGraphesVisibilite(w, h, file=None):
    """Cette fonction retourne une liste de polygones, où chaque polygone
    est représenté par une liste de coordonnées (x,y) de ses sommets,
    où 0<=x<=w et 0<=y<=h.
    Exemple: [[(Ax1,Ay1), (Ax2,Ay2), ..., (Axk,Ayk)], [(Bx1,By1), (Bx2,By2), ..., (Bxt,Byt)], ...]
    La fonction retourne aussi la position initiale du robot (x,y) et la cible à atteindre (x,y).
    La fonction retourne le graphe de visibilité pondéré.
    """
    with open(file, "rb") as fichier:
        (polygons, positionRobot, positionSortie, G) = pickle.load(fichier)

    return polygons, positionRobot, positionSortie, G


def chemin(gui, G, posInit, posFinal):
    """cette fonction prend en entrée l'interface graphique gui;
    le graphe de visibilité G, le sommet de départ du robot (posInit)
    et le sommet d'arrivée posFinal"""

    # Si vous souhaitez cacher les arêtes du graphes, supprimez les 3 lignes suivantes (ou mettez les en commentaires)
    #for u in G:
    #    for v in G[u]:
    #        gui.drawLine(u, v)

    # Commencer par programmer l'algorithme de Dijkstra
    # puis faire parcourir le plus court chemin par Oscar en utilisant la fonction gui.deplacer_oscar(v)

    ######################################################################################
    # à vous de jouer ci-dessous ! Vous pouvez programmer d'autres fonctions si besoin   #
    ######################################################################################
    
    
    #explored = []
    #explore = [posInit]
    #rapidite = {}
    #rapidite[posInit] = ([],0)
    #i = 0
    #
    #while len(explore) != i :
    #    for element in G[explore[-1]] :
    #        distanceY = max(explore[-1][1],element[1]) - min(explore[-1][1],element[1])
    #        distanceX = max(explore[-1][0],element[0]) - min(explore[-1][0],element[0])
    #        sumDistance = distanceX**2 + distanceY**2
    #        if rapidite.get(element) :
    #            # si le poids de l'élément est inférieur au poids avec l'ajout du chemin actuel
    #            
    #            if rapidite[element][1] > rapidite[explore[-1]][1] + sumDistance :
    #                rapidite[element] = (rapidite[explore[-1]][0]+[explore[-1]], rapidite[explore[-1]][1]+sumDistance)
    #        else :
    #            rapidite[element] = (rapidite[explore[-1]][0]+[explore[-1]], rapidite[explore[-1]][1]+sumDistance)
    #            explore
        
    d = {}
    pi = {}
    for u in G:
        d[u]='inf'
        pi[u]=None
    d[posInit] = 0

    explored = [posInit]

    i = 0 
    while i < len(explored) :
        toExplore = None
        for u in d.keys():
            if u not in explored and d[u] != 'inf' :
                if toExplore == None : 
                    toExplore = u
                if d[u] < d[toExplore] :
                    toExplore = u

        if toExplore == None : break

        

    





if __name__ == "__main__":
    # RIEN A MODIFIER ICI (excepté pour choisir la scene: scene1.pickle OU scene2.pickle)
    root = tk.Tk()
    width, height = 800, 600
    polygons, posInit, posFinal, graphe = creerObstaclesEtGraphesVisibilite(
        width, height, "scene1.pickle"
    )
    gui = MotionPlanning(root, width, height, polygons, posInit, posFinal)

    # Launch a Thread running the main program to move the robot
    def runwrapper():
        chemin(gui, graphe, posInit, posFinal)

    th = Thread(target=runwrapper)
    th.daemon = True
    root.after(100, th.start)

    root.mainloop()
