#include <iostream>
#include <cstring>
#include <vector>
#include <fstream>
#include <queue>
#include "Node.h"
#include "PathFinder.h"
using namespace std;

void play(Node& start, int max_moves) {
    cout << "Left: A or 4\nRight: D or 6\nUp: W or 8\nDown: S or 5\n";
    start.print(cout);
    int moves = 0;
    char choice;
    while (cin >> choice) {
        switch (choice) {
            case 'A':
            case 'a':
            case '4':
                if (!start.canMoveLeft())
                    throw "out of bounds";
                start = start.moveX(start.getLeftPos());
                break;
            case 'D':
            case 'd':
            case '6':
                if (!start.canMoveRight())
                    throw "out of bounds";
                start = start.moveX(start.getRightPos());
                break;
            case 'S':
            case 's':
            case '5':
                if (!start.canMoveDown())
                    throw "out of bounds";
                start = start.moveX(start.getDownPos());
                break;
            case 'W' :
            case 'w':
            case '8':
                if (!start.canMoveUp())
                    throw "out of bounds";
                start = start.moveX(start.getUpPos());
                break;
        }
        moves++;
        system("clear");
        start.print(cout);
        if (start.isOrdered()) {
            cout << "YOU WIN\n";
        }
        if (moves > max_moves) {
            cout << "GAME OVER\n";
        }
    }
}


int main() {
    Node* root = new Node("182x43765", -2);
    ofstream out;
    out.open("out.txt", ios::trunc);
    PathFinder p(*root);
    p.findPath(out,20000);

    return 0;
}
