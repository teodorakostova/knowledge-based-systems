#include "Node.h"
#define MATRIX_DIM 3
#define EMPTY_CELL 'x'
#define goal "12345678x"
#define goal1 "x12345678"

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
    int len = MATRIX_DIM * MATRIX_DIM;
    for (int i = 0; i < len; i++)
        if (data[i] == EMPTY_CELL)
            return i;
    return -1;
}

bool Node::canMoveUp() const {
    return x_pos >= MATRIX_DIM;
}

bool Node::canMoveDown() const {
    return x_pos + MATRIX_DIM < MATRIX_DIM * MATRIX_DIM - 1;
}

bool Node::canMoveRight() const {
    return (x_pos + 1) % MATRIX_DIM != 0;
}

bool Node::canMoveLeft() const {
    return x_pos % MATRIX_DIM != 0;
}


bool Node::isOrdered() const {
    return data == goal || data == goal1;
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
    int length = MATRIX_DIM * MATRIX_DIM;
    for (int i = 0; i < length; i++) {
        out << data[i] << " ";
        if ((i + 1) % MATRIX_DIM == 0)
            out << endl;
    }
}
