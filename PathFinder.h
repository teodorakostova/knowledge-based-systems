#pragma once
#include "Node.h"

// Search tree
class PathFinder {

public:
    PathFinder(Node& root) : root(&root) {};
    bool findPath(ostream&, int);
    bool aStar(ostream& out, int limitOfStates);
private:
// initial state
    Node* root;

};
