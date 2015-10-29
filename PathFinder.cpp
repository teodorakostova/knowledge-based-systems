#include "PathFinder.h"
#include <queue>
#include <vector>

bool contains(vector<Node*> children, Node* child) {
    int childrenSize = children.size();
    for (int i = 0; i < childrenSize; i++) {
        if (children[i]->getData() == child->getData()) {
            return true;
        }
    }
    return false;
}

bool PathFinder::findPath(ostream& out, int limitOfStates) {
    int exploredNodes = 0;
    int statesGenerated = 0;
    queue<Node*> q;
    vector<Node*> visited;
    q.push(root);

    while (!q.empty()) {
        if (statesGenerated >= limitOfStates) {
            cout << "No solution found in this limit\n";
            break;
        }
        Node* current = q.front();
        q.pop();
        visited.push_back(current);

        if (current->isOrdered()) {
            cout << "Ordered" << endl;
            break;
        }
        vector<Node*> children;
        current->getChildren(children);
        cout << children.size() << endl;
        int childrenCount = children.size();
        for (int i = 0; i < childrenCount; i++) {
            statesGenerated++;
            if (!contains(visited, children[i])) {
                q.push(children[i]);
            }

        }
        exploredNodes++;
    }

    cout << "Generated states: " << statesGenerated << endl;
    cout << "Explored nodes: " << exploredNodes << endl;
}
