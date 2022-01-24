Algoritmo menu
	aukera = -1
	logeatuta = Falso
	atera = Falso
	
	Hacer
		si !logeatuta
			Escribir ("---------MENUA--------")
			Escribir ("| 1 | LOGEATU        |")
			Escribir ("| 3 | ERREGISTRATU   |")
			Escribir ("| 3 | ATERA          |")
			Leer aukera
			Segun aukera Hacer
				Caso, 1:
					Escribir ("LOGEATU ZARA")
					logeatuta = Verdadero
				Caso 2:
					Escribir ("ERREGISTRATU ZARA")
				De Otro Modo:
					Escribir ("ATERATZEN")
					atera = Verdadero
			FinSegun
		SiNo
			Escribir ("KAIXO ERABILTZAILE")
			Escribir ("---------MENUA--------")
			Escribir ("| 1 | BILETEAK IKUSI |")
			Escribir ("| 3 | EROSI BILETEA  |")
			Escribir ("| 3 | ATERA          |")
			Leer aukera
			Segun aukera Hacer
				Caso, 1:
					Escribir ("BILETEAK")
				Caso 2:
					Escribir ("EROSI DUZU BILETE BAT")
				De Otro Modo:
					Escribir ("ATERATZEN")
					logeatuta = Falso
			FinSegun
		FinSi
		
	Hasta Que atera
FinAlgoritmo