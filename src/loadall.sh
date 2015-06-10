#!/bin/bash

case $1 in
	"consumable")
	for f in tools/npceventos/BDconsumables/*; do
		echo "in $f ..."
		java tools.Creator consumable $f
	done ;;

	"equip")
	for f in tools/npceventos/BDequips/*; do
		echo "in $f ..."
		java tools.Creator equip $f 
	done ;;

	"quiz")
	for f in tools/npceventos/BDQuiz/*; do
		echo "in $f ..."
		java tools.Creator quiz $f 
	done ;;

	"randomev")
	for f in tools/npceventos/Eventos/*; do
		echo "in $f ..."
		java tools.Creator randomev $f
	done ;;

	"attack")
	for f in tools/npceventos/BDattacks/*; do
		echo "in $f ..."
		java tools.Creator attack $f
	done ;;

	"npc")	
	for f in tools/npceventos/NPCS/*; do
		echo "in $f ..."
		java tools.Creator npc $f
	done ;;

	"room")
	for f in tools/npceventos/BDrooms/*; do
		echo "in $f ..."
		java tools.Creator room $f 
	done ;;

	"all")
	for a in "consumable" "equip" "quiz" "randomev" "attack" "npc" "room"; do
		./loadall.sh $a
	done ;;

esac

