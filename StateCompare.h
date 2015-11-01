#pragma once
#include "Node.h"


class StateCompare {
    // returns true if lhs is a better candidate than rhs


public:
    bool operator()(Node*& lhs, Node*& rhs) {
        //return lhs->getPriority() > rhs->getPriority();
    }

};
