package day2



def HIS_MOVE_CODES = [A:"R", B:"P", C:"S"]
def MY_MOVE_CODES = [X:"R", Y:"P", Z:"S"]

def MOVE_POINTS = [R:1, P:2, S:3]
def WINS = [R:"S", P:"R", S:"P" ]

int LOSS_POINTS = 0
int DRAW_POINTS = 3
int VICTORY_POINTS = 6


def rateMove =  {movement ->
	return MOVE_POINTS.get(movement)
}

def rateVictory = {def hisMove, def myMove ->
		
	if (hisMove == myMove) {
		return DRAW_POINTS
	}
	
	def vencido = WINS.get(hisMove)
	return (vencido == myMove) ? LOSS_POINTS : VICTORY_POINTS  
}

def rateMyMove = {def hisMove, def myMove ->
	return rateMove(myMove) + rateVictory(hisMove, myMove)
}


int points = 0
File input = "docs/day2.txt" as File
input.splitEachLine(" ") {round ->
	
	def hisMove = HIS_MOVE_CODES[round[0]]
	def myMove = MY_MOVE_CODES[round[1]]
	
	println round
	println hisMove
	println myMove
	
	int roundPoints = rateMyMove(hisMove, myMove)
	println "$hisMove vs $myMove : $roundPoints"
	points += roundPoints	
}

println points