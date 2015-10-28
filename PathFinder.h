#pragma once
#include "Node.h"

// Search tree
class PathFinder {

public:
    PathFinder(Node& root) : root(&root) {};
    bool findPath(ostream&, int);
private:
// initial state
    Node* root;

};
