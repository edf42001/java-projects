# Connect Four AI

*I have preserved this high school project as-is, only recently adding this readme.*

---

In this game, I play as blue and the AI is red.

![Connect Four AI](../media/connect_four.gif)

## Overview

In this project, I use the minimax algorithm to create a Connect Four AI. Minimax works by searching through the potential
moves of both players, to determine the optimal move to make. Optimality is determined by the evaluation function, which
scores the state of the game (in this case, a Connect Four board) based on important features. Carefully designing (or learning)
this function is crucial for a successful minimax AI. See the next section for details on my evaluation function.

Minimax works for two-player games, usually of the zero-sum variety. This means that when one player is winning, the other is losing.
The evaluation function is designed to be symmetric, so that Player 1 attempts to maximize the evaluation function in order to win,
while Player 2 attempts to minimize it. Hence the name minimax.

in addition, I use alpha-beta pruning to increase the speed of the search. Essentially, the agent can stop investigating a sequence of moves
if it knows that those moves are definitely worse than other moves it could have taken.

## Evaluation function

The final evaluation function is simple, but is the result of much experimenting and tuning. It is as follows:

* **10000** points for a winning board.
* **500** points for each set of three pieces in a row, in any direction, including gaps. For example: a piece, a gap, then two pieces.
* **-1500** points for each set of the opponent's three pieces in a row, in any direction, including gaps.
* **-3000** points for each "dangerous column". These are columns that would complete an opponent's four in a row,
if you end up placing a piece in that column and they place a piece on top of yours.

Notice the asymmetry in the valuation for three pieces in a row. This is because Connect Four often come down to who
plays the most defensively, leaving less opportunities for the opponent to win during the end game. This is also the reasoning
behind the dangerous columns penalty.
