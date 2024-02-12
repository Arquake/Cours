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
    
    def minMax (x,y) :
        h = max(x[0],y[0]) - min(x[0],y[0])
        v = max(x[1],y[1]) - min(x[1],y[1])
        return h**2 + v**2

    d = {}
    pi = {}
    for u in G:
        d[u]=9999999999999999
        pi[u]=None
    d[posInit] = 0

    X = priority_dict()
    F = priority_dict()

    for i in G:
        l= []
        for j in G[u]:
            l += [j]
        F[i] = l


    while True:
        try :
            u = F.pop_smallest()
        except :
            break
        X[u] = u
        

        for v in G[u].keys() :
            w = minMax(v,u)
            if type(d[v]) == str or d[v] > d[u] + w  :
                d[v] = d[u] + w
                pi[v] = u
                

    pos = posFinal
    prevPos = pi[pos]
    
    while prevPos != None :
        gui.drawLine(pos, prevPos)
        pos = prevPos
        prevPos = pi[pos]

    

def cheminBell(gui, G, posInit, posFinal):
    
    def minMax (x,y) :
        h = max(x[0],y[0]) - min(x[0],y[0])
        v = max(x[1],y[1]) - min(x[1],y[1])
        return h**2 + v**2

    d = {}
    pi = {}
    for u in G:
        d[u]=9999999999999999
        pi[u]=None
    d[posInit] = 0

    F = priority_dict()

    for i in G:
        l= []
        for j in G[u]:
            l += [j]
        F[i] = l

    for i in range(1, len(G)-1):
        for j in G.keys() :
            for k in G[j] :
                w = minMax(j,k)
                print(d[j])
                if type(d[j]) == str or d[j] > d[k] + w  :
                    d[j] = d[k] + w
                    pi[j] = k
                

    pos = posFinal
    prevPos = pi[pos]
    
    while prevPos != None :
        gui.drawLine(pos, prevPos)
        pos = prevPos
        prevPos = pi[pos]



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
        cheminBell(gui, graphe, posInit, posFinal)

    th = Thread(target=runwrapper)
    th.daemon = True
    root.after(100, th.start)

    root.mainloop()
