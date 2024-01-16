# -*- coding: utf-8 -*- 

from functools import reduce
from itertools import combinations
import random

def check(dS,k):
	""" given a set S, a dictionary dS with attributes 'prix', "calories' for each item of S,
		and an integer k, the fonction returns a couple (n,val) where nb is the number of subsets
		in dS of size k, and val is the maximum value of calories/prix over all subsets of size k """
	nb, val = 0, 0
	for subsets in combinations(dS, k):
		nb += 1
		sumCalories = sum([dS[j][0] for j in subsets])
		sumPrix = sum([dS[j][1] for j in subsets])
		val = max(val, sumCalories/sumPrix)
	return (nb, val)



def createDict(n):
	""" return a dictionary containing n fruits or vegetables
	and attributes (calories, price) for each portion"""
	mainlist = ['abricot', 'artichaut', 'asperge', 'aubergine', 'avocat', 'betterave', 'blette', 'brocoli', 'carotte', 'cassis', 'cerfeuil tubéreux', 'cerise', 'chou de Bruxelles', 'chou romanesco ', 'chou-fleur', 'chou-rave', 'châtaigne', 'citrouille', 'clémentine', 'coing', 'concombre', 'cornichon', 'courgette', 'céleri', 'datte', 'endive', 'flageolet', 'fraise', 'framboise', 'groseille', 'haricot', 'kaki', 'kiwi', 'laitue', 'lentille', 'mandarine', 'maïs doux', 'melon', 'mirabelle', 'myrtille', 'mâche', 'mûre', 'navet', 'noix', 'orange', 'pastèque', 'pastèque', 'patate douce', 'petit pois ', 'pissenlit', 'poire', 'poireau', 'pois chiche', 'poivron', 'pomme', 'pomme de terre', 'potiron', 'pâtisson', 'pêche', 'radis', 'radis noir', 'raisin', 'rhubarbe', 'roquette', 'rutabaga', 'salicorne', 'salsifis', 'scarole', 'tomate', 'topinambour', 'épinard ']	
	S = random.sample(mainlist, n)
	dictS = dict()
	for v in S:
		calories = random.randint(1, 500)	# random calories for a portion
		price = random.randint(1, 500)		# random price for a portion
		dictS[v]=(calories, price)
	return (S,dictS)



def GenerateSubsets(S,k):
	pass


def Permutation(S):
	pass
	
	
if __name__ == '__main__':
	random.seed()
	
	n, k = 25, 5
	S, dictS = createDict(n)
	
	print( check(dictS,k) )
	
#	for subsets in GenerateSubsets(S,k):
#		print("indices", subsets, " --> corresponding subset", [S[j] for j in subsets])
