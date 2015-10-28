#include "Node.h"

Node::Node(string data, int pos) {
    this->data = data;
    if (pos < 0)
        this->x_pos = findX();
    else
        this->x_pos = pos;
}

Node::~Node() {

}

int Node::getXPos() const {
    return x_pos;
}

void Node::getChildren(vector<Node*>& children) {
    if (canMoveDown())
        children.push_back(&moveX(getDownPos()));
    if (canMoveUp())
        children.push_back(&moveX(getUpPos()));
    if (canMoveLeft())
        children.push_back(&moveX(getLeftPos()));
    if (canMoveRight())
        children.push_back(&moveX(getRightPos()));
}


string Node::getData() const {
    return data;
}

int Node::findX() const {
    int len = sizeof(data) + 1;
    for (int i = 0; i < len; i++)
        if (data[i] == 'x')
            return i;
    return -1;
}

bool Node::canMoveUp() const {
    return x_pos >= MATRIX_DIM;
}

bool Node::canMoveDown() const {
    return x_pos < sizeof(data) - MATRIX_DIM + 1;
}

bool Node::canMoveRight() const {
    return (x_pos + 1) % MATRIX_DIM != 0;
}

bool Node::canMoveLeft() const {
    return x_pos % MATRIX_DIM != 0;
}


bool Node::isOrdered() const {
    if (data[0] != EMPTY_CELL && data[MATRIX_DIM * MATRIX_DIM - 1] != 'x')
        return false;
    bool ordered = true;
    int len = sizeof(data) + 1;
    for (int i = 1; i < len - 1; i++)
        if (data[i] > data[i+1])
            ordered = false;

    bool orderedBackwards = true;
     for (int i = 1; i < len - 1; i++)
        if (data[i] < data[i+1])
            orderedBackwards = false;
    return orderedBackwards || ordered;
}

int Node::getDownPos() const {
    return x_pos + MATRIX_DIM;
}

int Node::getUpPos() const {
    return x_pos - MATRIX_DIM;
}

int Node::getRightPos() const {
    return x_pos + 1;
}

int Node::getLeftPos() const {
    return x_pos - 1;
}

Node& Node::moveX(int moveToIndex) const {
    string tmp_data(data);
    swap(tmp_data[x_pos], tmp_data[moveToIndex]);
    return *(new Node(tmp_data, moveToIndex));
}

void Node::print(ostream& out) const {
    int length = data.size();
    for (int i = 0; i < length; i++) {
        out << data[i] << " ";
        if ((i + 1) % MATRIX_DIM == 0)
            out << endl;
    }
}
